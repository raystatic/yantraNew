package com.university.yantra.Fragments;

import android.content.Context;
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
import android.widget.VideoView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.university.yantra.Activities.FiltersActivity;
import com.university.yantra.Activities.MainActivity;
import com.university.yantra.Activities.WishlistActivity;
import com.university.yantra.R;
import com.university.yantra.Recommendation.AnalysisViewPager.presenters.TabFragment1Presenter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.models.Videos;
import com.university.yantra.presenters.VideosPresenter;
import com.university.yantra.videos.VideoAdapter;
import com.university.yantra.videos.YoutubeVideo;
import com.university.yantra.viewactions.VideosViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by rahul on 20/2/18.
 */

public class VideosFragment extends Fragment implements VideosViewAction {

    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    MyApplication context;
    Vector<YoutubeVideo> youtubeVideos=new Vector<YoutubeVideo>();
    ArrayList<String> videoUrls, names, age;
    FloatingActionMenu menuRed;
    VideosPresenter presenter;

    public VideosFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.videos_fragment_layout,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_videos);
        videoUrls=new ArrayList<>();
        names=new ArrayList<>();
        age=new ArrayList<>();

        menuRed=view.findViewById(R.id.menu_redVideos);

        context = new MyApplication();
        videoUrls.add("zjl0Y__e0H4");
        videoUrls.add("SMYMDX9YBMw");
        videoUrls.add("z6qlHaBlfvc");
        videoUrls.add("pMRO2dl9z3w");
        videoUrls.add("xcZG5sIqSHE");
        videoUrls.add("vWLcyFtni6U");
        videoUrls.add("BmYv8XGl-YU");
        videoUrls.add("D1R-jKKp3NA");
        videoUrls.add("MnrJzXM7a6o");
        videoUrls.add("9_YUbjQilQA");

        names.add("Stockport College Promotional Video");
        names.add("Christ University- PROMO 2016");
        names.add("Actual GD/RP/CS recording 16");
        names.add("Tell Me About Yourself");
        names.add("Google CEO Sundar Pichai Interview");
        names.add("Google CEO Sundar Pichai's I/O 2017");
        names.add("Facebook Founder's Commencement Address ");
        names.add("Steve Jobs Speech 2005");
        names.add("Steve Jobs Introduces iPhone in 2007");
        names.add("Mount Carmell College");

        age.add("5+");
        age.add("2+");
        age.add("7+");
        age.add("4+");
        age.add("2+");
        age.add("7+");
        age.add("6+");
        age.add("5+");
        age.add("4+");
        age.add("3+");

//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p_Ig9l4KnD8?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-TRHrwUBXlM?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/24Z2qrDMX6M?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/X4NIzKYjAxA?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XHG3kTW5yR8?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/u9kjhrIAGac?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VFVUBCYrzPg?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0LOpQjbbQDo?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d9Vmc-4ztJI?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));
//        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/htP3B1SE2rE?list=PLfDIvzq7WK3WdZoKmg2C6SE1HtjF_rZ4Y\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"));

        presenter = new VideosPresenter(new OnEntitiesReceivedListener<Videos>(getContext()) {
                    public void onReceived(List<Videos> entities){
                        setVideosRecyclerView(entities);
                    }
                },this);
        presenter.loadVideos(new HashMap<String, String>());
//        videoAdapter=new VideoAdapter(MainActivity.getInstance(),videoUrls,names,age);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(videoAdapter);
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


        return view;
    }
    public void setVideosRecyclerView(List<Videos> entities) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        DisplayMetrics displayMetrics=new DisplayMetrics();

        videoAdapter=new VideoAdapter(getContext(),entities, displayMetrics);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(layoutManager);
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    public static VideosFragment newInstance()
    {
        return new VideosFragment();
    }
}
