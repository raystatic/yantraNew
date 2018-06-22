package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.SearchViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rishabh on 4/19/2018.
 */

public class SearchPresenter {
    SearchViewAction viewAction;
    Context context;
    Repository repository;
    public SearchPresenter(SearchViewAction viewAction,Context context , Repository repository){
        this.viewAction = viewAction;
        this.context = context;
        this.repository = repository;
    }
    public void searchUniversity(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.searchUniversity(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                PaginatedResponse<University> arrayResponse = (PaginatedResponse<University>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setUniversityRecyclerView(arrayResponse.getResults());
                }
                else{
                    viewAction.showMessage("null");
                }
            }

        });
    }
    public void searchAmbassdors(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getCampaigners(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<Campaigners> arrayResponse = (PaginatedResponse<Campaigners>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setCampaignersRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }
    public void searchCourses(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getCourses(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<Course> arrayResponse = (PaginatedResponse<Course>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
    public void searchShortTermCourses(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getShortTermCourses(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<ShortTermCourse> arrayResponse = (PaginatedResponse<ShortTermCourse>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");

                    }
                    else
                    viewAction.setShortTermCoursesRecyclerView(arrayResponse.getResults());
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


}
