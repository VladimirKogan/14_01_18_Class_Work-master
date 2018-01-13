package com.example.vladik.a10_01_18_home_work;

/**
 * Created by vladik on 12/01/2018.
 */

public class Auth {
    private String email, password;

    public Auth(){

    }

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
