package com.university.yantra.Recommendation.AnalysisViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;
import com.university.yantra.Activities.LoginActivity;
import com.university.yantra.Activities.PaymentsActivity;
import com.university.yantra.R;
import com.university.yantra.Recommendation.BaseFragment;
import com.university.yantra.Recommendation.TabFragmentViewAction;
import com.university.yantra.common.ChildAnimationExample;
import com.university.yantra.common.PrefManager;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TabFragment3 extends BaseFragment implements ViewPagerEx.OnPageChangeListener,TabFragmentViewAction {

    PrefManager prefManager;
    private SliderLayout mDemoSlider;
     SwipeSelector sizeSelector;
    ImageView research,happy;
    CardView pay;
    Bitmap bitmap;
    ImageView startLiveChatButton;

    TextView payMoney;
    @Override
    public int getLayoutResId() {
        return R.layout.tab_fragment_1;
    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefManager = new PrefManager(getActivity());

        pay = view.findViewById(R.id.payButton);

        sizeSelector = (SwipeSelector) view.findViewById(R.id.sizeSelector);

        research=(ImageView)view.findViewById(R.id.expertise);
        research.setColorFilter(getResources().getColor(R.color.dark_purple));

        happy=(ImageView)view.findViewById(R.id.happy);
        happy.setColorFilter(getResources().getColor(R.color.white));

        payMoney = view.findViewById(R.id.pay_money);
        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaymentsActivity.class);
                intent.putExtra("price","1250");
                startActivity(intent);
            }
        });

        sizeSelector.setItems(
                new SwipeItem(0,"Launch Child Career","Pay Premium and Start Journey of your Child."),
                new SwipeItem(1,"Start Live Sessions with Expert","Our Expert would reach out to you on login email-id within 24 hours to setup 6 video sessions for the course of next 3 months."),
                new SwipeItem(2,"Primary Sessions: 1-2","Our Expert would analyze child’s current skills and career report and discuss areas of improvement and child’s career objective with you." ),
                new SwipeItem(3,"Focused Sessions: 3-4","Based on previous sessions, expert would suggest resources (Videos, Books, Games, Toys) in order to improve child’s targeted skills. Also, he would coin idea of “Unique game(s)” that would be customized as per child’s need."),
                new SwipeItem(4,"Closing Sessions","Joint discussions in terms of improvements and hand-over of game and resources. Closure of sessions with mutual agreement (Re-new or End of sessions) on next steps. ")
        );

        startLiveChatButton = view.findViewById(R.id.startLiveChatButton);
        startLiveChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWhatsapp();
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static TabFragment3 create() {

        return new TabFragment3();
    }


    public void startLoginActivity(){
        Toast.makeText(getActivity(),"Login To Add Products To Cart",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    public Bitmap getThumbnail(String filename) {

        Bitmap thumbnail = null;

// If no file on external storage, look in internal storage
        try {
            File filePath = getActivity().getFileStreamPath(filename);
            FileInputStream fi = new FileInputStream(filePath);
            thumbnail = BitmapFactory.decodeStream(fi);
            return thumbnail;
        } catch (Exception ex) {
            Log.e("getThumbnail() internal", ex.getMessage());
            return null;
        }
    }


    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void startAnalysisNotDone() {

    }

    public void startWhatsapp(){
        PackageManager packageManager = getContext().getPackageManager();
        String phone = "919818787752";
        String message = "Hi! I want to start a Live Chat session that you provide under Yantra. I've paid for the session.";
        Intent i = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                getContext().startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
