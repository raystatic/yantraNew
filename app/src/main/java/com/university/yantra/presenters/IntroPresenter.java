package com.university.yantra.presenters;


import com.university.yantra.common.PrefManager;
import com.university.yantra.view.IntroNavigator;

public class IntroPresenter {

    private IntroNavigator introNavigator;

    public IntroPresenter(IntroNavigator introNavigator){
        this.introNavigator = introNavigator;
    }

    public void start(PrefManager prefManager) {

        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            introNavigator.startPreferencesActivity();
        }else{
            introNavigator.startMainActivity();
        }
    }
}
