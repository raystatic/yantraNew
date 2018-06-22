package com.university.yantra.apiservices.responses;

/**
 * Created by sharda on 24/02/18.
 */

public class AuthenticationResponse {

    String access_token;
    String token_type;
    String expires_in;
    String refresh_token;
    String scope;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
