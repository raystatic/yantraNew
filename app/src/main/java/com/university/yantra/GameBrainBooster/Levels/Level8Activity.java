package com.university.yantra.GameBrainBooster.Levels;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.CustomView;
import com.university.yantra.GameBrainBooster.EndActivity;
import com.university.yantra.GameBrainBooster.OrientationManager;
import com.university.yantra.R;

public class Level8Activity extends AppCompatActivity implements OrientationManager.OrientationListener{

    Drawable wrong, right;
    Intent intent;


    ImageView check;
    ImageButton owl;
    CustomView c;

    Typeface t;

    TextView text;
    Intent i;

    int x,y;

    OrientationManager orientationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intent = getIntent();

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level8);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        i = new Intent(this, EndActivity.class);



        x = getResources().getDisplayMetrics().widthPixels;
        y = getResources().getDisplayMetrics().heightPixels;


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView2);

        owl = (ImageButton) findViewById(R.id.imageButton5);
        owl.setX(2*x);

        c = (CustomView) findViewById(R.id.custom);
        c.level = 13;

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);

        orientationManager = new OrientationManager(this, SensorManager.SENSOR_DELAY_NORMAL, this);
        orientationManager.enable();
    }

    public void onSkip(View v) {
        orientationManager.disable();
        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }


    public  void imgClicked(View v){

        orientationManager.disable();

        CustomView.game8_CorrectlyAnswered++;

        check.setBackground(right);

        check.setVisibility(View.VISIBLE);
        timerDelayRemoveView(800, check);

        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }

    @Override
    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case LANDSCAPE:
                loadmainAniamtion();

                orientationManager.disable();
                break;
        }

    }


    public void loadmainAniamtion() {


        ObjectAnimator textAnimator;

        textAnimator = ObjectAnimator.ofFloat(owl, "x", x+50, x/2-x/7);
        textAnimator.setDuration(1000L);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(textAnimator);
        animatorSet.start();
    }


    public void timerDelayRemoveView(long time, final ImageView img) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                img.setVisibility(View.GONE);
            }
        }, time);
    }

    public  void life(){
        if (c.life>0)
            c.life-=1;
        c.invalidate();
    }

    @Override
    protected void onStop() {
        super.onStop();

        orientationManager.disable();
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level8));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
