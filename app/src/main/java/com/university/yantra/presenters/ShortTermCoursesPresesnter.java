package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.apiservices.responses.LikeResponse;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.repositories.ShortTermCoursesRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.ShortTermCoursesViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul on 10/3/18.
 */

public class ShortTermCoursesPresesnter {

    ShortTermCoursesViewAction viewAction;
    Context context;
    ShortTermCoursesRepository repository;

    public ShortTermCoursesPresesnter(ShortTermCoursesViewAction viewAction, Context context, ShortTermCoursesRepository repository) {
        this.viewAction = viewAction;
        this.context = context;
        this.repository = repository;
    }

    public void getShortTermCourses()
    {
        repository.getShortTermCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("short responseCode",response.code()+","+response.message());
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


    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

}
