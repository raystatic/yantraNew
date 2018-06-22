package com.university.yantra.ViewMore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.ViewMoreAdapter;
import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;
import com.university.yantra.models.University;
import com.university.yantra.models.UniversityData;
import com.university.yantra.presenters.TrendingUniversitryPresenter;
import com.university.yantra.repositories.TrendingUniversitiesRepository;
import com.university.yantra.viewactions.TrendingCoursesViewAction;
import com.university.yantra.viewactions.TrendingUniversitiesViewAction;
import com.university.yantra.viewactions.UniversityViewAction;

import java.util.ArrayList;
import java.util.List;

public class TrendingUniversitiesActivity extends BaseActivity implements TrendingUniversitiesViewAction{

    RecyclerView recyclerView;
    ViewMoreAdapter adapter;
    TrendingUniversitryPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_viewMore);

        presenter=new TrendingUniversitryPresenter(this,getApplicationContext(),new Repository());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("Bundle");
//        ArrayList<UniversityData> universityDatas=(ArrayList<UniversityData>)bundle.getSerializable("UniversityData");

        presenter.getUniversities();

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
    public void setTrendingUniversitiesRecyclerView(List<University> universityDataList) {
        adapter=new ViewMoreAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),universityDataList)
            {
                @Override
                public void like(int id) {
                presenter.likeUniversity(id);
            }
            };

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
