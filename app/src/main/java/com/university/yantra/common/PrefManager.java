package com.university.yantra.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.university.yantra.apiservices.responses.AuthenticationResponse;

/**
 * Created by Rishabh on 2/16/2018.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "yantra";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FIRST_TIME_MAIN_LAUNCH = "IsFirstTimeMainLaunch";
    private static String ACCESS_TOKEN = "access_token_string";
    private static String REFRESH_TOKEN = "refresh_token_string";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void saveString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void saveInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }


    public String getString(String key){
        return pref.getString(key, null);
    }
    public int getInt(String key){
        return pref.getInt(key, -1);
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeMainLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_MAIN_LAUNCH, true);
    }

    public void setFirstTimeMainLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_MAIN_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void putAccessToken(String token){
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }

    public String getAccessToken(){
        return pref.getString(ACCESS_TOKEN, "");   //doesn't return null (intentionally)
    }



    public void putRefreshToken(String token){
        editor.putString(REFRESH_TOKEN, token);
        editor.commit();
    }

    public String getRefreshToken(){
        return pref.getString(REFRESH_TOKEN, "");   //doesn't return null (intentionally)
    }

    public void putAuthenticationTokens(AuthenticationResponse authenticationResponse){
        putAccessToken(authenticationResponse.getAccessToken());
        putRefreshToken(authenticationResponse.getRefreshToken());
    }

    public boolean isLoggedIn(){
        return ! getAccessToken().equals("");
    }

    public void logout(){
        editor.remove(ACCESS_TOKEN);
        editor.remove(REFRESH_TOKEN);
        editor.commit();
    }
}
