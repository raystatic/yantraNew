package com.university.yantra.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.university.yantra.Activities.FiltersActivity;
import com.university.yantra.Activities.WishlistActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.ExploreAdapter;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.Tag;
import com.university.yantra.models.Videos;
import com.university.yantra.presenters.ExplorePresenter;
import com.university.yantra.presenters.VideosPresenter;
import com.university.yantra.viewactions.ExploreViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rishabh on 2/22/2018.
 */

public class ExploreFragment extends Fragment implements ViewPagerEx.OnPageChangeListener, ExploreViewAction {
    View view;
    ExploreAdapter adapter;
    ExplorePresenter presenter;
    RecyclerView recyclerView;
    DisplayMetrics displayMetrics;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> tags, views;
    FloatingActionMenu menuRed;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_explore, container, false);
        this.view = view;
        recyclerView = view.findViewById(R.id.explore_recycler_view);
        menuRed=view.findViewById(R.id.menu_redExplore);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        tags=new ArrayList<>();
        views=new ArrayList<>();

        urls.add("http://www.studycrow.com/articles/wp-content/uploads/2015/08/StudyCrow_College.jpg");
        urls.add("http://college4u.in/wp-content/uploads/2017/02/sharda1-830x307.jpg");
        urls.add("http://www.srmuniv.ac.in/sites/all/themes/srmuniversity/main_layout/images/menu-img2.jpg");
        urls.add("https://www.timeshighereducation.com/sites/default/files/styles/the_breaking_news_image_style/public/stanford-university-best-universities-in-the-united-states-2016.jpg");
        urls.add("https://www.higheredjobs.com/images/articles/article_715_4.jpg");
        urls.add("https://www.omm.com/~/media/images/site/services/industries/colleges_and_universities_780x520px.ashx");
        urls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/20130612_Budapest_137.jpg/300px-20130612_Budapest_137.jpg");
        urls.add("https://static01.nyt.com/images/2017/12/27/us/00enrollment-01/merlin_115680671_46d4fc1a-e455-430d-a107-f7d06d2e1a81-master768.jpg");
        urls.add("https://hackerrankblog-aaa3.kxcdn.com//wp-content/uploads/2016/11/photo-1470378639897-89788e74b7bf-1.jpeg");
        urls.add("https://thebestschools.org/wp-content/uploads/2014/02/NH-top-U.jpg");

        tags.add("BEST FOR PERSONA IMPROVEMENT");
        tags.add("WORLD CLASS FACILITY");
        tags.add("BEST RATED CAMPUS");
        tags.add("TOUGH TO CRACK");
        tags.add("TOP RATED");
        tags.add("BEST MALE FEMALE PROPORTION");
        tags.add("TRENDING");
        tags.add("BEST PLACEMENTS");
        tags.add("BEST ROI");
        tags.add("WITH BEST FACILITIES");

        views.add("158");
        views.add("253");
        views.add("276");
        views.add("485");
        views.add("350");
        views.add("422");
        views.add("195");
        views.add("328");
        views.add("865");
        views.add("151");

        menuRed.setMenuButtonColorNormal(getResources().getColor(R.color.lightpink));
        menuRed.setMenuButtonColorPressed(getResources().getColor(R.color.lightpink));

        final FloatingActionButton programFab1 = new FloatingActionButton(getContext());
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Filters");
        programFab1.setImageResource(R.drawable.ic_action_edit2);
        menuRed.addMenuButton(programFab1);
        programFab1.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab1.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), FiltersActivity.class));

                menuRed.close(true);

            }
        });
        final FloatingActionButton programFab2 = new FloatingActionButton(getContext());
        programFab2.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab2.setLabelText("Wishlist");
        programFab2.setImageResource(R.drawable.ic_action_heart2);
        menuRed.addMenuButton(programFab2);
        programFab2.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab2.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab2.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab2.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), WishlistActivity.class));

                menuRed.close(true);

            }
        });

        presenter = new ExplorePresenter(new OnEntitiesReceivedListener<Tag>(getContext()) {
            public void onReceived(List<Tag> entities){
                setExploreRecyclerView(entities);
            }
        },this);
        presenter.loadTags(new HashMap<String, String>());
        return view;
    }

    public void setExploreRecyclerView(List<Tag> entities){

        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(getContext()); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new ExploreAdapter(getContext(),entities,displayMetrics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
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
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }
}
