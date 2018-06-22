package com.university.yantra.Recommendation;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.university.yantra.R;
import com.university.yantra.Recommendation.AnalysisViewPager.AnalysisPagerAdapter;

public class Top2 extends Fragment {

    View view;
    public ViewPager viewPager;
    public AnalysisPagerAdapter adapter;

    public Top2(){

    }

    public static Top2 newInstance()
    {
        Top2 fragment = new Top2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view =inflater.inflate(R.layout.analysis_report, container, false);
        final TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Moments"));
        tabLayout.addTab(tabLayout.newTab().setText("My Posts"));
        tabLayout.addTab(tabLayout.newTab().setText("Contests"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new AnalysisPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(),viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout .setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
    }
}