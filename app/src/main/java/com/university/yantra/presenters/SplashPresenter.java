package com.university.yantra.presenters;


import com.university.yantra.common.PrefManager;
import com.university.yantra.view.SplashNavigator;

/**
 * Created by nikhiljain on 1/27/17.
 */

public class SplashPresenter {
    private SplashNavigator splashNavigator;

    public SplashPresenter(SplashNavigator splashNavigator) {
        this.splashNavigator = splashNavigator;
    }


    public void start(PrefManager prefManager) {


         if (prefManager.isFirstTimeLaunch()) {
            splashNavigator.startIntroActivity();
        }
        else if (!prefManager.isFirstTimeLaunch()) {
             if(prefManager.isLoggedIn()){
                 splashNavigator.startMainActivity();
             }
             else
            splashNavigator.startLoginActivity();
//        } else if(!prefManager.isFirstTimeLaunch() && !prefManager.isNotLogin()) {
//            splashNavigator.startMainActivity();
//        }
        }
    }
}
