package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.apiservices.responses.LikeResponse;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.models.Course;
import com.university.yantra.repositories.ShortTermCoursesRepository;
import com.university.yantra.repositories.TrendingCoursesRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.ShortTermCoursesViewAction;
import com.university.yantra.viewactions.TrendingCoursesViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul on 10/3/18.
 */

public class TrendingCoursesPresenter {

    TrendingCoursesRepository repository;
    Context context;
    TrendingCoursesViewAction viewAction;

    public TrendingCoursesPresenter(TrendingCoursesRepository repository, Context context, TrendingCoursesViewAction viewAction) {
        this.repository = repository;
        this.context = context;
        this.viewAction = viewAction;
    }

    public void getCourses(){
        repository.getCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("course responseCode",response.code()+","+response.message());
                PaginatedResponse<Course> arrayResponse = (PaginatedResponse<Course>) response.body();
                if(arrayResponse!=null){
                    viewAction.setTrendingCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }

    public void likeCourse(int id){
        repository.likeCourse(id, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                if(response!=null){
                    LikeResponse likeResponse = (LikeResponse)response.body();
                    if(likeResponse.getLiked()){
                        viewAction.showMessage("Added to Wishlist");
                    }
                    else
                        viewAction.showMessage("Removed from Wishlist");
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }

}
