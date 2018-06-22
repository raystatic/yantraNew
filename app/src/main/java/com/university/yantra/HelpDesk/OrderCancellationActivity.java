package com.university.yantra.HelpDesk;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;

public class OrderCancellationActivity extends BaseActivity {

    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7;
    ExpandableRelativeLayout exp1,exp2,exp3,exp4,exp5,exp6,exp7;
    ImageView img1,img2,img3,img4,img5,img6,img7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cancellation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Order Placements");

        cardView1=(CardView)findViewById(R.id.card1_orderplacement);
        cardView2=(CardView)findViewById(R.id.card2_orderplacement);
        cardView3=(CardView)findViewById(R.id.card3_orderplacement);
        cardView4=(CardView)findViewById(R.id.card4_orderplacement);
        cardView5=(CardView)findViewById(R.id.card5_orderplacement);
        cardView6=(CardView)findViewById(R.id.card6_orderplacement);
        cardView7=(CardView)findViewById(R.id.card7_orderplacement);

        img1=(ImageView)findViewById(R.id.img1_orderplacement);
        img2=(ImageView)findViewById(R.id.img2_orderplacement);
        img3=(ImageView)findViewById(R.id.img4_orderplacement);
        img4=(ImageView)findViewById(R.id.img5_orderplacement);
        img5=(ImageView)findViewById(R.id.img6_orderplacement);
        img6=(ImageView)findViewById(R.id.img7_orderplacement);
        img7=(ImageView)findViewById(R.id.img8_orderplacement);

        exp1=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order1_help);
        exp2=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order2_help);
        exp3=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order3_help);
        exp4=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order4_help);
        exp5=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order5_help);
        exp6=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order6_help);
        exp7=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_order7_help);

        exp1.collapse();
        exp2.collapse();
        exp3.collapse();
        exp4.collapse();
        exp5.collapse();
        exp6.collapse();
        exp7.collapse();

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp1.isExpanded())
                {
                    img1.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img1.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp1.toggle();
                exp1.expand();
                exp1.moveChild(0);
                exp1.move(100);
                exp1.setClosePosition(0);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp2.isExpanded())
                {
                    img2.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img2.setImageResource(R.drawable.ic_action_uparraow);
                }
                exp2.toggle();
                exp2.expand();
                exp2.moveChild(0);
                exp2.move(100);
                exp2.setClosePosition(0);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp3.isExpanded())
                {
                    img3.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img3.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp3.toggle();
                exp3.expand();
                exp3.moveChild(0);
                exp3.move(100);
                exp3.setClosePosition(0);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp4.isExpanded())
                {
                    img4.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img4.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp4.toggle();
                exp4.expand();
                exp4.moveChild(0);
                exp4.move(100);
                exp4.setClosePosition(0);
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp5.isExpanded())
                {
                    img5.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img5.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp5.toggle();
                exp5.expand();
                exp5.moveChild(0);
                exp5.move(100);
                exp5.setClosePosition(0);
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp6.isExpanded())
                {
                    img6.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img6.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp6.toggle();
                exp6.expand();
                exp6.moveChild(0);
                exp6.move(100);
                exp6.setClosePosition(0);
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp7.isExpanded())
                {
                    img7.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img7.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp7.toggle();
                exp7.expand();
                exp7.moveChild(0);
                exp7.move(100);
                exp7.setClosePosition(0);
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
