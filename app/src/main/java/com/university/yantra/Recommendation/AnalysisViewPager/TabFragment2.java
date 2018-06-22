package com.university.yantra.Recommendation.AnalysisViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.university.yantra.Activities.LoginActivity;

import com.university.yantra.Activities.PaymentsActivity;
import com.university.yantra.R;
import com.university.yantra.Recommendation.BaseFragment;
import com.university.yantra.Recommendation.TabFragmentViewAction;
import com.university.yantra.Recommendation.Top2;
import com.university.yantra.common.ChildAnimationExample;
import com.university.yantra.common.PrefManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import link.fls.swipestack.SwipeStack;

public class TabFragment2 extends BaseFragment implements TabFragmentViewAction, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    PrefManager prefManager;
    private ArrayList<Drawable> mData;
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    ImageView happy_parent;
    int x=5;
    TabFragmentViewAction tabFragmentViewAction;
    CardView pay;
    ImageView b4;
    Bitmap bitmap;
    CardView cardx;
    View view;
    TextView payMoney;
    CardView testimonials;

    private SliderLayout mDemoSlider;

    @Override
    public int getLayoutResId() {
        return R.layout.tab_fragment_2;
    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefManager = new PrefManager(getActivity());
        this.view = view;
        mSwipeStack = (SwipeStack) view.findViewById(R.id.swipeStack);
        b4 = (ImageView) view.findViewById(R.id.b4);
        b4.setColorFilter(getResources().getColor(R.color.dark_purple));

        pay=view.findViewById(R.id.payButton);

        testimonials=view.findViewById(R.id.testcard);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        Double height=displayMetrics.heightPixels/(4.0);
//
//        testimonials.getLayoutParams().height=height.intValue();
//        testimonials.getLayoutParams().width=displayMetrics.widthPixels;

        payMoney = view.findViewById(R.id.pay_money);
        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaymentsActivity.class);
                intent.putExtra("price","1250");
                startActivity(intent);
            }
        });

//        pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMessage("clicked");
//                Intent intent = new Intent(getContext(), PaymentsActivity.class);
//                intent.putExtra("price","1250");
//                startActivity(intent);
//
//            }
//        });
        happy_parent = (ImageView) view.findViewById(R.id.hp);
        happy_parent.setColorFilter(getResources().getColor(R.color.white));

        final TextView textView=(TextView)view.findViewById(R.id.scrolly);
        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {

            @Override
            public void onViewSwipedToRight(int position) {
                //String swipedElement = mAdapter.getItem(position);
                // Toast.makeText(this, getString(R.string.view_swiped_right, swipedElement),
                //      Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewSwipedToLeft(int position) {
                //String swipedElement = mAdapter.getItem(position);
                // Toast.makeText(this, getString(R.string.view_swiped_left, swipedElement),
                //       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStackEmpty() {
                //Toast.makeText(this, R.string.stack_empty, Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.VISIBLE);

            }
        });

        fillWithTestData();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setUpTestimonialsSlider();

    }

    public static TabFragment2 create() {

        return new TabFragment2();
    }


    public void startLoginActivity(){
        Toast.makeText(getActivity(),"Login To Add Products To Cart",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
    }
    private void fillWithTestData() {


        mData.add(getResources().getDrawable(R.drawable.r1));
        mData.add(getResources().getDrawable(R.drawable.r2));
        mData.add(getResources().getDrawable(R.drawable.r3));
        mData.add(getResources().getDrawable(R.drawable.r4));
        mData.add(getResources().getDrawable(R.drawable.r5));
        mData.add(getResources().getDrawable(R.drawable.r6));
        mData.add(getResources().getDrawable(R.drawable.r7));
        mData.add(getResources().getDrawable(R.drawable.r8));
        mData.add(getResources().getDrawable(R.drawable.r9));
        mData.add(getResources().getDrawable(R.drawable.r10));

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

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


    public class SwipeStackAdapter extends BaseAdapter {

        private List<Drawable> mData;

        public SwipeStackAdapter(List<Drawable> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Drawable getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

                LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
                convertView = mInflater.inflate(R.layout.sliding_card_item, parent, false);
            }

            ImageView textViewCard = (ImageView) convertView.findViewById(R.id.preview);
            textViewCard.setImageDrawable(mData.get(position));



            return convertView;
        }
    }





    @Override
    public void startAnalysisNotDone() {


    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    void setUpTestimonialsSlider(){
        mDemoSlider = view.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Kashish Garg (6 Years)",R.drawable.testimonial_1);
        file_maps.put("Simran Arora (9 Years)" , R.drawable.testimonial_2);
        file_maps.put("Medhavi Singh (13 Years)",R.drawable.testimonial_3);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView
                    .getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setPresetTransformer("ZoomOut");
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
    }
}