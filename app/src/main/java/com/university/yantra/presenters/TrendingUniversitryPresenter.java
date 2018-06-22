package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.University;
import com.university.yantra.repositories.TrendingUniversitiesRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.TrendingUniversitiesViewAction;
import com.university.yantra.viewactions.UniversityViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul on 10/3/18.
 */

public class TrendingUniversitryPresenter {

    TrendingUniversitiesViewAction viewAction;
    Context context;
    Repository repository;

    public TrendingUniversitryPresenter(TrendingUniversitiesViewAction viewAction, Context context, Repository repository) {
        this.viewAction = viewAction;
        this.context = context;
        this.repository = repository;
    }

    public void likeUniversity(int id){
        repository.likeUniversity(id, new LikeEntityManager(viewAction));
    }
    public void getUniversities(){
        repository.getUniversities(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("uni responseCode",response.code()+","+response.message());
                PaginatedResponse<University> arrayResponse = (PaginatedResponse<University>) response.body();
                if(arrayResponse!=null){
                    viewAction.setTrendingUniversitiesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }

}
