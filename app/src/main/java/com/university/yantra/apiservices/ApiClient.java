package com.university.yantra.apiservices;

import android.util.Log;

import com.university.yantra.common.Constants;
import com.university.yantra.common.MyApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sharda on 24/02/18.
 */

public class ApiClient {


    private static final String BASE_URL = Constants.ip +"api/";
    private static Retrofit retrofit = null;
    private static String token;
    private static final String TAG = ApiClient.class.getSimpleName();


    public static Retrofit getClient() {
        Log.d(TAG, "getClient called");
        if (retrofit == null || token.equals("")) {

            token = MyApplication.getInstance().prefManager.getAccessToken();

            Log.d(TAG, "creating retrofit client...");

            //build Authorization header, if token is empty use Anonymous else use Bearer suffixed by the access token
            final String authHeader = (token.equals("")) ? Constants.ANONYMOUS_AUTH_HEADER : "Bearer " + token;

            OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Accept", "application/JSON")
                                            .addHeader("Authorization", authHeader)
                                            .build();
                                    return chain.proceed(request);
                                }
                            })
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build();


            retrofit =
                    new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(defaultHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        }


        return retrofit;
    }
}
