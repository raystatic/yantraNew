package com.university.yantra.GameBrainBooster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.university.yantra.GameBrainBooster.Levels.Level1Activity;
import com.university.yantra.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Typeface t;
    TextView title, sbt;
    Intent intent;
    Button b;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        deleteCache(this);

        intent = getIntent();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CustomView c=new CustomView(this);
        c.life=3;
        CustomView.game8_CorrectlyAnswered=0;

        i = new Intent(this, Level1Activity.class);
        if(intent.getExtras().get("flag")==("true")){
            i.putExtra("flag","true");
            CustomView.analysis = false;
        }
        else{
            CustomView.analysis = true;
            i.putExtra("flag","false");
        }

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");

        title = (TextView) findViewById(R.id.textView);
        sbt = (TextView) findViewById(R.id.textView4);
        b = (Button) findViewById(R.id.button);

        title.setTypeface(t);
        sbt.setTypeface(t);

        b.setTypeface(t);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();

            deleteDir(dir);
        }catch (Exception e){

        }
    }


    public  static boolean deleteDir(File dir) {
        if((dir != null) && dir.isDirectory()) {
            String[] c=  dir.list();

            for (int i=0; i < c.length; ++i) {
                boolean success = deleteDir(new File(dir, c[i]));

                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!=null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bbmain));
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
