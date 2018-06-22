package com.university.yantra.apiservices;

import android.util.Log;

import com.university.yantra.common.Constants;
import com.university.yantra.models.User;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sharda on 24/02/18.
 */

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    public Repository(){

    }

    private Map<String, RequestBody> getPartMap(Map<String, String> map){
        Map<String, RequestBody> newMap = new HashMap<>();
        //create request body map for Multipart Request
        for(String key : map.keySet())
            newMap.put(key, map.get(key) != null ? createPartFromString(map.get(key)) : createPartFromString(""));
        return newMap;
    }

    private RequestBody createPartFromString(String text){
        return  RequestBody.create(
                MediaType.parse("multipart/form-data"),  text );

    }

    /**
     *
     * @param map
     * @param callback
     *
     * <p>Map object should contain necessary POST parameters for user registration</p>
     */

    public void registerUser(Map<String, String> map, Callback callback){
        Log.d(TAG, "registerUser : " + map.toString());
        Call<User> call = apiService.registerUser(getPartMap(map));
        call.enqueue(callback);
    }

    /**
     *
     * @param map
     * @param callback
     *
     * <p>Expects username and password in map object.</p>
     */
    public void loginDefault(Map<String, String> map, Callback callback){

        map.put(Constants.OAUTH2_CLIENT_ID_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_ID);
        map.put(Constants.OAUTH2_CLIENT_SECRET_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_SECRET);
        map.put(Constants.OAUTH2_GRANT_TYPE_PARAM_KEY, Constants.PASSWORD_GRANT);

        Log.d(TAG, "loginDefault : " + map.toString());

        apiService.getToken(map).enqueue(callback);

    }

    /**
     *
     * @param facebookAccessToken
     * @param callback
     *
     * <p>Expects 'facebookAccessToken' string</p>
     */
    public void loginFacebook(String facebookAccessToken, Callback callback){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.OAUTH2_CLIENT_ACCESS_TOKEN_PARAM_KEY, facebookAccessToken);
        map.put(Constants.OAUTH2_BACKEND_PARAM_KEY, Constants.FACEBOOK_AUTH_BACKEND);
        convertToken(map,callback);
    }

    /**
     *
     * @param googleAccessToken
     * @param callback
     *
     * <p>Expects 'googleAccessToken' string</p>
     */
    public void loginGoogle(String googleAccessToken, Callback callback){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.OAUTH2_CLIENT_ACCESS_TOKEN_PARAM_KEY, googleAccessToken);
        map.put(Constants.OAUTH2_BACKEND_PARAM_KEY, Constants.GOOGLE_AUTH_BACKEND);
        convertToken(map,callback);
    }


    /**
     *
     * @param map
     * @param callback
     *
     * Expects 'token' and 'backend' in map object. Called by loginFacebook and loginGoogle.
     */
    protected void convertToken(Map<String, String> map, Callback callback){
        map.put(Constants.OAUTH2_CLIENT_ID_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_ID);
        map.put(Constants.OAUTH2_CLIENT_SECRET_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_SECRET);
        map.put(Constants.OAUTH2_GRANT_TYPE_PARAM_KEY, Constants.CONVERT_TOKEN_GRANT);
        Log.d(TAG, "convertToken: " + map.toString());
        apiService.convertToken(map).enqueue(callback);
    }

    public void getCurrentUser(Callback callback){
        apiService.getCurrentUser().enqueue(callback);
    }

    public void getCourses(Map<String,String> uriQuery, Callback callback){
        apiService.getCourses(uriQuery).enqueue(callback);

    }
    public void searchUniversity(Map<String,String> uriQuery, Callback callback){
        apiService.searchUniversities(uriQuery).enqueue(callback);

    }
    public void getVideos(Map<String,String> uriQuery, Callback callback){
        apiService.getVideos(uriQuery).enqueue(callback);
    }
    public void getShortTermCourses(Map<String,String> uriQuery, Callback callback){
        apiService.getShortTermCourses(uriQuery).enqueue(callback);
    }
    public void getTags(Map<String,String> uriQuery, Callback callback){
        apiService.getTags(uriQuery).enqueue(callback);
    }
    public void getUniversities(Map<String,String> uriQuery, Callback callback){
        apiService.getUniversities(uriQuery).enqueue(callback);
    }
    public void getCampaigners(Map<String,String> uriQuery, Callback callback){
        apiService.getCampaigners(uriQuery).enqueue(callback);
    }
    public void likeUniversity(int id, Callback callback){
        apiService.likeUniversity(id).enqueue(callback);
    }
    public void likeCourse(int id, Callback callback){
        apiService.likeCourse(id).enqueue(callback);
    }
    public void likeShortTermCourse(int id, Callback callback){
        apiService.likeShortTermCourse(id).enqueue(callback);
    }

    public void likeCampaigners(int id, Callback callback){
        apiService.likeCampaigners(id).enqueue(callback);
    }


}
