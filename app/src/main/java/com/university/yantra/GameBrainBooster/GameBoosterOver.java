package com.university.yantra.GameBrainBooster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.university.yantra.Activities.*;
import com.university.yantra.R;

public class GameBoosterOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_booster_over);

        Button b = (Button) findViewById(R.id.start_next_game);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameBoosterOver.this, AnalysisActivity.class);
                i.putExtra("state","1");
                startActivity(i);
                finish();
            }
        });

    }
}
