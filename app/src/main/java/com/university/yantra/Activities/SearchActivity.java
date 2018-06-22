package com.university.yantra.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.AmbassdorsRecyclerViewAdapter;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.adapters.SearchIniActivityAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.adapters.ViewMoreAdapter;
import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.SearchPresenter;
import com.university.yantra.viewactions.SearchViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener, SearchViewAction {

    Context context;
    public int searchChoice = 1;
    SearchPresenter presenter;
    Map<String, String> queryMap;
    ProgressBar progressBar;
    AmbassdorsRecyclerViewAdapter ambassdorsRecyclerViewAdapter;
    RecommendedCourseAdapter courseAdapter;
    ShortTermAdapter shortTermAdapter;
    RecyclerView recyclerView;
    public SearchView searchView;
    String mQueryString;
    RecommendedUniAdapter adapter;

    android.os.Handler handler;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        getSupportActionBar().setTitle("Search");
        context = getApplicationContext();
        searchView = (SearchView) findViewById(R.id.searchView);
        addStaticData();
        queryMap = new HashMap<>();
        presenter = new SearchPresenter(this,this,new Repository());
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        handler = new android.os.Handler();

        searchView = (SearchView) findViewById(R.id.searchView);
        assert searchView != null;
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    }
    public void addStaticData(){


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("Sharda University");
        products.add("Cambridge University");
        products.add("Delhi University");
        products.add("Banasthali Vidyapeeth");
        products.add("Oxford University");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(this); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchIniActivityAdapter adapter = new SearchIniActivityAdapter(getApplicationContext(),products,recyclerView,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQueryString=query;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.GONE);
        if(TextUtils.isEmpty(query)){
            addStaticData();
        }
        else{
            queryMap.put("search",query);
            switch (searchChoice){
                case 1:presenter.searchUniversity(queryMap);
                    break;
                case 2:presenter.searchCourses(queryMap);
                    break;
                case 3:presenter.searchShortTermCourses(queryMap);
                    break;
                case 4:presenter.searchAmbassdors(queryMap);
                    break;
                default:showMessage("Error");
            }
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        mQueryString = newText;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.VISIBLE);
//
//       // presenter.applySearch(mQueryString);
        if(TextUtils.isEmpty(newText)){
            addStaticData();
            progressBar.setVisibility(View.GONE);
            clearRecyclerView();
        }
        else {

            if(!searchView.toString().equals(""))

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(TextUtils.getTrimmedLength(mQueryString)>0){
                            queryMap.put("search",newText);
                            Log.d("searchintent",newText);
                            switch (searchChoice){
                                case 1:presenter.searchUniversity(queryMap);
                                    break;
                                case 2:presenter.searchCourses(queryMap);
                                    break;
                                case 3:presenter.searchShortTermCourses(queryMap);
                                    break;
                                case 4:presenter.searchAmbassdors(queryMap);
                                    break;
                                default:showMessage("Error");
                            }
                        }

                        else{

                        }

                    }
                }, 600);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        return super.onCreateOptionsMenu(menu);
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
                new RecommendedUniAdapter(this,universityDataList,displayMetrics){
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

    public void clearRecyclerView() {


        recyclerView.removeAllViewsInLayout();
        recyclerView.removeAllViews();
        if(recyclerView.getAdapter()!=null)
            recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        else if(item.getItemId()==R.id.univ){
            showMessage("Universities Selected");
            searchChoice = 1;
            addStaticData();
        }
        else if(item.getItemId() == R.id.course){
            showMessage("Course Selected");
            searchChoice = 2;
            addStaticData();
        }
        else if(item.getItemId()==R.id.short_term){
            showMessage("Short Term Courses Selected");
            searchChoice  = 3;
            addStaticData();
        }
        else if(item.getItemId() == R.id.ambassdors){
            showMessage("Ambassdors Selected");
            searchChoice = 4;
            addStaticData();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
