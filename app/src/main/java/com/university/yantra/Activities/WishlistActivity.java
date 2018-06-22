package com.university.yantra.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.AmbassdorsRecyclerViewAdapter;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.WishListPresenter;
import com.university.yantra.repositories.HomePageRepository;
import com.university.yantra.viewactions.WishlistViewAction;

import java.util.List;

public class WishlistActivity extends BaseActivity implements WishlistViewAction{

    ImageView filters;
    RecyclerView recyclerView;
    WishListPresenter presenter;
    RecommendedUniAdapter adapter;
    RecommendedCourseAdapter courseAdapter;
    ShortTermAdapter shortTermAdapter;
    AmbassdorsRecyclerViewAdapter ambassdorsRecyclerViewAdapter;
    PrefManager prefManager;

    DisplayMetrics displayMetrics;

    int filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        savedInstanceState=new Bundle();
        savedInstanceState.putInt("filter",filter);

        filters=findViewById(R.id.filterWish);

//        filter=getIntent().getIntExtra("filter",0);



        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.recyclerView_wishlist);

        prefManager=new PrefManager(getApplicationContext());

        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FiltersActivity.class);
                intent.putExtra("filterState",prefManager.getInt("filterState"));
                startActivityForResult(intent,1);
            }
        });

        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        presenter=new WishListPresenter(new HomePageRepository(),this,getApplicationContext());


        Log.d("filterState",prefManager.getInt("filterState")+"");


        switch (prefManager.getInt("filterState"))
        {
            case 1: presenter.getUniversities();
                    break;
            case 2: presenter.getWishCourses();
                    break;
            case 3: presenter.getWishShortTermCourses();
                    break;
            case 4: presenter.getWishCampaigners();
                    break;
            default: presenter.getUniversities();
                        Toast.makeText(getApplicationContext(),"filterSaved = "+filter,Toast.LENGTH_LONG).show();
                        break;
        }

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
    public void setWishListRecyclerView(List<University> universityDataList) {

        adapter=new RecommendedUniAdapter(this,universityDataList,displayMetrics){
            @Override
            public void OnLiked(int id) {
                presenter.likeUniversity(id);
            }
        };
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setWishListCourses(List<Course> courseList) {
        courseAdapter=new RecommendedCourseAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList){
            @Override
            public void OnLiked(int id) {
                presenter.likeCourses(id);
            }
        };
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseAdapter);
    }

    @Override
    public void setWishListShortTermCourses(List<ShortTermCourse> shortTermCourseList) {
        shortTermAdapter=new ShortTermAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),shortTermCourseList){
            @Override
            public void OnLiked(int id) {
                presenter.likeShortTermCourses(id);
            }
        };
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shortTermAdapter);
    }

    @Override
    public void setWishListCampaigners(List<Campaigners> campaignersList) {
        ambassdorsRecyclerViewAdapter=new AmbassdorsRecyclerViewAdapter(getApplicationContext(),campaignersList,displayMetrics)
        {
            @Override
            public void OnLiked(int id) {
                presenter.likeCampaigners(id);
            }
        };
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            if (resultCode== RESULT_OK)
            {
                filter=data.getIntExtra("filter",0);
                if (filter==1)
                {
                    presenter.getUniversities();
                }
                else if (filter==2)
                {
                    presenter.getWishCourses();
                }
                else if (filter==3)
                {
                    presenter.getWishShortTermCourses();
                }
                else if (filter==4)
                {
                    presenter.getWishCampaigners();
                }
                else {
                    Toast.makeText(getApplicationContext(),"filter = "+filter,Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
