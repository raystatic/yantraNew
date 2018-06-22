package com.university.yantra.HelpDesk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;

public class HelpDeskActivity extends BaseActivity {

    ExpandableRelativeLayout expandableLinearLayout, expandableRelativeLayout_other, expandableRelativeLayout_faq;
    ImageButton imageButton_rightarrow, imageButton_rightarrow_other, imageButton_orderPLacements, imageButton_payments;
    ImageButton imageButton_shipping, imageButton_myAccount, imageButton_cancellations;
    ImageButton imageButton_faq, imageButton_terms, imageButton_privacy, imageButton_refund;
    CardView cardView_cancel, cardView_other_help, cardView_orders, cardView_faq1,cardView_faq2,cardView_faq3,cardView_faq4;
    CardView card_pay_help,card_ship_help,card_cancellation_help, card_faq_help, card_email_help;
    EditText editText_name_cancel, editText_email_cancel,editText_orderno_cancel, editText_desc_cancel;
    Button button_submit_cancel;
    EditText editText_name_other, editText_email_other,editText_orderno_other, editText_desc_other;
    Button button_submit_other;

    LinearLayout rootHelpLinearLayout;
    RelativeLayout contactRelative;

    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);

        rootHelpLinearLayout=findViewById(R.id.help_linear_layout);
        contactRelative=findViewById(R.id.contact_rel);

        displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        rootHelpLinearLayout.getLayoutParams().height = height.intValue();
//        rootHelpLinearLayout.getLayoutParams().width = displayMetrics.widthPixels;

        editText_name_cancel=(EditText)findViewById(R.id.et_name_cancel_help);
        editText_email_cancel=(EditText)findViewById(R.id.et_email_cancel_help);
        editText_desc_cancel=(EditText)findViewById(R.id.et_desc_cancel_help);
        editText_orderno_cancel=(EditText)findViewById(R.id.et_orderNumber_cancel_help);

        button_submit_cancel=(Button)findViewById(R.id.btn_submit_cancel_help);

        editText_name_other=(EditText)findViewById(R.id.et_name_other_help);
        editText_email_other=(EditText)findViewById(R.id.et_email_other_help);
        editText_desc_other=(EditText)findViewById(R.id.et_desc_other_help);
        editText_orderno_other =(EditText)findViewById(R.id.et_orderNumber_other_help);

        button_submit_other=(Button)findViewById(R.id.btn_submit_other_help);

        expandableLinearLayout=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_cancel_help);
        imageButton_rightarrow=(ImageButton)findViewById(R.id.imgbtn_rightarraow_help);
        cardView_cancel=(CardView)findViewById(R.id.card_cancel_help);
        cardView_cancel.setVisibility(View.VISIBLE);
        expandableRelativeLayout_other=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_other_help);
        imageButton_rightarrow_other=(ImageButton)findViewById(R.id.imgbtn_rightarraow_other_help);
        imageButton_orderPLacements=(ImageButton)findViewById(R.id.imgbtn_rightarraow_orderplacements_help);
        imageButton_payments=(ImageButton)findViewById(R.id.imgbtn_rightarraow_payments_help);
        imageButton_shipping=(ImageButton)findViewById(R.id.imgbtn_rightarraow_shipping_help);
        //imageButton_myAccount=(ImageButton)findViewById(R.id.imgbtn_rightarraow_myAccount_help);
        imageButton_cancellations=(ImageButton)findViewById(R.id.imgbtn_rightarraow_cancellations_help);
        imageButton_faq=(ImageButton)findViewById(R.id.imgbtn_rightarraow_faq_help);
        //imageButton_refund=(ImageButton)findViewById(R.id.imgbtn_rightarraow_refund_help);
        expandableRelativeLayout_faq=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_faq_help);
        cardView_other_help=(CardView)findViewById(R.id.card_other_help);

        //  card_cancel_help=(CardView)findViewById(R.id.card_cancel_help);

        cardView_orders=(CardView)findViewById(R.id.card_orderplacements_help);

        cardView_faq1=(CardView)findViewById(R.id.card1_faq_help);
        cardView_faq2=(CardView)findViewById(R.id.card2_faq_help);
        cardView_faq3=(CardView)findViewById(R.id.card3_faq_help);
        cardView_faq4=(CardView)findViewById(R.id.card4_faq_help);
        card_email_help=(CardView)findViewById(R.id.card_email_help);

        card_pay_help=(CardView)findViewById(R.id.card_payments_help);
        card_ship_help=(CardView)findViewById(R.id.card_shipping_help);
        card_cancellation_help=(CardView)findViewById(R.id.card_cancellations_help);
        card_faq_help=(CardView)findViewById(R.id.card_faq_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Help Desk");

        expandableLinearLayout.collapse();
        expandableRelativeLayout_other.collapse();
        expandableRelativeLayout_faq.collapse();

        //cardView_cancel.setVisibility(View.GONE);

        cardView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLinearLayout.isExpanded())
                {
                    imageButton_rightarrow.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else
                {
                    imageButton_rightarrow.setImageResource(R.drawable.ic_action_uparraow);
                }

                expandableLinearLayout.toggle();
                expandableLinearLayout.expand();
                expandableLinearLayout.moveChild(0);
                expandableLinearLayout.move(100);
                expandableLinearLayout.setClosePosition(0);
            }
        });
        imageButton_rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLinearLayout.isExpanded())
                {
                    imageButton_rightarrow.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else
                {
                    imageButton_rightarrow.setImageResource(R.drawable.ic_action_uparraow);
                }

                expandableLinearLayout.toggle();
                expandableLinearLayout.expand();
                expandableLinearLayout.moveChild(0);
                expandableLinearLayout.move(100);
                expandableLinearLayout.setClosePosition(0);
            }
        });

        RelativeLayout relativeLayout_help=(RelativeLayout)findViewById(R.id.rel_helpdesk);

        //relativeLayout_help.setVisibility(View.GONE);

        cardView_other_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expandableRelativeLayout_other.isExpanded())
                {
                    imageButton_rightarrow_other.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else
                {
                    imageButton_rightarrow_other.setImageResource(R.drawable.ic_action_uparraow);
                }
                expandableRelativeLayout_other.toggle();
                expandableRelativeLayout_other.expand();
                expandableRelativeLayout_other.moveChild(0);
                expandableRelativeLayout_other.move(100);
                expandableRelativeLayout_other.setClosePosition(0);
            }
        });

        imageButton_rightarrow_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expandableRelativeLayout_other.isExpanded())
                {
                    imageButton_rightarrow_other.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else
                {
                    imageButton_rightarrow_other.setImageResource(R.drawable.ic_action_uparraow);
                }
                expandableRelativeLayout_other.toggle();
                expandableRelativeLayout_other.expand();
                expandableRelativeLayout_other.moveChild(0);
                expandableRelativeLayout_other.move(100);
                expandableRelativeLayout_other.setClosePosition(0);
            }
        });


//        cardView_orders.setVisibility(View.GONE);
//        card_pay_help.setVisibility(View.GONE);
//        card_ship_help.setVisibility(View.GONE);
//        card_cancellation_help.setVisibility(View.GONE);
//        card_faq_help.setVisibility(View.GONE);



//        cardView_orders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), OrderPlacementsActivity.class));
//            }
//        });
//
//        card_pay_help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), OrderPaymentsActivity.class));
//            }
//        });
//        card_ship_help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), OrderShippingActivity.class));
//            }
//        });
//////        imageButton_myAccount.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                startActivity(new Intent(getApplicationContext(), OrderMyAccountActivity.class));
//////            }
//////        });
//        card_cancellation_help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), OrderCancellationActivity.class));
//            }
//        });
        card_faq_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expandableRelativeLayout_faq.isExpanded())
                {
                    imageButton_faq.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else {
                    imageButton_faq.setImageResource(R.drawable.ic_action_uparraow);
                }
                expandableRelativeLayout_faq.toggle();
                expandableRelativeLayout_faq.expand();
                expandableRelativeLayout_faq.moveChild(0);
                expandableRelativeLayout_faq.move(100);
                expandableRelativeLayout_faq.setClosePosition(0);
            }
        });
        imageButton_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (expandableRelativeLayout_faq.isExpanded())
                {
                    imageButton_faq.setImageResource(R.drawable.ic_action_rightarrow);
                }
                else {
                    imageButton_faq.setImageResource(R.drawable.ic_action_uparraow);
                }
                expandableRelativeLayout_faq.toggle();
                expandableRelativeLayout_faq.expand();
                expandableRelativeLayout_faq.moveChild(0);
                expandableRelativeLayout_faq.move(100);
                expandableRelativeLayout_faq.setClosePosition(0);
            }
        });
//////        imageButton_refund.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                startActivity(new Intent(getApplicationContext(), RefundsActivity.class));
//////            }
//////        });
//        cardView_faq1.setVisibility(View.GONE);
//        cardView_faq2.setVisibility(View.GONE);
//        cardView_faq4.setVisibility(View.GONE);
////
//        cardView_faq1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), RewardProgram.class));
//            }
//        });
//        cardView_faq2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ReferAFriend.class));
//            }
//        });
//        cardView_faq3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), AuthencityActivity.class));
//            }
//        });
//
//        cardView_faq4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), AffilateActivity.class));
//            }
//        });
        card_email_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent emailIntent=new Intent(Intent.ACTION_SEND);
//                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,"funcandiexperts@gmail.com");
//                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Issues faced in Funcandi");
//                emailIntent.setType("text/plain");
//                startActivity(emailIntent);

//                Intent mailClient = new Intent(Intent.ACTION_VIEW);
//                mailClient.setClassName("com.geniefusion.genie.funcandi.activity", "com.geniefusion.genie.funcandi.activity.HelpDeskActivity");
//                startActivity(mailClient);
                // startApplication("com.google.android.gm");

                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "universityexperts85@gmail.com"));
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "Issues with funcandi");
//                    intent.putExtra(Intent.EXTRA_TEXT, "Issues resolved");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });

        button_submit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,orderNumber,desc,text;
                name=editText_name_cancel.getText().toString();
                email=editText_email_cancel.getText().toString();
                orderNumber=editText_orderno_cancel.getText().toString();
                desc=editText_desc_cancel.getText().toString();
                text="Name: "+name+"\nEmail ID: "+email+"\nOrder Number: "+orderNumber+"\nDescription: "+desc+"";
                if (name.isEmpty() || email.isEmpty() || orderNumber.isEmpty() || desc.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Each detail is madatory to fill",Toast.LENGTH_LONG).show();
                }
                else
                {
                    try{
                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "funcandiexperts@gmail.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Cancellation/return issue");
                        intent.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(intent);
                    }catch(ActivityNotFoundException e){
                        //TODO smth
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    editText_name_cancel.setText("");
                    editText_email_cancel.setText("");
                    editText_orderno_cancel.setText("");
                    editText_desc_cancel.setText("");

                }
            }
        });

        button_submit_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,orderNumber,desc,text;
                name=editText_name_other.getText().toString();
                email=editText_email_other.getText().toString();
                orderNumber=editText_orderno_other.getText().toString();
                desc=editText_desc_other.getText().toString();
                text="Name: "+name+"\nEmail ID: "+email+"\nOrder Number: "+orderNumber+"\nDescription: "+desc+"";
                if (name.isEmpty() || email.isEmpty() || orderNumber.isEmpty() || desc.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Each detail is mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else
                {
                    try{
                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "universityexperts85@gmail.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Query related to Yantra");
                        intent.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(intent);
                    }catch(ActivityNotFoundException e){
                        //TODO smth
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    editText_name_other.setText("");
                    editText_email_other.setText("");
                    editText_orderno_other.setText("");
                    editText_desc_other.setText("");

                }

            }
        });

//        Double height = displayMetrics.heightPixels/(4.0);
//
//        contactRelative.getLayoutParams().height = height.intValue();
//        contactRelative.getLayoutParams().width = displayMetrics.widthPixels;


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

}
