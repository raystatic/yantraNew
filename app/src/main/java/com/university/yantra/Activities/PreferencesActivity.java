package com.university.yantra.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;

public class PreferencesActivity extends BaseActivity {

    Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        getStarted=findViewById(R.id.btnGetStarted);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void showNetworkError(String message) {
        super.showNetworkError(message);
    }
    public void startLoginActivity(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
