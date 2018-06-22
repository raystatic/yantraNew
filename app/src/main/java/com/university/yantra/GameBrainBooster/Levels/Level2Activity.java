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

public class Level2Activity extends AppCompatActivity implements View.OnClickListener{

    CustomView c;
    ImageView check;
    ImageButton b1, b2, b3, b4;

    TextView text;
    Intent intent, i;
    Typeface t;

    Drawable wrong, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        c = (CustomView) findViewById(R.id.custom);
        c.level=3;

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");
        intent = getIntent();

        i = new Intent(this, Level9Activity.class);

        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);


        b1 = (ImageButton) findViewById(R.id.imageButton);
        b2 = (ImageButton) findViewById(R.id.imageButton1);
        b3 = (ImageButton) findViewById(R.id.imageButton2);
        b4 = (ImageButton) findViewById(R.id.imageButton3);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);


        text = (TextView) findViewById(R.id.textView3);
        text.setTypeface(t);

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

        if (v.getId() == R.id.imageButton) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(800, check);

        }

        if (v.getId() == R.id.imageButton1) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

        }

        if (v.getId() == R.id.imageButton2) {

            CustomView.game8_CorrectlyAnswered++;


            check.setBackground(right);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

            if(intent.getExtras().get("flag").equals("true")){
                i.putExtra("flag","true");
            }
            else{
                i.putExtra("flag","false");
            }
            startActivity(i);

        }

        if (v.getId() == R.id.imageButton3) {

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

        unbindDrawables(findViewById(R.id.bb_level2));
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
