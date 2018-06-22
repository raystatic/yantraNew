package com.university.yantra.bottomfragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.university.yantra.Activities.AnalysisActivity;
import com.university.yantra.Fragments.EventsFragment;
import com.university.yantra.Fragments.ExploreFragment;
import com.university.yantra.Fragments.UniversitiesFragment;
import com.university.yantra.Fragments.VideosFragment;
import com.university.yantra.R;
import com.university.yantra.adapters.MainPagerAdapter;

/**
 * Created by Rishabh on 2/14/2018.
 */

public class ProfileFragment extends Fragment {
    View view;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private MainPagerAdapter adapter;
    private ProfileFragment.OnFragmentInteractionListener mListener;
    private TabLayout tabLayout;
    TextView start;

    public ProfileFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_bottom_1, container, false);
        toolbar= view.findViewById(R.id.toolbar);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        setUpViewPager(viewPager, tabLayout);

        return view;
    }
    public static ProfileFragment newInstance(int index) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragment.OnFragmentInteractionListener) {
            mListener = (ProfileFragment.OnFragmentInteractionListener) context;
            // mListener.hideToolbar();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void hideToolbar() ;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.view = null;
        mListener = null;
        viewPager = null;
        tabLayout = null;
    }

    public void setUpViewPager(final ViewPager viewPager, TabLayout tabLayout)
    {
        adapter = new MainPagerAdapter (getChildFragmentManager(), 4);
        adapter.addFragment(new UniversitiesFragment(), "Home");
        adapter.addFragment(new ExploreFragment(), "Explore");
        adapter.addFragment(new VideosFragment(), "Career");
        adapter.addFragment(new EventsFragment(), "Events");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {

//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){

            }
        });


    }


}

