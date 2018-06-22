package com.university.yantra.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by paras on 31-Mar-17.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    public PrefManager prefManager;
    //public static NetworkErrorDialogDriver networkErrorDialogDriver;

    @Override
    public void onCreate() {

        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT>19) {
            Log.d("myapp","higher than kitkat");

            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/DroidSans.ttf")
                    .setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath)
                    .build());

        }

        prefManager = new PrefManager(this);

        mInstance = this;

        //networkErrorDialogDriver = new NetworkErrorDialogDriver(getBaseContext());

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

//    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//        ConnectivityReceiver.connectivityReceiverListener = listener;
//    }
@Override
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(newBase);
    MultiDex.install(this);
}

}
