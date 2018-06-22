package com.university.yantra.GameBrainBooster.Levels;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.CustomView;
import com.university.yantra.R;

public class Level3Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton i1, i2, i3;
    ImageView check;
    Intent intent, i;
    Drawable wrong, right;

    TextView text, t1, t2, t3;

    Typeface t;
    CustomView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        intent = getIntent();

        i = new Intent(this, Level4Activity.class);

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        c = (CustomView) findViewById(R.id.custom);
        c.level = 5;

        i1 = (ImageButton) findViewById(R.id.imageButton4);
        i2 = (ImageButton) findViewById(R.id.imageButton5);
        i3 = (ImageButton) findViewById(R.id.imageButton6);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);

        text = (TextView) findViewById(R.id.textView2);
        t1 = (TextView) findViewById(R.id.textView5);
        t2 = (TextView) findViewById(R.id.textView6);
        t3 = (TextView) findViewById(R.id.textView7);

        text.setTypeface(t);
        t1.setTypeface(t);
        t2.setTypeface(t);
        t3.setTypeface(t);

    }
    public void onSkip(View v) {
        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imageButton4) {

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

        if (v.getId() == R.id.imageButton5) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

        }

        if (v.getId() == R.id.imageButton6) {

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

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level3));
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
