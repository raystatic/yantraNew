package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.repositories.HomePageRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.WishlistViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rahul on 18/4/18.
 */

public class WishListPresenter {

    HomePageRepository repository;
    WishlistViewAction viewAction;
    Context context;
    Map<String,String> queryMap;

    public WishListPresenter(HomePageRepository repository, WishlistViewAction viewAction, Context context) {
        this.repository = repository;
        this.viewAction = viewAction;
        this.context = context;
        queryMap = new HashMap<>();
    }

    public void getUniversities(){
        queryMap.put("liked","true");
        repository.getUniversities(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("uni responseCode",response.code()+","+response.message());
                PaginatedResponse<University> arrayResponse = (PaginatedResponse<University>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Universities added!");
                    }
                    else
                    viewAction.setWishListRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }

    public void getWishCourses(){
        queryMap.put("liked","true");
        repository.getCourses(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("uni responseCode",response.code()+","+response.message());
                PaginatedResponse<Course> arrayResponse = (PaginatedResponse<Course>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Courses added!");
                    }
                    else
                        viewAction.setWishListCourses(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }
    public void getWishShortTermCourses(){
        queryMap.put("liked","true");
        repository.getShortTermCourses(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("uni responseCode",response.code()+","+response.message());
                PaginatedResponse<ShortTermCourse> arrayResponse = (PaginatedResponse<ShortTermCourse>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Courses added!");
                    }
                    else
                        viewAction.setWishListShortTermCourses(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }

    public void getWishCampaigners(){
        queryMap.put("liked","true");
        repository.getCampaigners(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("uni responseCode",response.code()+","+response.message());
                PaginatedResponse<Campaigners> arrayResponse = (PaginatedResponse<Campaigners>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Campaigners added!");
                    }
                    else
                        viewAction.setWishListCampaigners(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }

    public void likeUniversity(int id){repository.likeUniversity(id, new LikeEntityManager(viewAction) );}
    public void likeCourses(int id){repository.likeCourses(id, new LikeEntityManager(viewAction) );}
    public void likeShortTermCourses(int id){repository.likeShortTermCourses(id,new LikeEntityManager(viewAction));}
    public void likeCampaigners(int id){repository.likeCompaigners(id,new LikeEntityManager(viewAction));}
}
