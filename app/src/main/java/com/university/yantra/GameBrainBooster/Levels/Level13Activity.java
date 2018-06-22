package com.university.yantra.GameBrainBooster.Levels;

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
import com.university.yantra.GameBrainBooster.MainActivity;
import com.university.yantra.GameBrainBooster.OrientationManager;
import com.university.yantra.R;

public class Level13Activity extends AppCompatActivity implements OrientationManager.OrientationListener{

    ImageButton img;
    ImageView check;
    Intent intent, i;
    Drawable wrong, right;

    ImageView flameL, flameR;
    int l=0,r=0;

    TextView text;

    Typeface t;
    CustomView c;

    OrientationManager orientationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level13);

        intent = getIntent();

        i = new Intent(this, Level2Activity.class);

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        flameL = (ImageView) findViewById(R.id.imageView10);
        flameR = (ImageView) findViewById(R.id.imageView11);

        c = (CustomView) findViewById(R.id.custom);
        c.level = 2;

        img = (ImageButton) findViewById(R.id.imageButton8);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life();
                check.setBackground(wrong);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(500, check);
            }
        });

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);

        orientationManager = new OrientationManager(this, SensorManager.SENSOR_DELAY_NORMAL, this);
        orientationManager.enable();
    }

    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case LANDSCAPE:

                l = 1;
                flameL.setVisibility(View.VISIBLE);
                checkImg();
                break;

            case REVERSED_LANDSCAPE:

                r=1;
                flameR.setVisibility(View.VISIBLE);
                checkImg();
                break;
        }

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

    public void checkImg() {
        if (l==1 && r==1) {
            orientationManager.disable();
            check.setBackground(right);

            CustomView.game8_CorrectlyAnswered++;


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
    }

    @Override
    protected void onStop() {
        super.onStop();

        orientationManager.disable();
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level13));
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
