package com.university.yantra.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.ViewMore.ShortTermCoursesActivity;
import com.university.yantra.ViewMore.TrendingCoursesActivity;
import com.university.yantra.adapters.AptitudeAdapter;
import com.university.yantra.adapters.AptitudeAdapter2;
import com.university.yantra.adapters.AptitudeAdapter3;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.adapters.ViewMoreCarrierAdapter;
import com.university.yantra.adapters.ViewMoreTrendingCoursesAdapter;
import com.university.yantra.common.MyApplication;
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

public class AptitudeActivity extends BaseActivity implements TrendingCoursesViewAction, ShortTermCoursesViewAction,OnUserReceivedListener {

    RecyclerView recyclerView, recyclerView2, recyclerView3;

    ArrayList<String> texts;
    TrendingCoursesPresenter presenter;
    ShortTermCoursesPresesnter presesnter2;
    Switch aSwitch;
    CurrentUserPresenter userPresenter;
    TextView personalized, library;
    TextView start;
    TextView viewMoreSt, viewMoreTrending;
    RecyclerView recyclerViewSt, recyclerViewTrending;
    RelativeLayout relative1,relative2,relative3,topLayout,startlayout;
    CardView cardView1;
    ShortTermAdapter adapter;
    DisplayMetrics displayMetrics;
    TextView user;
    RecommendedCourseAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude);

//        recyclerView=findViewById(R.id.recyclerviewApt1);
//        recyclerView2=findViewById(R.id.recyclerviewApt2);
        aSwitch=findViewById(R.id.switchApt);
        startlayout = findViewById(R.id.startLayout);
        topLayout = findViewById(R.id.top_layout);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        topLayout.getLayoutParams().height = displayMetrics.heightPixels/3;
        topLayout.getLayoutParams().width = displayMetrics.widthPixels;

        startlayout.getLayoutParams().height = displayMetrics.heightPixels/8;
        startlayout.getLayoutParams().width = displayMetrics.widthPixels;

        user = findViewById(R.id.user);
        recyclerView3=findViewById(R.id.recyclerviewApt3);
        start = (TextView)findViewById(R.id.tvStartApt);
        personalized=findViewById(R.id.tvPersonlized);
        library=findViewById(R.id.tvLibrary);
        recyclerViewSt=findViewById(R.id.stRecyclerViewApt);
        recyclerViewTrending=findViewById(R.id.coursesRecyclerViewApt);
        cardView1=findViewById(R.id.cardApt1);
        viewMoreSt=findViewById(R.id.tvViewMoreCorsesApt);
        viewMoreTrending=findViewById(R.id.tvViewMoreTrendingCoursesApt);

        relative1=findViewById(R.id.relativeApt1);
        relative2=findViewById(R.id.relativeApt2);

        userPresenter = new CurrentUserPresenter(this,this);
        userPresenter.getCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Notification");

        presenter=new TrendingCoursesPresenter(new TrendingCoursesRepository(),getApplicationContext(),this);
        presesnter2=new ShortTermCoursesPresesnter(this,getApplicationContext(),new ShortTermCoursesRepository());

//        cardView1.setVisibility(View.GONE);
////        cardView2.setVisibility(View.GONE);
////        cardView3.setVisibility(View.GONE);
//        relative1.setVisibility(View.GONE);
//        relative2.setVisibility(View.GONE);


        viewMoreSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShortTermCoursesActivity.class));
            }
        });

        viewMoreTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TrendingCoursesActivity.class));
            }
        });

        recyclerView3.setVisibility(View.VISIBLE);

        presenter.getCourses();
        presesnter2.getShortTermCourses();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AptitudeActivity.this, AnalysisActivity.class);
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

                    AptitudeAdapter3 aptitudeAdapter3=new AptitudeAdapter3(getApplicationContext(),AptitudeActivity.this,libraries);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
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

    }

    @Override
    public void onUserReceived(User user) {
        this.user.setText(user.getFirstName());
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
    public void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
        adapter2=new RecommendedCourseAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList)
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

        adapter=new ShortTermAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),shortTermCourseList)
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
    public void startVideos() {
        startActivity(new Intent(this, VideosActivity.class));
    }
}
