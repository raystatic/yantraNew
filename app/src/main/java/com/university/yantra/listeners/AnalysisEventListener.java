package com.university.yantra.listeners;

import com.university.yantra.models.Analysis.MCQ;
import com.university.yantra.viewactions.BaseViewAction;

/**
 * Created by sharda on 10/03/18.
 */

public interface AnalysisEventListener extends BaseViewAction {
    void showQuestion1Screen(MCQ mcq);
    void showQuestion2Screen(MCQ mcq);
    void showQuestion3Screen(MCQ mcq);
    void showQuestion4Screen(MCQ mcq);
    void showQuestion5Screen(MCQ mcq);
    void showQuestion6Screen(MCQ mcq);
    void showQuestion7Screen(MCQ mcq);
    void showQuestion9Screen(MCQ mcq);
    void showSeekBarScreen();
    void showAcademicsScreen();
    void showSocialScreen();
    void showEndScreenIntro();
    void showEndScreenEmotional();
    void showEndScreenLife();
    void showEndScreenPersonal();
    void showEndScreenCritical();
    void showMoodScreen();
    void showLifeChoicesScreen();
    void showPreferencesScreen();
    void showBrainBoosterIntroScreen();
    void showHigherEducationScreen();
    void showIntroScreen();
    void showBioDataScreen();
    void showHandwritingScreen();
    void finishActivity();
    void showEmotionalIntroScreen();
     void  showCriticalIntroScreen();
     void  showCriticalQues1Screen();
     void  showCriticalQues2Screen();
     void  showCriticalQues3Screen();
     void  showCriticalQues4Screen();
     void  showCriticalQues5Screen();
     void  showLifeChoicesIntroScreen();
    void  showPersonalIntroScreen();

}
