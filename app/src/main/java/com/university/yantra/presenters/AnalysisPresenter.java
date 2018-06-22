package com.university.yantra.presenters;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import com.university.yantra.listeners.AnalysisEventListener;
import com.university.yantra.models.Analysis.MCQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sharda on 10/03/18.
 */

public class AnalysisPresenter extends BasePresenter {

    private enum Screen {
        Intro, BioData,Preferences,Academics,HigherEducation,Social,EndScreenIntro,EmotionalIntro,Mood,MCQ1,MCQ2,MCQ3,MCQ4,MCQ5,MCQ6,MCQ7,SeekBarScreen,MCQ9,EndScreenEmotional,CriticalIntro
        ,Ques1,Ques2,Ques3,Ques4,Ques5,EndScreenCritical,LifeChoicesIntro,EndScreenLife,Choices,BrainBoosterIntro,PersonalIntro,Handwriting,EndScreenPersonal
    }

    public int sequence;
    public int lastQuestionId;
    ArrayList<Screen> screens;
    AnalysisEventListener viewAction;
    Map<Integer, MCQ> mcqMap;   //caches MCQs by ID.

    public AnalysisPresenter(AnalysisEventListener viewAction){
        this.viewAction = viewAction;
        screens = new ArrayList<Screen>();
        sequence = -1;
        lastQuestionId = 0;
        mcqMap = new HashMap<Integer, MCQ>();
    }


    public void start(int i){


        screens.add(Screen.Intro);
        screens.add(Screen.BioData);
        screens.add(Screen.Preferences);
        screens.add(Screen.Academics);
        screens.add(Screen.HigherEducation);
        screens.add(Screen.Social);
        screens.add(Screen.EndScreenIntro);
        screens.add(Screen.EmotionalIntro);
        screens.add(Screen.Mood);
        screens.add(Screen.MCQ1);
        screens.add(Screen.MCQ2);
        screens.add(Screen.MCQ3);
        screens.add(Screen.MCQ4);
        screens.add(Screen.MCQ5);
        screens.add(Screen.MCQ6);
        screens.add(Screen.MCQ7);
        screens.add(Screen.SeekBarScreen);
        screens.add(Screen.MCQ9);
        screens.add(Screen.EndScreenEmotional);
        screens.add(Screen.CriticalIntro);
        screens.add(Screen.Ques1);
        screens.add(Screen.Ques2);
        screens.add(Screen.Ques3);
        screens.add(Screen.Ques4);
        screens.add(Screen.Ques5);
        screens.add(Screen.EndScreenCritical);
        screens.add(Screen.LifeChoicesIntro);
        screens.add(Screen.Choices);
        screens.add(Screen.EndScreenLife);
        screens.add(Screen.BrainBoosterIntro);
        screens.add(Screen.PersonalIntro);
        screens.add(Screen.Handwriting);
        screens.add(Screen.EndScreenPersonal);
        if(i==0) {
            sequence = -1;
            next();
        }
      else if(i==1)
        {
            sequence = 30;
        }
        else{
            sequence = 2;
        }

    }

    public void next() {


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (++sequence < screens.size()) {
                    switch (screens.get(sequence)) {
                        case Intro:
                            lastQuestionId++;
                            viewAction.showIntroScreen();
                            break;
                        case BioData:
                            lastQuestionId++;
                            viewAction.showBioDataScreen();
                            break;
                        case Preferences:
                            lastQuestionId++;
                            viewAction.showPreferencesScreen();
                            break;
                        case BrainBoosterIntro:
                            lastQuestionId++;
                            viewAction.showBrainBoosterIntroScreen();
                            break;
                        case Academics:
                            lastQuestionId++;
                            viewAction.showAcademicsScreen();
                            break;
                        case HigherEducation:
                            lastQuestionId++;
                            viewAction.showHigherEducationScreen();
                            break;
                        case Social:
                            lastQuestionId++;
                            viewAction.showSocialScreen();
                            break;
                        case EndScreenIntro:
                            lastQuestionId++;
                            viewAction.showEndScreenIntro();
                            break;
                        case EndScreenEmotional:
                            lastQuestionId++;
                            viewAction.showEndScreenEmotional();
                            break;
                        case EndScreenCritical:
                            lastQuestionId++;
                            viewAction.showEndScreenCritical();
                            break;
                        case EndScreenLife:
                            lastQuestionId++;
                            viewAction.showEndScreenLife();
                            break;
                        case EndScreenPersonal:
                            lastQuestionId++;
                            viewAction.showEndScreenPersonal();
                            break;
                        case EmotionalIntro:
                            lastQuestionId++;
                            viewAction.showEmotionalIntroScreen();
                            break;
                        case Mood:
                            lastQuestionId++;
                            viewAction.showMoodScreen();
                            break;
                        case MCQ1:
                            lastQuestionId++;
                            viewAction.showQuestion1Screen(getMCQ());
                            break;
                        case MCQ2:
                            lastQuestionId++;
                            viewAction.showQuestion2Screen(getMCQ());
                            break;
                        case MCQ3:
                            lastQuestionId++;
                            viewAction.showQuestion3Screen(getMCQ());
                            break;
                        case MCQ4:
                            lastQuestionId++;
                            viewAction.showQuestion4Screen(getMCQ());
                            break;
                        case MCQ5:
                            lastQuestionId++;
                            viewAction.showQuestion5Screen(getMCQ());
                            break;
                        case MCQ6:
                            lastQuestionId++;
                            viewAction.showQuestion6Screen(getMCQ());
                            break;
                        case MCQ7:
                            lastQuestionId++;
                            viewAction.showQuestion7Screen(getMCQ());
                            break;
                        case SeekBarScreen:
                            lastQuestionId++;
                            viewAction.showSeekBarScreen();
                            break;
                        case MCQ9:
                            lastQuestionId++;
                            viewAction.showQuestion9Screen(getMCQ());
                            break;
                        case CriticalIntro:
                            lastQuestionId++;
                            viewAction.showCriticalIntroScreen();
                            break;
                        case Ques1:
                            lastQuestionId++;
                            viewAction.showCriticalQues1Screen();
                            break;
                        case Ques2:
                            lastQuestionId++;
                            viewAction.showCriticalQues2Screen();
                            break;
                        case Ques3:
                            lastQuestionId++;
                            viewAction.showCriticalQues3Screen();
                            break;
                        case Ques4:
                            lastQuestionId++;
                            viewAction.showCriticalQues4Screen();
                            break;
                        case Ques5:
                            lastQuestionId++;
                            viewAction.showCriticalQues5Screen();
                            break;
                        case LifeChoicesIntro:
                            lastQuestionId++;
                            viewAction.showLifeChoicesIntroScreen();
                            break;
                        case Choices:
                            lastQuestionId++;
                            viewAction.showLifeChoicesScreen();
                            break;
                        case PersonalIntro:
                            lastQuestionId++;
                            viewAction.showPersonalIntroScreen();
                            break;
                        case Handwriting:
                            lastQuestionId++;
                            viewAction.showHandwritingScreen();
                            break;
                    }
                } else {
                    viewAction.showMessage("analysis completed"+sequence);
                    sequence--;
                }


            }
            }, 200);


        }

    public void previous(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(--sequence >= 0) {

                    switch (screens.get(sequence)) {
                        case Intro:
                            viewAction.showIntroScreen();
                            break;
                        case BioData:

                            viewAction.showBioDataScreen();
                            break;
                        case Preferences:

                            viewAction.showPreferencesScreen();
                            break;
                        case Academics:

                            viewAction.showAcademicsScreen();
                            break;
                        case HigherEducation:
                            viewAction.showHigherEducationScreen();
                            break;
                        case Social:

                            viewAction.showSocialScreen();
                            break;
                        case BrainBoosterIntro:

                            viewAction.showBrainBoosterIntroScreen();
                            break;
                        case EmotionalIntro:

                            viewAction.showEmotionalIntroScreen();
                            break;
                        case EndScreenIntro:
                            viewAction.showEndScreenIntro();
                            break;
                        case EndScreenEmotional:

                            viewAction.showEndScreenEmotional();
                            break;
                        case EndScreenCritical:

                            viewAction.showEndScreenCritical();
                            break;
                        case EndScreenLife:

                            viewAction.showEndScreenLife();
                            break;
                        case EndScreenPersonal:

                            viewAction.showEndScreenPersonal();
                            break;
                        case Mood:

                            viewAction.showMoodScreen();
                            break;
                        case MCQ1:

                            viewAction.showQuestion1Screen(getMCQ());
                            break;
                        case MCQ2:

                            viewAction.showQuestion2Screen(getMCQ());
                            break;
                        case MCQ3:

                            viewAction.showQuestion3Screen(getMCQ());
                            break;
                        case MCQ4:

                            viewAction.showQuestion4Screen(getMCQ());
                            break;
                        case MCQ5:
                            lastQuestionId++;
                            viewAction.showQuestion5Screen(getMCQ());
                            break;
                        case CriticalIntro:

                            viewAction.showCriticalIntroScreen();
                            break;
                        case Ques1:

                            viewAction.showCriticalQues1Screen();
                            break;
                        case Ques2:

                            viewAction.showCriticalQues2Screen();
                            break;
                        case Ques3:

                            viewAction.showCriticalQues3Screen();
                            break;
                        case Ques4:

                            viewAction.showCriticalQues4Screen();
                            break;
                        case MCQ6:
                            viewAction.showQuestion6Screen(getMCQ());
                            break;
                        case MCQ7:
                            viewAction.showQuestion7Screen(getMCQ());
                            break;
                        case SeekBarScreen:
                            viewAction.showSeekBarScreen();
                            break;
                        case MCQ9:
                            viewAction.showQuestion9Screen(getMCQ());
                            break;
                        case Ques5:

                            viewAction.showCriticalQues5Screen();
                            break;
                        case LifeChoicesIntro:

                            viewAction.showLifeChoicesIntroScreen();
                            break;

                        case Choices:

                            viewAction.showLifeChoicesScreen();
                            break;
                        case PersonalIntro:

                            viewAction.showPersonalIntroScreen();
                            break;
                        case Handwriting:

                            viewAction.showHandwritingScreen();
                            break;

                    }

                }
                else{
                    viewAction.finishActivity();
                }

            }
        }, 200);

    }

    public MCQ getMCQ(){
        //first check if the MCQ lies in the cache map;
        MCQ mcq = mcqMap.get(lastQuestionId);
        if(mcq != null) return mcq;

        //if not present in cache
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        // get next MCQ from API using the lastQuestionId, put the MCQ in map cache
        return  mcq;
    }

}
