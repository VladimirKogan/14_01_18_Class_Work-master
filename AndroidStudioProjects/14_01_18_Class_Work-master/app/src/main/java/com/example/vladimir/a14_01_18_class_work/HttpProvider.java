package com.example.vladimir.a14_01_18_class_work;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by vladimir on 14/01/2018.
 */

public class HttpProvider {
    private static final HttpProvider ourInstance = new HttpProvider();
    private static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";

    private OkHttpClient client;
    private MediaType JSON;
    private Gson gson;

    public static HttpProvider getInstance() {
        return ourInstance;
    }

    private HttpProvider() {
        /*Manager m = new Manager.Builder()
                .name("")
                .email("")
                .salary(26)
                .build();*/
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        gson = new Gson();
    }
    public String viewAllContacts(String email, String password, String token) throws Exception {
        Auth auth = new Auth(email, password, token);

        String jsonRequest = gson.toJson(auth);
        RequestBody body = RequestBody.create(JSON, jsonRequest);
        Request request = new Request.Builder().url(BASE_URL + "/contactsarray").get().build();

        Response response = client.newCall(request).execute();

        if(response.code()<400){Log.d("MY_TAG", String.valueOf(response.code()));}
        else if(response.code() == 401){Log.d("MY_TAG", String.valueOf(response.code()));}
        else{
            throw new Exception("Server error!!!!");
        }
        return token;
    }

    public String registration(String email, String password) throws Exception {
        Auth auth = new Auth(email, password);
        String jsonRequest = gson.toJson(auth);

        RequestBody body = RequestBody.create(JSON, jsonRequest);
        Request request = new Request.Builder()
                .url(BASE_URL + "/registration")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        if(response.code() < 400){
            String responseJson = response.body().string();
            AuthToken token = gson.fromJson(responseJson, AuthToken.class);
            //return responseJson;
            return token.getToken();
        }else if(response.code() == 409){
            throw new Exception("User already exist");
        }else{
            String error = response.body().string();
            Log.d("MY_TAG", "registration error: " + error);
            throw new Exception("Server error! Call to support");
        }
    }
    public String login(String email, String password) throws Exception {
        Auth auth = new Auth(email, password);
        String jsonRequest = gson.toJson(auth);
        RequestBody body = RequestBody.create(JSON, jsonRequest);
        Request request = new Request.Builder().url(BASE_URL + "/login").post(body).build();

        Response response = client.newCall(request).execute();
        if(response.code() < 400){
            String responeJson = response.body().string();
            AuthToken token = gson.fromJson(responeJson, AuthToken.class);
            //return token.getToken();
            return token.getToken();
            //return token.getKind();
            //return responeJson;
        }else if(response.code() == 401){
            Log.d("MY_TAG", "401 Error");
            throw new Exception("Wrong login o password!!!");
            //String responseJson = response.body().string();
            //AuthToken msg = gson.fromJson(responseJson, AuthToken.class);
            //String message = msg.getContent().getMessage();
            //return "" + msg.getCode();
            //return responseJson;
            //Log.d("MY_TAG", message);
            //return message;

        }else{
            throw new Exception("Server Error!");
        }
    }
}
