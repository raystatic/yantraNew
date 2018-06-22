package com.university.yantra.apiservices;

import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.apiservices.responses.GenericResponse;
import com.university.yantra.apiservices.responses.LikeResponse;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.Tag;
import com.university.yantra.models.University;
import com.university.yantra.models.User;
import com.university.yantra.models.Videos;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.models.ShortTermCourse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by sharda on 24/02/18.
 *
 * API Endpoint declarations
 *
 */

public interface ApiEndpoint {


    /** Register User using manual form **/
    @POST("users/")
    @Multipart
    Call<User> registerUser(@PartMap() Map<String, RequestBody> map);

    /** Get details for the user whose token is sent in Authorization header **/
    @GET("users/by_token")
    Call<User> getCurrentUser();

    /** Get Yantra OAuth2 Access Token using username(email) and password
     *
     *  Method : POST
     *  Parameters (SET 1) : client_id, client_secret, grant_type - PASSWORD_GRANT, username, password
     *  Parameters (SET 2 - for refresh_token) : client_id, client_secret, grant_type - REFRESH_TOKEN_GRANT, refresh_token
     *  Result : Returns Yantra OAUTH2 Access Token
     *
     **/
    //fcm token send to server

    @GET("videos/")
    Call<PaginatedResponse<Videos>> getVideos(@QueryMap Map<String, String> filterData);

    @FormUrlEncoded
    @POST("fcm/")
    Call<GenericResponse> sendTokenToServer(
            @Field("registrationId") String registrationId
    );
    @GET("universityTags/")
    Call<PaginatedResponse<Tag>> getTags(@QueryMap Map<String, String> filterData);
    @GET("courses/")
    Call<PaginatedResponse<Course>> getCourses(@QueryMap Map<String, String> filterData);
    @GET("shortTermCourses/")
    Call<PaginatedResponse<ShortTermCourse>> getShortTermCourses(@QueryMap Map<String, String> filterData);
    @GET("universities/")
    Call<PaginatedResponse<University>> getUniversities(@QueryMap Map<String, String> filterData);
    @GET("universities/")
    Call<PaginatedResponse<University>> searchUniversities(@QueryMap Map<String, String> filterData);
    @GET("campaigners/")
    Call<PaginatedResponse<Campaigners>> getCampaigners(@QueryMap Map<String, String> filterData);

    @POST("universities/{id}/like/")
    Call<LikeResponse> likeUniversity(@Path("id") int id);

    @POST("courses/{id}/like/")
    Call<LikeResponse> likeCourse(@Path("id") int id);

    @POST("shortTermCourses/{id}/like/")
    Call<LikeResponse> likeShortTermCourse(@Path("id") int id);

    @POST("campaigners/{id}/like/")
    Call<LikeResponse> likeCampaigners(@Path("id") int id);




    @POST("auth/token/")
    @FormUrlEncoded
    Call<AuthenticationResponse> getToken(@FieldMap() Map<String, String> map);

    /** Convert third-party provider access token to Yantra OAuth2 Access Token
     *  Method : POST
     *  Parameters : backend - [facebook, google-oauth2], client_id, client_secret, grant_type - CONVERT_TOKEN_GRANT, token - Third Party Access Token
     *  Result : Returns Yantra OAUTH2 Access Token
     *
     **/


    @POST("auth/convert-token/")
    @FormUrlEncoded
    Call<AuthenticationResponse> convertToken(@FieldMap() Map<String, String> map);



}
