package com.university.yantra.listeners;

import android.content.Context;
import android.widget.Toast;

import com.university.yantra.viewactions.BaseViewAction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sharda on 17/03/18.
 */

public abstract class OnEntitiesReceivedListener<T> implements BaseViewAction{

    Context context;

    public OnEntitiesReceivedListener(Context context){
        this.context = context;
    }

    public abstract void onReceived(List<T> entities);

    @Override
    public void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message){
        showMessage(message);
    }
}
