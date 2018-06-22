package com.university.yantra.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.ViewMoreAdapter;
import com.university.yantra.adapters.ViewMoreCarrierAdapter;
import com.university.yantra.adapters.ViewMoreTrendingCoursesAdapter;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.ShortTermCoursesPresesnter;
import com.university.yantra.presenters.TrendingCoursesPresenter;
import com.university.yantra.presenters.TrendingUniversitryPresenter;
import com.university.yantra.repositories.ShortTermCoursesRepository;
import com.university.yantra.repositories.TrendingCoursesRepository;
import com.university.yantra.repositories.TrendingUniversitiesRepository;
import com.university.yantra.viewactions.ShortTermCoursesViewAction;
import com.university.yantra.viewactions.TrendingCoursesViewAction;
import com.university.yantra.viewactions.TrendingUniversitiesViewAction;

import java.util.List;

public class FiltersActivity extends BaseActivity{


    RadioButton universities, courses, stCourses, camp;
    RadioGroup sortGroup;
    ImageView resetButton;
    public Integer sortId = 0;
    Button apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Filters");

        initUi();


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

    private void initUi()
    {
        universities = (RadioButton) findViewById(R.id.uni);
        courses = (RadioButton) findViewById(R.id.courses);
        stCourses = (RadioButton) findViewById(R.id.stCourses);
        sortGroup = (RadioGroup) findViewById(R.id.sortGroup);
        camp=findViewById(R.id.camp);
        apply=findViewById(R.id.btn_apply_filters);


        resetButton = (ImageView) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFilters();
            }
        });

        final int filter=getIntent().getIntExtra("filterState",1);



        switch (filter){
            case 1 : universities.setChecked(true); break;
            case 2 : courses.setChecked(true); break;
            case 3 : stCourses.setChecked(true); break;
            case 4 : camp.setChecked(true); break;
            default: Toast.makeText(getApplicationContext(),"filter = "+filter,Toast.LENGTH_LONG).show();
        }



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (universities.isChecked())
                {
                    sortId=1;
                }
                else if (courses.isChecked())
                {
                    sortId=2;
                }
                else if (stCourses.isChecked())
                {
                    sortId=3;
                }
                else if (camp.isChecked())
                {
                    sortId=4;
                }

                if (sortId==0)
                {
                    Toast.makeText(getApplicationContext(),"No filter is applied right now",Toast.LENGTH_LONG).show();
                }

                PrefManager prefManager=new PrefManager(getApplicationContext());
                prefManager.saveInt("filterState",sortId);

                Intent intent=new Intent();

                intent.putExtra("filter",sortId);
                setResult(RESULT_OK, intent);

//                startActivity(intent);
                finish();
            }
        });
    }


    public void resetFilters(){
        sortId=0;
    }

}
