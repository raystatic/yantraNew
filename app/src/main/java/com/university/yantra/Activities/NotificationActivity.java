package com.university.yantra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.NotificationRecyclerViewAdapter;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.NotificationData;
import com.university.yantra.viewactions.BaseViewAction;
import com.university.yantra.viewactions.NotificationViewAction;

import java.util.ArrayList;

/**
 * Created by Rishabh on 3/2/2018.
 */

public class NotificationActivity extends BaseActivity implements BaseViewAction,NotificationViewAction{

    RecyclerView recyclerView;
    ArrayList<NotificationData> notificationDataArrayList;
    NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Notification");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_notif);

        notificationDataArrayList=new ArrayList<>();

        NotificationData notificationData1=new NotificationData();
        notificationData1.setCollege("Sharda University");
        notificationData1.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now.");
        notificationData1.setTime_numeric("18");
        notificationData1.setTime_text("hours");
        notificationData1.setTime_ago("ago");
        notificationData1.setApply_button_text("Apply Now");
        notificationData1.setImage("https://timesofindia.indiatimes.com/thumb/msid-59023402,width-400,resizemode-4/59023402.jpg");

        NotificationData notificationData2=new NotificationData();
        notificationData2.setCollege("University of Delhi");
        notificationData2.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now.");
        notificationData2.setTime_numeric("01");
        notificationData2.setTime_text("hour");
        notificationData2.setTime_ago("ago");
        notificationData2.setApply_button_text("Apply Now");
        notificationData2.setImage("https://timesofindia.indiatimes.com/photo/58944050.cms");

        NotificationData notificationData3=new NotificationData();
        notificationData3.setCollege("Banaras Hindu University");
        notificationData3.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now.");
        notificationData3.setTime_numeric("5");
        notificationData3.setTime_text("hours");
        notificationData3.setTime_ago("ago");
        notificationData3.setApply_button_text("Apply Now");
        notificationData3.setImage("https://images.static-collegedunia.com/public/college_data/images/campusimage/14980395041443100431BHU_NEW.jpg");

        NotificationData notificationData4=new NotificationData();
        notificationData4.setCollege("Niagara College");
        notificationData4.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now.");
        notificationData4.setTime_numeric("17");
        notificationData4.setTime_text("hours");
        notificationData4.setTime_ago("ago");
        notificationData4.setApply_button_text("Apply Now");
        notificationData4.setImage("https://28vi5c11qlvo3esjm01s1a4x-wpengine.netdna-ssl.com/wp-content/uploads/Welland-Frontview.png");

        NotificationData notificationData5=new NotificationData();
        notificationData5.setCollege("Imperial College London");
        notificationData5.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now");
        notificationData5.setTime_numeric("19");
        notificationData5.setTime_text("hours");
        notificationData5.setTime_ago("ago");
        notificationData5.setApply_button_text("Apply Now");
        notificationData5.setImage("https://www.rca.ac.uk/media/images/to_imperial.width-750.jpg");

        NotificationData notificationData6=new NotificationData();
        notificationData6.setCollege("IISC Bombay");
        notificationData6.setMessage("Applications for admission for 2018 - 2020 are now open. You can apply right now.");
        notificationData6.setTime_numeric("8");
        notificationData6.setTime_text("hours");
        notificationData6.setTime_ago("ago");
        notificationData6.setApply_button_text("Apply Now");
        notificationData6.setImage("https://i.ndtvimg.com/i/2016-09/iisc-bangalore_650x400_41473142498.jpg");

        notificationDataArrayList.add(notificationData6);
        notificationDataArrayList.add(notificationData5);
        notificationDataArrayList.add(notificationData4);
        notificationDataArrayList.add(notificationData3);
        notificationDataArrayList.add(notificationData2);
        notificationDataArrayList.add(notificationData1);

        setNotificationRecyclerView();

        Log.d("object",notificationDataArrayList.get(0).getCollege());
        Log.d("object",notificationDataArrayList.get(1).getCollege());
        Log.d("object",notificationDataArrayList.get(2).getCollege());
        Log.d("object",notificationDataArrayList.get(3).getCollege());
        Log.d("object",notificationDataArrayList.get(4).getCollege());
        Log.d("object",notificationDataArrayList.get(5).getCollege());

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public void showNetworkError(String message) {
        super.showNetworkError(message);
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
    public void setNotificationRecyclerView() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        notificationRecyclerViewAdapter=new NotificationRecyclerViewAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),notificationDataArrayList);
        recyclerView.setAdapter(notificationRecyclerViewAdapter);

    }
}
