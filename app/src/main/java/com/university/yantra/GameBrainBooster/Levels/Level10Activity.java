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
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.CustomView;
import com.university.yantra.GameBrainBooster.OrientationManager;
import com.university.yantra.R;

public class Level10Activity extends AppCompatActivity implements View.OnClickListener, OrientationManager.OrientationListener{

    CustomView c;
    ImageView check;

    TextView text, eq;

    Intent intent, i;
    Typeface t, t1;


    Drawable wrong, right;

    OrientationManager orientationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level10);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        c = (CustomView) findViewById(R.id.custom);
        c.level=7;

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");
        t1 = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");


        intent = getIntent();

        i = new Intent(this, Level5Activity.class);

        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        text = (TextView) findViewById(R.id.textView2);
        eq = (TextView) findViewById(R.id.textView9);

        text.setTypeface(t);
        eq.setTypeface(t1);

        eq.setOnClickListener(this);

        orientationManager = new OrientationManager(getApplicationContext(), SensorManager.SENSOR_DELAY_NORMAL, this);
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


    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case PORTRAIT:break;
            case REVERSED_PORTRAIT:


                CustomView.game8_CorrectlyAnswered++;

                check.setBackground(right);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(800, check);

                orientationManager.disable();
                if(intent.getExtras().get("flag").equals("true")){
                    i.putExtra("flag","true");
                }
                else{
                    i.putExtra("flag","false");
                }
                startActivity(i);
                break;
            case REVERSED_LANDSCAPE:

                break;
            case LANDSCAPE:
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.textView9) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

        }
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

        unbindDrawables(findViewById(R.id.bb_level10));
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
