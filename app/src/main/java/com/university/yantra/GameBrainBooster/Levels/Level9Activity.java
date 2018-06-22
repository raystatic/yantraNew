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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.CustomView;
import com.university.yantra.R;

public class Level9Activity extends AppCompatActivity implements View.OnClickListener{

    CustomView c;
    ImageView check;

    EditText ans;
    Button b;

    TextView text;
    Intent intent, i;
    Typeface t;

    Drawable wrong, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level9);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        c = (CustomView) findViewById(R.id.custom);
        c.level=4;

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");

        intent = getIntent();

        i = new Intent(this, Level3Activity.class);

        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        ans = (EditText) findViewById(R.id.editText);
        b = (Button) findViewById(R.id.button26);
        text = (TextView) findViewById(R.id.textView2);

        b.setOnClickListener(this);

        text.setTypeface(t);
        ans.setTypeface(t);
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
        if (v.getId() == R.id.button26) {
            if (ans.getText().toString().equals("3")) {

                CustomView.game8_CorrectlyAnswered++;


                check.setBackground(right);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(500, check);

                if (intent.getExtras().get("flag").equals("true")) {
                    i.putExtra("flag", "true");
                } else {
                    i.putExtra("flag", "false");
                }
                startActivity(i);
            } else {

                life();
                check.setBackground(wrong);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(500, check);
            }
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

        unbindDrawables(findViewById(R.id.puzzle4));
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
