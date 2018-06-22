package com.university.yantra.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;

import com.university.yantra.R;
import com.university.yantra.adapters.LoanAdapter;
import com.university.yantra.presenters.ExplorePresenter;

import java.util.ArrayList;

public class ForexActivity extends AppCompatActivity {

    View view;
    LoanAdapter adapter;
    ExplorePresenter presenter;
    RecyclerView recyclerView;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> tags, views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Buy Forex");

        recyclerView = findViewById(R.id.explore_recycler_view);

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

        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        adapter =
                new LoanAdapter(this,urls,tags,views,displayMetrics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();

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
}
