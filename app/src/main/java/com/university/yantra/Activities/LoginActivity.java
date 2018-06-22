package com.university.yantra.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.messaging.*;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.common.MyApplication;
import com.university.yantra.listeners.auth.OnAuthenticatedListener;
import com.university.yantra.presenters.AuthenticationPresenter;

import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements OnAuthenticatedListener, GoogleApiClient.OnConnectionFailedListener{

    TextView skip;
    TextView signUp;
    public static final String FB_LOGIN = "FB_LOGIN";
    public static final String GOOGLE_LOGIN = "Google_login";
    public static final String FB_PROFILE= "FB_PROFILE";
    @BindView(R.id.email) EditText email;
    TextView forgotPassword;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.facebookLoginButton)LoginButton facebookLoginButton; CallbackManager callbackManager;
    @BindView(R.id.googleSigninButton)SignInButton googleSignInButton; private GoogleApiClient googleApiClient;

    private static final int GOOGLE_SIGIN_ACTIVITY_REQUEST_CODE = 321;
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skip = (TextView)findViewById(R.id.skip);
        signUp = (TextView)findViewById(R.id.registerButton);
        forgotPassword = findViewById(R.id.forgot_password);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        ButterKnife.bind(this);

        initFacebookLogin();
        initGoogleLogin();

    }

    @Override
    public void onAuthenticated(AuthenticationResponse authenticationResponse) {
        MyApplication.getInstance().prefManager.putAuthenticationTokens(authenticationResponse);
        FirebaseIDService idService = new FirebaseIDService();
        if(MyApplication.getInstance().prefManager.getString(FirebaseIDService.TOKEN)!=null)
            idService.sendRegistrationToServer(MyApplication.getInstance().prefManager.getString(FirebaseIDService.TOKEN));
        else{
            showMessage("token is still null");
        }
        startActivity(new Intent(this, MainActivity.class));
    }


    @OnClick(R.id.loginButton)
    void onLoginClicked(View v){
        showMessage("Please wait...");
        new AuthenticationPresenter(this, this).loginDefault(email.getText().toString(), password.getText().toString());
    }


    void initFacebookLogin(){
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setReadPermissions(Arrays.asList("email"));
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                showMessage("logged in fb");
                MyApplication.getInstance().prefManager.saveString(FB_LOGIN,"true");
                prefManager.saveString(FB_PROFILE,loginResult.getAccessToken().getUserId()+"");
                showMessage(loginResult.getAccessToken().getUserId()+"");

                new AuthenticationPresenter(LoginActivity.this, LoginActivity.this).loginFacebook(loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                showMessage("Facebook login cancelled!");
            }

            @Override
            public void onError(FacebookException e) {
                showMessage("Some error occurred while logging in via facebook. \n\n FB: " + e.getMessage());
            }
        });
    }


    void initGoogleLogin(){

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @OnClick(R.id.googleSigninButton)
    void onGoogleSignInButtonClicked(View view){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGIN_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOOGLE_SIGIN_ACTIVITY_REQUEST_CODE) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()) {
                final GoogleSignInAccount account = result.getSignInAccount();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String scope = "oauth2:"+Scopes.EMAIL+" "+ Scopes.PROFILE;
                            String accessToken = GoogleAuthUtil.getToken(getApplicationContext(), account.getAccount(), scope, new Bundle());
                            Log.d(TAG, "GoogleAccessToken: "+accessToken); //accessToken:ya29.Gl...
                            new AuthenticationPresenter(LoginActivity.this, LoginActivity.this).loginGoogle(accessToken);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (GoogleAuthException e) {
                            e.printStackTrace();
                        }
                    }
                };
                AsyncTask.execute(runnable);
            }
            else
                Toast.makeText(this, "Cancelled Google Login!", Toast.LENGTH_LONG).show();

        }  else  callbackManager.onActivityResult(requestCode, resultCode, data); //facebook login callback manager


        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
