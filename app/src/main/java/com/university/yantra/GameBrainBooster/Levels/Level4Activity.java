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
import com.university.yantra.GameBrainBooster.OrientationManager;
import com.university.yantra.R;

public class Level4Activity extends AppCompatActivity implements OrientationManager.OrientationListener{

    Drawable wrong, right;

    ImageButton ketchup;

    ImageView check, sauce;
    Intent intent, i;
    CustomView c;

    Typeface t;

    TextView text;

    OrientationManager orientationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);

        intent = getIntent();

        i = new Intent(this, Level10Activity.class);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        sauce = (ImageView) findViewById(R.id.imageView5);

        ketchup = (ImageButton) findViewById(R.id.imageButton4) ;
        ketchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life();

                check.setBackground(wrong);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(800, check);





            }
        });

        c = (CustomView) findViewById(R.id.custom);
        c.level = 6;

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);



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
    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case PORTRAIT:break;
            case REVERSED_PORTRAIT:


                CustomView.game8_CorrectlyAnswered++;

                sauce.setVisibility(View.VISIBLE);

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
    protected void onStop() {
        super.onStop();

        orientationManager.disable();
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.puzzle5));
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
