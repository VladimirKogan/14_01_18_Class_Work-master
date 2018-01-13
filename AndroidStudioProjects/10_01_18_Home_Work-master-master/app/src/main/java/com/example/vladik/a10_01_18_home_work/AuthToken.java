package com.example.vladik.a10_01_18_home_work;

/**
 * Created by vladik on 12/01/2018.
 */

public class AuthToken {
    private String token;

    public AuthToken(AuthToken token) {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}