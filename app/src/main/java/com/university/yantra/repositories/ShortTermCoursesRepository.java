package com.university.yantra.repositories;

import com.university.yantra.apiservices.ApiClient;
import com.university.yantra.apiservices.ApiEndpoint;

import java.util.Map;

import retrofit2.Callback;


/**
 * Created by rahul on 10/3/18.
 */

public class ShortTermCoursesRepository {

    ApiEndpoint apiEndpoint= ApiClient.getClient().create(ApiEndpoint.class);

    public void getShortTermCourses(Map<String, String> uriQuery, Callback callback)
    {
        apiEndpoint.getShortTermCourses(uriQuery).enqueue(callback);
    }
    public void likeShortTermCourse(int id, Callback callback){
        apiEndpoint.likeShortTermCourse(id).enqueue(callback);
    }

}
