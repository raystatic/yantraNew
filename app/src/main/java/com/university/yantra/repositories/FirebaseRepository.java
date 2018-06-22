package com.university.yantra.repositories;

import com.university.yantra.apiservices.ApiClient;
import com.university.yantra.apiservices.ApiEndpoint;
import com.university.yantra.apiservices.responses.GenericResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rishabh on 3/6/2018.
 */

public class FirebaseRepository {
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    public void sendRegistrationIdToServer(String token, Callback callback){
        Call<GenericResponse> call = apiService.sendTokenToServer(token);
        call.enqueue(callback);

    }

}
