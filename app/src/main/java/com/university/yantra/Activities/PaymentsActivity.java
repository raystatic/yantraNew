package com.university.yantra.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;

import java.util.HashMap;
import java.util.Map;

public class PaymentsActivity extends BaseActivity {

    String Key;
    ImageView CartIcon;
    Map<String,String> map= new HashMap<>();
    TextView CODText;
    EditText Address;
    Dialog baseDialog;
    EditText Pincode;
    EditText Landmark;
    Float totalPrice;
    Boolean valid = false;
    Intent intent;
    Button pay;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pay = findViewById(R.id.payfor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Place Order");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instamojo.com/@universityexperts/"));
                startActivity(i);
            }
        });





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
