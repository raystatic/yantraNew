package com.university.yantra.presenters;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.util.Log;

import com.university.yantra.apiservices.responses.LikeResponse;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.repositories.HomePageRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.UniversityViewAction;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rishabh on 3/9/2018.
 */

public class HomePagePresenter extends BasePresenter{
    UniversityViewAction viewAction;
    Context context;

    public HomePagePresenter(Context context,UniversityViewAction viewAction,HomePageRepository repository){
        this.viewAction = viewAction;
        this.context = context;
    }

    public void getCourses(){
        repository.getCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<Course> arrayResponse = (PaginatedResponse<Course>) response.body();
                if(arrayResponse!=null){
                    viewAction.setCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
    public void getShortTermCourses(){
        repository.getShortTermCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<ShortTermCourse> arrayResponse = (PaginatedResponse<ShortTermCourse>) response.body();
                if(arrayResponse!=null){
                    viewAction.setShortTermCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
    public void getCampaigners(){
        repository.getCampaigners(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<Campaigners> arrayResponse = (PaginatedResponse<Campaigners>) response.body();
                if(arrayResponse!=null){
                    viewAction.setCampaignersRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }
    public void likeUniversity(int id){repository.likeUniversity(id, new LikeEntityManager(viewAction) );}

    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }

    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

    public void likeCampaigners(int id){repository.likeCampaigners(id, new LikeEntityManager(viewAction));}
    
    public void getUniversities(){
        repository.getUniversities(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<University> arrayResponse = (PaginatedResponse<University>) response.body();
                if(arrayResponse!=null){
                    viewAction.setUniversitiesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }


}
