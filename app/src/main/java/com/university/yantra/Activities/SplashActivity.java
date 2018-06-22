package com.university.yantra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.presenters.SplashPresenter;
import com.university.yantra.view.SplashNavigator;

public class SplashActivity extends BaseActivity implements SplashNavigator{

    SplashPresenter splashPresenter;
    private static int  SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Thread(){

            @Override
            public void run() {

                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {

                    splashPresenter = new SplashPresenter(SplashActivity.this);
                    splashPresenter.start(MyApplication.getInstance().prefManager);

                }
            }
        }.start();
    }


    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startIntroActivity() {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}
