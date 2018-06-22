package com.university.yantra.ViewMore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.ViewMoreAdapter;
import com.university.yantra.adapters.ViewMoreTrendingCoursesAdapter;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCoursesData;
import com.university.yantra.models.UniversityData;
import com.university.yantra.presenters.TrendingCoursesPresenter;
import com.university.yantra.repositories.TrendingCoursesRepository;
import com.university.yantra.viewactions.TrendingCoursesViewAction;

import java.util.ArrayList;
import java.util.List;

public class TrendingCoursesActivity extends BaseActivity implements TrendingCoursesViewAction{

    RecyclerView recyclerView;
    ViewMoreTrendingCoursesAdapter adapter;
    TrendingCoursesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more__trending_courses);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_viewMore_trendingCourses);

        presenter=new TrendingCoursesPresenter(new TrendingCoursesRepository(),getApplicationContext(),this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("Bundle");
//        ArrayList<ShortTermCoursesData> shortTermCoursesData=(ArrayList<ShortTermCoursesData>)bundle.getSerializable("ShortTermCoursesData");

        presenter.getCourses();

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
    public void setTrendingCoursesRecyclerView(List<Course> courseList) {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        adapter=new ViewMoreTrendingCoursesAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList,displayMetrics){
            @Override
            public void OnLiked(int id) {
                presenter.likeCourse(id);
            }
        };

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startVideos() {

    }
}
