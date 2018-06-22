package com.university.yantra.presenters;

import android.util.Log;

import com.university.yantra.common.AbstractCallback;
import com.university.yantra.listeners.auth.OnCreateUserListener;
import com.university.yantra.models.User;
import com.university.yantra.viewactions.BaseViewAction;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Paras Jain
 * @version 1.0
 * @since 2018-02-24
 */
public class SignUpPresenter extends BasePresenter {

    public static final String TAG = SignUpPresenter.class.getSimpleName();
    BaseViewAction viewAction;
    OnCreateUserListener onCreateUserListener;

    public SignUpPresenter(OnCreateUserListener onCreateuserListener, BaseViewAction viewAction){
        this.onCreateUserListener = onCreateuserListener;
        this.viewAction = viewAction;
    }

    public void signUp(Map<String, String> map){
        repository.registerUser(map, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 201){
                    //user successfully registered!
                    viewAction.showMessage("Account created successfully! Logging you in...");
                    onCreateUserListener.onUserCreated((User) response.body());
                } else {
                    Log.d("errorsign", response.code() + " " + response.message());
                    viewAction.showMessage("There was some problem while creating your account! Please try again.");
                }
            }
        });
    }
}
