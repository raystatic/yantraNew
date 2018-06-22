package com.university.yantra.Activities;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.apiservices.responses.GenericResponse;
import com.university.yantra.common.MyApplication;
import com.university.yantra.listeners.auth.OnAuthenticatedListener;
import com.university.yantra.presenters.CurrentUserPresenter;
import com.university.yantra.repositories.FirebaseRepository;
import com.university.yantra.viewactions.BaseViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rishabh on 3/4/2018.
 */

public class FirebaseIDService extends FirebaseInstanceIdService implements OnAuthenticatedListener,BaseViewAction {
    private static final String TAG = "FirebaseIDService";
    private static String token = null;
    public static final String TOKEN = "token";


    public void FirebaseIDService(){

    }
    @Override
    public void onTokenRefresh() {
        showMessage("called ontokenrefresh");
        token = FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance();
        if(token!=null) {
            Log.d( "Refreshed token: ", token);
            MyApplication.getInstance().prefManager.saveString(TOKEN,token+"");
            //sendRegistrationToServer(token);
        }
        else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    onTokenRefresh();
                }
            }, 5000);

        }

    }

    public static String getToken(){
        return FirebaseInstanceId.getInstance().getToken();
    }

    public void sendRegistrationToServer(final String token){
        Log.d("refreshed","called");

        if(token!=null){
            Log.d("token",token+"");
            FirebaseRepository repository = new FirebaseRepository();
            repository.sendRegistrationIdToServer(token, new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    GenericResponse genericResponse = (GenericResponse)response.body();
                    if(genericResponse!=null){
                        Log.d("firebaseResponse","not null"+response.message());
                       // Toast.makeText(FirebaseIDService.this,"done",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("firebaseResponse","null");
                       // Toast.makeText(FirebaseIDService.this,"null",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("firebaseResponse","failed");
                   // Toast.makeText(FirebaseIDService.this,"failure",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void showMessage(String message) {
       // Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void onAuthenticated(AuthenticationResponse authenticationResponse) {

    }
}
