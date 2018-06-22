package com.university.yantra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.common.MyApplication;
import com.university.yantra.listeners.auth.OnAuthenticatedListener;
import com.university.yantra.listeners.auth.OnCreateUserListener;
import com.university.yantra.models.User;
import com.university.yantra.presenters.AuthenticationPresenter;
import com.university.yantra.presenters.SignUpPresenter;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends BaseActivity implements OnCreateUserListener, OnAuthenticatedListener{

    private AwesomeValidation awesomeValidation;
    TextView login;
    AuthenticationPresenter authenticationPresenter;
    SignUpPresenter signUpPresenter;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText mobileNo;
    EditText password;
    EditText rePassword;
    Button submit;
    Map<String,String> map= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login = (TextView)findViewById(R.id.link_login);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.input_email);
        mobileNo = (EditText)findViewById(R.id.input_mobile);
        password = (EditText)findViewById(R.id.input_password);
        rePassword = (EditText)findViewById(R.id.input_reEnterPassword);

        submit = (Button)findViewById(R.id.btn_signup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.first_name,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.last_name,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.input_mobile,"[0-9]+", R.string.mobileerror);
        awesomeValidation.addValidation(this,R.id.input_email, Patterns.EMAIL_ADDRESS,R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.input_password,"^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$",R.string.passerror);
        awesomeValidation.addValidation(this,R.id.input_reEnterPassword,"^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$",R.string.passerror);

        authenticationPresenter = new AuthenticationPresenter(this, this);
        signUpPresenter = new SignUpPresenter(this, this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    if(password.getText().toString().equals(rePassword.getText().toString()))
                    {
                        showMessage("validated");
                        map.put("username",email.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("first_name",firstName.getText().toString());
                        map.put("last_name",lastName.getText().toString());
                        map.put("contact",mobileNo.getText().toString());
                        map.put("password",password.getText().toString());


                        signUpPresenter.signUp(map);



                    }
                    else{
                        showMessage("passwords don't match");
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });



    }

    @Override
    public void onUserCreated(User user) {
        //user created, log in the user now.
        authenticationPresenter.loginDefault("username", "password");
    }


    @Override
    public void onAuthenticated(AuthenticationResponse authenticationResponse) {
        MyApplication.getInstance().prefManager.putAuthenticationTokens(authenticationResponse);
    }
}
