package com.university.yantra.bottomfragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.Activities.WheelActivity;
import com.university.yantra.R;
import com.university.yantra.common.PrefManager;

import java.util.Random;

/**
 * Created by Rishabh on 2/14/2018.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    TextView text;
    Button btn;
    ImageView wheel;
    //Presenter presenter;

    Random r;

    Typeface t;

    int degrees=0, old_degrees=0;

    private final float FACTOR = 15f;



    boolean isPressed = false;

    public String SCORE ="";


    // Each variable will get its value level by level
    public int TWF_SCORE;

    //**************************************************
   /* Score for JACKPOT = 999
    Score for SORRY = 0*/
    //**************************************************


    PrefManager prefManager;

    public  final String TASKWORKFLOW_SCORE_LEVEL1 = "TaskWorkFlow_Score_Level1";
    public  final String TASKWORKFLOW_SCORE_LEVEL2 = "TaskWorkFlow_Score_Level2";
    public  final String TASKWORKFLOW_SCORE_LEVEL3 = "TaskWorkFlow_Score_Level3";
    public  final String TASKWORKFLOW_SCORE_LEVEL4 = "TaskWorkFlow_Score_Level4";
    public  final String TASKWORKFLOW_SCORE_LEVEL5 = "TaskWorkFlow_Score_Level5";


    String mes, title;


    private String loseMes = "You have lost." +
            "\nBetter Luck Next Time!!";
    private String loseTitle = "OOPS!!";

    private String ScoreMes = "You have won additional 10% scholarship in all Universities" +
            "\nUse coupon code \"UNIV10\" to avail the discount." +
            "\nHappy Shopping!! :)";
    private String ScoreTitle = "CONGRATULATIONS!!";

    private String jackpotMes = "Congratulations!! You have won Jackpot!!" +
            "\nYou have won a \"scholarship\" from Yantra. Please contact yantra to claim your offer." +
            "\nuniversityexperts85@gmail.com";
    private String jackpotTitle = "JACKPOT!!";


    Intent i;

    int level;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alert;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_bottom_2, container, false);
        getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        prefManager = new PrefManager(getContext());

        i = getActivity().getIntent();

        level = i.getIntExtra("level", 1);
        //presenter = new Presenter(new Repository(),this,this);


        t = Typeface.createFromAsset(getActivity().getAssets(), "budmo.ttf");

        text = (TextView) view.findViewById(R.id.textView);
        btn = (Button) view.findViewById(R.id.button);
        wheel = (ImageView) view.findViewById(R.id.wheel);

        text.setTypeface(t);
        btn.setTypeface(t);

        r = new Random();

        btn.setOnClickListener(this);
        return  view;

    }
    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
    private String currentnumber(int degrees) {


        if (degrees >= (FACTOR * 1) && degrees <= (FACTOR * 3)) {
            SCORE = "800";
        }

        if (degrees >= (FACTOR * 3) && degrees <= (FACTOR * 5)) {
            SCORE = "600";
        }
        if (degrees >= (FACTOR * 5) && degrees <= (FACTOR * 7)) {
            SCORE = "JACKPOT!!!";
        }

        if (degrees >= (FACTOR * 7) && degrees <= (FACTOR * 9)) {
            SCORE = "700";
        }
        if (degrees >= (FACTOR * 9) && degrees <= (FACTOR * 11)) {
            SCORE = "1200";
        }
        if (degrees >= (FACTOR * 11) && degrees <= (FACTOR * 13)) {
            SCORE = "SORRY!";
        }
        if (degrees >= (FACTOR * 13) && degrees <= (FACTOR * 15)) {
            SCORE = "400";
        }
        if (degrees >= (FACTOR * 15) && degrees <= (FACTOR * 17)) {
            SCORE = "1500";
        }
        if (degrees >= (FACTOR * 17) && degrees <= (FACTOR * 19)) {
            SCORE = "300";
        }
        if (degrees >= (FACTOR * 19) && degrees <= (FACTOR * 21)) {
            SCORE = "2000";
        }
        if (degrees >= (FACTOR * 21) && degrees <= (FACTOR * 23)) {
            SCORE = "500";
        }


        // First section
        if ((degrees >= (FACTOR * 23) && degrees < 360) || (degrees >= 0 && degrees < (FACTOR *1))) {
            SCORE = "1000";
        }


        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(WheelActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, 800);*/




        return SCORE;
    }
    @Override
    public void onClick(View view) {

        if (!isPressed)
        {
            isPressed = true;

            old_degrees = degrees % 360;

            degrees = r.nextInt(3600) + 720;

            RotateAnimation rotate = new RotateAnimation(old_degrees, degrees, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(3600);
            rotate.setFillAfter(true);

            rotate.setInterpolator(new DecelerateInterpolator());

            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    text.setText("");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    text.setText(currentnumber(360 - (degrees % 360)));
                    if (SCORE.equals("JACKPOT!!!")) {
                        TWF_SCORE = 999;
                        mes = jackpotMes;
                        title = jackpotTitle;

                    } else if (SCORE.equals("SORRY!")) {
                        TWF_SCORE = 0;

                        mes = loseMes;
                        title = loseTitle;
                    } else {
                        TWF_SCORE = Integer.parseInt(SCORE);

                        mes = ScoreMes;
                        title = ScoreTitle;
                    }

                    prefManager.saveString(TASKWORKFLOW_SCORE_LEVEL1, TWF_SCORE + "");

                    dialog();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            wheel.startAnimation(rotate);

        }

    }
    public void dialog() {
        alertDialogBuilder = new AlertDialog.Builder(getContext());

        alertDialogBuilder.setMessage(mes);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){

                        // System.gc();



                        System.gc();
                        alert.dismiss();

                    }
                });


        //System.gc();

        alert = alertDialogBuilder.create();
        alert.show();
    }

}
