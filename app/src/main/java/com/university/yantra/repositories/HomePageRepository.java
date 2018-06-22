package com.university.yantra.repositories;

import com.university.yantra.apiservices.ApiClient;
import com.university.yantra.apiservices.ApiEndpoint;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rishabh on 3/9/2018.
 */

public class HomePageRepository {
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    public void getCourses(Map<String,String> uriQuery, Callback callback){
        apiService.getCourses(uriQuery).enqueue(callback);

    }
    public void getShortTermCourses(Map<String,String> uriQuery, Callback callback){
        apiService.getShortTermCourses(uriQuery).enqueue(callback);
    }
    public void getUniversities(Map<String,String> uriQuery, Callback callback){
        apiService.getUniversities(uriQuery).enqueue(callback);
    }
    public void getCampaigners(Map<String,String> uriQuery, Callback callback){
        apiService.getCampaigners(uriQuery).enqueue(callback);
    }
    public void likeUniversity(int id, Callback callback){
        apiService.likeUniversity(id);
    }
    public void likeCourses(int id, Callback callback)
    {
        apiService.likeCourse(id);
    }

    public void likeShortTermCourses(int id, Callback callback)
    {
        apiService.likeShortTermCourse(id);
    }

    public void likeCompaigners(int id, Callback callback)
    {
        apiService.likeCampaigners(id);
    }

}
