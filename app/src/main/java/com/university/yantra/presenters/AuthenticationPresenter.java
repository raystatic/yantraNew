package com.university.yantra.presenters;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.university.yantra.Activities.LoginActivity;
import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.common.Constants;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.listeners.auth.OnAuthenticatedListener;
import com.university.yantra.viewactions.BaseViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 * <h1>Authentication Presenter - Logs in the user</h1>
 *
 * <p>Provides various methods for authenticating the user by requesting OAUTH2 token from backend and
 * notifying the OnAuthenticatedListener</p>
 *
 * @author Paras Jain
 * @version 1.0
 * @since 2018-02-24
 */
public class AuthenticationPresenter extends BasePresenter {

    public static final String TAG = AuthenticationPresenter.class.getSimpleName();
    OnAuthenticatedListener listener;
    BaseViewAction viewAction;

    public AuthenticationPresenter(OnAuthenticatedListener listener, BaseViewAction viewAction){
        this.listener = listener;
        this.viewAction = viewAction;
    }

    public void loginDefault(String username, String password){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.OAUTH2_USERNAME_PARAM_KEY, username);
        map.put(Constants.OAUTH2_PASSWORD_PARAM_KEY, password);
        repository.loginDefault(map, new AuthenticationCallback());
    }

    public void loginFacebook(String facebookAccessToken){
        repository.loginFacebook(facebookAccessToken, new AuthenticationCallback());
    }

    public void loginGoogle(String googleAccessToken){
        repository.loginGoogle(googleAccessToken, new AuthenticationCallback());
    }

    public void logout(){

        //logout from facebook
        if(AccessToken.getCurrentAccessToken() != null){
            LoginManager.getInstance().logOut();
        }

        MyApplication.getInstance().prefManager.logout();
    }


    /**
     * Implementation of the retrofit2 Callback for handling Authentication response.
     */
    private class AuthenticationCallback implements Callback{

        @Override
        public void onResponse(Call call, Response response) {
            if(response.code() == 200){
                listener.onAuthenticated((AuthenticationResponse) response.body());
//
            } else {
                Log.d(TAG, response.code() + " " + response.message());
                viewAction.showMessage("There was some problem while authenticating. Please try again.");
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Log.d(TAG, t.getMessage());
            viewAction.showMessage("There was some problem while authenticating. Please try again.");
        }
    }
}
