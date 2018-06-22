package com.university.yantra.common;

import android.os.Handler;
import android.util.Log;

import com.university.yantra.viewactions.BaseViewAction;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sharda on 24/02/18.
 */

abstract public class AbstractCallback implements Callback {

    BaseViewAction viewAction;
    static Boolean IS_FAILURE = false;
    public static final String TAG = AbstractCallback.class.getSimpleName();

    public AbstractCallback(BaseViewAction viewAction){
        this.viewAction = viewAction;
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ISFAIL : " + IS_FAILURE);
        if(IS_FAILURE)
            return;

        IS_FAILURE = true;     //acquire lock

        Log.d("Network Failure Log", t.getMessage());
        viewAction.showNetworkError("Unable to connect. Please try again!");    //show message

        new Handler().postDelayed(new Runnable() {  //release lock after 2 seconds
            @Override
            public void run() {

                    IS_FAILURE = false;
            }
        }, 1000);
    }
}
