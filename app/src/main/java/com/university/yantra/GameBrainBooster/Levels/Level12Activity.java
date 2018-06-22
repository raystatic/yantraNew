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

public class Level12Activity extends AppCompatActivity implements View.OnClickListener{

    Drawable wrong, right;

    Button b1, b2, b3;

    ImageView check;
    Intent intent, i;
    CustomView c;

    Typeface t;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level12);


        intent = getIntent();

        i = new Intent(this, Level8Activity.class);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        c = (CustomView) findViewById(R.id.custom);
        c.level = 12;

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);

        b1= (Button) findViewById(R.id.button27);
        b2= (Button) findViewById(R.id.button28);
        b3= (Button) findViewById(R.id.button29);

        b1.setTypeface(t);
        b2.setTypeface(t);
        b3.setTypeface(t);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        text.setOnClickListener(this);

        /*String str = "";

        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(str, TextView.BufferType.SPANNABLE);

        Spannable ss = (Spannable) text.getText();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
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
        };
        ss.setSpan(clickableSpan, 16, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/

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



    /*public void textClick(View v) {
        check.setBackground(right);

        CustomView.game8_CorrectlyAnswered++;


        check.setVisibility(View.VISIBLE);
        timerDelayRemoveView(800, check);

        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
            Toast.makeText(this,"true",Toast.LENGTH_SHORT).show();
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView2) {
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

        else {
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

        unbindDrawables(findViewById(R.id.puzzle2));
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
