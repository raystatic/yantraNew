package com.university.yantra.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.Recommendation.Top1;
import com.university.yantra.Recommendation.Top2;
import com.university.yantra.viewactions.ReccommViewAction;

public class Recommendations extends BaseActivity implements ReccommViewAction {

    FragmentTransaction transaction;
    Switch s1;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.togglebutton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.filter_blue)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Recommendations");

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.My_Container_1_ID, new Top1(), "Frag_Top_tag");
        transaction.commit();
        s1=(Switch)findViewById(R.id.switchButton);

        int c = getIntent().getIntExtra("key",0);

        Log.e("key","key="+c);
        if(c==1)
        {
            s1.setChecked(true);
            transaction=manager.beginTransaction();
            transaction.add(R.id.My_Container_1_ID, new Top2());
            transaction.commit();

        }

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked==false)
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.My_Container_1_ID, new Top1());
                    transaction.commit();
                }
                else
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.My_Container_1_ID, new Top2());
                    transaction.commit();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Recommendations.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Recommendations.this, MainActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        transaction = null;
        manager = null;


    }

    @Override
    public void showNetworkTimeoutError() {

    }

    @Override
    public void showNoNetworkException() {

    }
}