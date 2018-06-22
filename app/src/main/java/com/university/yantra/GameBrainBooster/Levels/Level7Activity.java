package com.university.yantra.GameBrainBooster.Levels;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import com.university.yantra.GameBrainBooster.ShakeListener;
import com.university.yantra.R;

public class Level7Activity extends AppCompatActivity {

    Drawable wrong, right;
    Intent intent;

    ImageView check, mango, tree;

    Typeface t;

    TextView text;

    CustomView c;

    int x, y;


    ShakeListener mShaker;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        x = getResources().getDisplayMetrics().widthPixels;
        y = getResources().getDisplayMetrics().heightPixels;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level7);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        i = new Intent(getApplicationContext(), Level12Activity.class);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView2);



        text = (TextView) findViewById(R.id.textView2);
        mango = (ImageView) findViewById(R.id.imageView6);
        tree = (ImageView) findViewById(R.id.imageView4);

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life();
                check.setBackground(wrong);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(500, check);
            }
        });

        c = (CustomView) findViewById(R.id.custom);

        c.level=11;

        text.setTypeface(t);


        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                CustomView.game8_CorrectlyAnswered++;

                mShaker.pause();
                loadmainAniamtion();

                check.setBackground(right);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(900, check);


                if(intent.getExtras().get("flag").equals("true")){
                    i.putExtra("flag","true");
                }
                else{
                    i.putExtra("flag","false");
                }
                startActivity(i);
            }
        });

    }

    public void onSkip(View v) {
        mShaker.pause();
        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }





    public void loadmainAniamtion() {


        ObjectAnimator textAnimator;

        textAnimator = ObjectAnimator.ofFloat(mango, "y", y/2, y-y/5);
        textAnimator.setDuration(1200L);



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

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level7));
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
