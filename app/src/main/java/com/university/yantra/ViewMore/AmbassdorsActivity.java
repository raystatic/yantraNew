package com.university.yantra.ViewMore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.ViewMoreAdapter;
import com.university.yantra.adapters.ViewMoreAmbassadorAdapter;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.AmbassdorsData;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.UniversityData;
import com.university.yantra.presenters.CampaignersPresenters;
import com.university.yantra.repositories.CampaignersRepository;
import com.university.yantra.viewactions.AmbasdorsViewAction;

import java.util.ArrayList;
import java.util.List;

public class AmbassdorsActivity extends BaseActivity implements AmbasdorsViewAction{

    RecyclerView recyclerView;
    ViewMoreAmbassadorAdapter adapter;
    CampaignersPresenters presenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more__ambassador);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_viewMore_ambass);

        presenters=new CampaignersPresenters(this,getApplicationContext(),new CampaignersRepository());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("Bundle");
//        ArrayList<AmbassdorsData> ambassdorsData=(ArrayList<AmbassdorsData>)bundle.getSerializable("AmbassadorsData");
        presenters.getCampaigners();

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
    public void setAmbassdorRecyclerView(List<Campaigners> ambassdorsDataList) {
        adapter=new ViewMoreAmbassadorAdapter(getApplicationContext(),ambassdorsDataList){
            @Override
            public void OnLiked(int id) {
                presenters.likeCampaigners(id);
            }
        };
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
