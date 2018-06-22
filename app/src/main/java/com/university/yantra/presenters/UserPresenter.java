package com.university.yantra.presenters;

import android.nfc.Tag;
import android.util.Log;

import com.university.yantra.listeners.OnUsersReceivedListener;
import com.university.yantra.models.User;
import com.university.yantra.viewactions.BaseViewAction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 24/02/18.
 */

public class UserPresenter extends BasePresenter{

    OnUsersReceivedListener onUsersReceivedListener;
    BaseViewAction viewAction;
    public static final String TAG = UserPresenter.class.getSimpleName();

    public UserPresenter(OnUsersReceivedListener onUsersReceivedListener, BaseViewAction viewAction){
        this.onUsersReceivedListener = onUsersReceivedListener;
        this.viewAction = viewAction;
    }

    private class UserReceiveCallback implements Callback{

        @Override
        public void onResponse(Call call, Response response) {
            if(response.code() == 200){
                onUsersReceivedListener.onUsersReceived((List<User>) response.body());
            } else {
                Log.d(TAG, response.code() + " " + response.message());
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Log.d(TAG, t.getMessage());
            viewAction.showMessage("Network Problem");
        }
    }

    public void getUsers(String keyword){

    }
}
