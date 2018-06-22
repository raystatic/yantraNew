package com.university.yantra.GameBrainBooster;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.university.yantra.R;
import com.university.yantra.common.PrefManager;

public class GameOver extends AppCompatActivity {

    Button b, exit;
    Intent intent, i;
    Typeface t;

    TextView mes;

    PrefManager prefManager;

    public static final String GAME8_CORRECTLY_ANSWERED = "game8_CorrectlyAnswered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefManager = new PrefManager(this);
        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        b = (Button) findViewById(R.id.button14);
        exit = (Button) findViewById(R.id.button15);
        mes = (TextView) findViewById(R.id.textView12);

        b.setTypeface(t);
        exit.setTypeface(t);
        mes.setTypeface(t);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = new Intent(GameOver.this, MainActivity.class);

                if(intent.getExtras().get("flag").equals("true")){
                    Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                    i.putExtra("flag", "true");
                }
                else{
                    i.putExtra("flag", "false");
                }
                startActivity(i);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                if(intent.getExtras().get("flag").equals("true")){
                    //Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                    i = new Intent(GameOver.this, MainActivity.class);
                    i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(i);
                }
                else{
                    prefManager.saveString(GAME8_CORRECTLY_ANSWERED, CustomView.game8_CorrectlyAnswered + "");

                    i = new Intent(GameOver.this, GameBoosterOver.class);
                    startActivity(i);
                }

            }
        });

        //Toast.makeText(getApplicationContext(), "S" + CustomView.score, Toast.LENGTH_SHORT).show();

    }
}
