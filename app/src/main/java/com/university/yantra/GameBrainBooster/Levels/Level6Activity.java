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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.CustomView;
import com.university.yantra.R;

public class Level6Activity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8;

    Drawable wrong, right;

    ImageView check;

    Typeface t;
    Intent intent, i;

    TextView text;

    CustomView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level6);

        intent = getIntent();

        i = new Intent(this, Level11Activity.class);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView2);

        b1 = (Button) findViewById(R.id.button6);
        b2 = (Button) findViewById(R.id.button7);
        b3 = (Button) findViewById(R.id.button8);
        b4 = (Button) findViewById(R.id.button9);
        b5 = (Button) findViewById(R.id.button10);
        b6 = (Button) findViewById(R.id.button11);
        b7 = (Button) findViewById(R.id.button12);
        b8 = (Button) findViewById(R.id.button13);

        text = (TextView) findViewById(R.id.textView2);
        c = (CustomView) findViewById(R.id.custom);

        c.level=9;

        text.setTypeface(t);
        b1.setTypeface(t);
        b2.setTypeface(t);
        b3.setTypeface(t);
        b4.setTypeface(t);
        b5.setTypeface(t);
        b6.setTypeface(t);
        b7.setTypeface(t);
        b8.setTypeface(t);
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


    public void Bclicked(View v) {
        life();
        check.setBackground(wrong);

        check.setVisibility(View.VISIBLE);
        timerDelayRemoveView(500, check);
    }

    public void Textclicked(View v){

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

        unbindDrawables(findViewById(R.id.bb_level6));
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
