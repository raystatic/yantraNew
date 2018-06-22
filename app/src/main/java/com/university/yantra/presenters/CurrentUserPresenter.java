package com.university.yantra.presenters;

import android.util.Log;

import com.university.yantra.common.AbstractCallback;
import com.university.yantra.listeners.OnUserReceivedListener;
import com.university.yantra.models.User;
import com.university.yantra.viewactions.BaseViewAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 24/02/18.
 */

public class CurrentUserPresenter extends BasePresenter{

    OnUserReceivedListener onUserReceivedListener;
    BaseViewAction viewAction;
    public static final String TAG = CurrentUserPresenter.class.getSimpleName();

    public CurrentUserPresenter(OnUserReceivedListener onUserReceivedListener, BaseViewAction viewAction){
        this.onUserReceivedListener = onUserReceivedListener;
        this.viewAction = viewAction;
    }

    public void getCurrentUser(){
        repository.getCurrentUser(new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 200){
                    onUserReceivedListener.onUserReceived((User) response.body());
                } else {
                    Log.d(TAG, response.code() + " " + response.message());
                    viewAction.showMessage("Some problem occurred while retrieving user data!");
                }
            }
        });
    }
}
