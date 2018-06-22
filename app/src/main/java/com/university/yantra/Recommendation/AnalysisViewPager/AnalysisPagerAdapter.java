package com.university.yantra.Recommendation.AnalysisViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paras on 7/19/2017.
 */

public class AnalysisPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String[] tabText = {"Analysis Report","Career Report","Launch Career"};
    Map<String,Fragment> mPageReferenceMap = new HashMap<>();
    ViewPager viewPager;

    public  AnalysisPagerAdapter(FragmentManager fm, int NumOfTabs, ViewPager viewPager) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.viewPager = viewPager;
    }

    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;

    }

    @Override
    public String getPageTitle(int position) {
        return tabText[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TabFragment1.create();
            case 1:
               return TabFragment2.create();
            case 2:

                return TabFragment3.create();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
