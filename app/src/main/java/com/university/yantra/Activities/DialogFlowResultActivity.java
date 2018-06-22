package com.university.yantra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.university.yantra.R;
import com.university.yantra.adapters.AmbassdorsRecyclerViewAdapter;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.SearchPresenter;

import java.util.List;

public class DialogFlowResultActivity extends SearchActivity {

    Intent i;
    RecyclerView recyclerView;
    Integer type;
    TextView title;
    String searchQuery;
    SearchPresenter presenter;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_flow);
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.tooltilte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        i = getIntent();
        displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        presenter = new SearchPresenter(this, this, new Repository());
        type = Integer.parseInt(i.getExtras().getString("type"));
        searchQuery = i.getExtras().getString("search");
        String university = i.getExtras().getString("university");

        searchQuery = searchQuery.replaceAll("^\"|\"$", "");
        Log.d("searchintents",type+searchQuery);
        if(!searchQuery.equals("null")){
            queryMap.put("search", searchQuery);
            Log.d("searchintents",searchQuery+"");
        }
        else{
            searchQuery = " ";
            queryMap.put("search"," ");
        }
        if(!university.equals("null")){
            queryMap.put("universities__name", university);
        }
        switch (type) {
            case 1:
                presenter.searchUniversity(queryMap);
               title.setText("Here are some universities based on "+"'"+searchQuery+"'");
                break;
            case 2:
                presenter.searchCourses(queryMap);
                title.setText("Here are some courses based on "+"'"+searchQuery+"'");
                break;
            case 3:
                presenter.searchShortTermCourses(queryMap);
                title.setText("Here are some short term courses based on "+"'"+searchQuery+"'");
                break;
            case 4:
                presenter.searchAmbassdors(queryMap);
                title.setText("Here are some ambassdors based on "+"'"+searchQuery+"'");
                break;
            default:
                showMessage("Error");
        }
    }

    @Override
    public void setUniversityRecyclerView(List<University> universityDataList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new RecommendedUniAdapter(this, universityDataList,displayMetrics) {
                    @Override
                    public void OnLiked(int id) {
                        presenter.likeUniversity(id);
                    }
                };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();

    }
    @Override
    public void setCoursesRecyclerView(List<Course> courses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        courseAdapter =
                new RecommendedCourseAdapter(MyApplication.getInstance().prefManager,this,courses){
                    @Override
                    public void like(int id) {
                        presenter.likeCourse(id);
                    }
                };

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        courseAdapter.notifyDataSetChanged();
    }
    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        shortTermAdapter = new ShortTermAdapter(new PrefManager(this),this,shortTermCourses){
            @Override
            public void like(int id) {
                presenter.likeShortTermCourse(id);
            }
        };
        recyclerView.setAdapter(shortTermAdapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        shortTermAdapter.notifyDataSetChanged();
    }
    @Override
    public void setCampaignersRecyclerView(List<Campaigners> campaigners){
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ambassdorsRecyclerViewAdapter = new AmbassdorsRecyclerViewAdapter(this,campaigners,displayMetrics){
            @Override
            public void like(int id) {
                presenter.likeCampaigners(id);
            }
        };
        recyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
        ambassdorsRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
