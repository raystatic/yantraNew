package com.university.yantra.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.university.yantra.Activities.AnalysisActivity;
import com.university.yantra.Activities.AptitudeActivity;
import com.university.yantra.Activities.VideosActivity;
import com.university.yantra.R;
import com.university.yantra.ViewMore.ShortTermCoursesActivity;
import com.university.yantra.ViewMore.TrendingCoursesActivity;
import com.university.yantra.adapters.AptitudeAdapter3;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.bottomfragments.FragmentTabThree;
import com.university.yantra.common.PrefManager;
import com.university.yantra.listeners.OnUserReceivedListener;
import com.university.yantra.models.Course;
import com.university.yantra.models.Library;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.User;
import com.university.yantra.presenters.CurrentUserPresenter;
import com.university.yantra.presenters.ShortTermCoursesPresesnter;
import com.university.yantra.presenters.TrendingCoursesPresenter;
import com.university.yantra.repositories.ShortTermCoursesRepository;
import com.university.yantra.repositories.TrendingCoursesRepository;
import com.university.yantra.viewactions.ShortTermCoursesViewAction;
import com.university.yantra.viewactions.TrendingCoursesViewAction;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AptitudeFragment extends Fragment implements TrendingCoursesViewAction,OnUserReceivedListener, ShortTermCoursesViewAction {

    RecyclerView recyclerView, recyclerView2, recyclerView3;

    ArrayList<String> texts;
    TrendingCoursesPresenter presenter;
    ShortTermCoursesPresesnter presesnter2;
    Switch aSwitch;
    TextView personalized, library;
    TextView start;
    CurrentUserPresenter userPresenter;
    TextView user;
    TextView viewMoreSt, viewMoreTrending;
    RecyclerView recyclerViewSt, recyclerViewTrending;
    RelativeLayout relative1,relative2,relative3,topLayout,startlayout;
    CardView cardView1;
    ShortTermAdapter adapter;
    RecommendedCourseAdapter adapter2;
    DisplayMetrics displayMetrics;


    public AptitudeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_aptitude,container,false);

        startlayout = view.findViewById(R.id.startLayout);
        topLayout = view.findViewById(R.id.top_layout);
        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        topLayout.getLayoutParams().height = displayMetrics.heightPixels/3;
        topLayout.getLayoutParams().width = displayMetrics.widthPixels;

        startlayout.getLayoutParams().height = displayMetrics.heightPixels/8;
        startlayout.getLayoutParams().width = displayMetrics.widthPixels;
        aSwitch=view.findViewById(R.id.switchApt);
        recyclerView3=view.findViewById(R.id.recyclerviewApt3);
        start = (TextView)view.findViewById(R.id.tvStartApt);
        user = (TextView)view.findViewById(R.id.user);
        personalized=view.findViewById(R.id.tvPersonlized);
        library=view.findViewById(R.id.tvLibrary);
        recyclerViewSt=view.findViewById(R.id.stRecyclerViewApt);
        recyclerViewTrending=view.findViewById(R.id.coursesRecyclerViewApt);
        cardView1=view.findViewById(R.id.cardApt1);
        viewMoreSt=view.findViewById(R.id.tvViewMoreCorsesApt);
        viewMoreTrending=view.findViewById(R.id.tvViewMoreTrendingCoursesApt);

        userPresenter = new CurrentUserPresenter(this,this);
        userPresenter.getCurrentUser();
        relative1=view.findViewById(R.id.relativeApt1);
        relative2=view.findViewById(R.id.relativeApt2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Notification");


        presenter=new TrendingCoursesPresenter(new TrendingCoursesRepository(),getActivity().getApplicationContext(),this);
        presesnter2=new ShortTermCoursesPresesnter(this,getActivity().getApplicationContext(),new ShortTermCoursesRepository());

//        cardView1.setVisibility(View.GONE);
////        cardView2.setVisibility(View.GONE);
////        cardView3.setVisibility(View.GONE);
//        relative1.setVisibility(View.GONE);
//        relative2.setVisibility(View.GONE);


        viewMoreSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), ShortTermCoursesActivity.class));
            }
        });

        viewMoreTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), TrendingCoursesActivity.class));
            }
        });

        recyclerView3.setVisibility(View.VISIBLE);

        presenter.getCourses();
        presesnter2.getShortTermCourses();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), AnalysisActivity.class);
                i.putExtra("state","0");
                startActivity(i);
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(getApplicationContext(),"Switch state: "+b,Toast.LENGTH_LONG).show();
                if (b==true)
                {
                    library.setEnabled(true);
                    personalized.setEnabled(false);
                    cardView1.setVisibility(View.GONE);
//                    cardView2.setVisibility(View.GONE);
//                    cardView3.setVisibility(View.GONE);
                    relative1.setVisibility(View.GONE);
                    relative2.setVisibility(View.GONE);

                    recyclerView3.setVisibility(View.VISIBLE);

                    List<Library> libraries=new ArrayList<>();

                    Library library1=new Library();
                    library1.setTopic("Profile");

                    Library library2=new Library();
                    library2.setTopic("Emotional Intelligence");

                    Library library3=new Library();
                    library3.setTopic("Critical Reasoning");

//                    Library library4=new Library();
//                    library4.setTopic("Inequalities, Modulus and Logarithms");

                    Library library5=new Library();
                    library5.setTopic("Life Choices");

                    Library library6=new Library();
                    library6.setTopic("Personality Section");


                    libraries.add(library1);
                    libraries.add(library2);
                    libraries.add(library3);
                    libraries.add(library5);
                    libraries.add(library6);

                    AptitudeAdapter3 aptitudeAdapter3=new AptitudeAdapter3(getActivity().getApplicationContext(),AptitudeFragment.this,libraries);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView3.setLayoutManager(linearLayoutManager);
                    recyclerView3.setAdapter(aptitudeAdapter3);


                }
                else if (b==false)
                {
                    library.setEnabled(false);
                    personalized.setEnabled(true);
                    //aSwitch.setBackgroundColor(Color.WHITE);
                    recyclerView3.setVisibility(View.GONE);
                    cardView1.setVisibility(View.VISIBLE);
//                    cardView2.setVisibility(View.VISIBLE);
//                    cardView3.setVisibility(View.VISIBLE);
                    relative1.setVisibility(View.VISIBLE);
                    relative2.setVisibility(View.VISIBLE);

                    presenter.getCourses();
                    presesnter2.getShortTermCourses();

                }

            }
        });

        return view;

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void setTrendingCoursesRecyclerView(List<Course> courseList) {
        recyclerViewTrending.setHasFixedSize(true);
        recyclerViewTrending.removeAllViews();
        recyclerViewTrending.setNestedScrollingEnabled(false);
        recyclerViewTrending.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        recyclerViewTrending.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter2=new RecommendedCourseAdapter(new PrefManager(getActivity().getApplicationContext()),getActivity().getApplicationContext(),courseList)
        {
            @Override
            public void like(int id) {
                presenter.likeCourse(id);
            }
        };
        recyclerViewTrending.setAdapter(adapter2);
        recyclerViewTrending.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter2.notifyDataSetChanged();
    }


    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourseList) {
        recyclerViewSt.setHasFixedSize(true);
        recyclerViewSt.removeAllViews();
        recyclerViewSt.setNestedScrollingEnabled(false);
        recyclerViewSt.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        recyclerViewSt.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter=new ShortTermAdapter(new PrefManager(getActivity().getApplicationContext()),getActivity().getApplicationContext(),shortTermCourseList)
        {
            @Override
            public void like(int id) {
                presesnter2.likeShortTermCourse(id);
            }
        };

        recyclerViewSt.setAdapter(adapter);
        recyclerViewSt.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onUserReceived(User user) {
        this.user.setText(user.getFirstName());
    }

    public static AptitudeFragment newInstance(int index) {
        AptitudeFragment fragment = new AptitudeFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void startVideos() {
        startActivity(new Intent(getContext(), VideosActivity.class));
    }
}
