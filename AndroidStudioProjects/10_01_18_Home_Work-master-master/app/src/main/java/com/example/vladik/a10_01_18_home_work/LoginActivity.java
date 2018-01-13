package com.example.vladik.a10_01_18_home_work;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vladik.a10_01_18_home_work.Auth;
import com.example.vladik.a10_01_18_home_work.AuthToken;
import com.example.vladik.a10_01_18_home_work.R;
import com.example.vladik.a10_01_18_home_work.ViewActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailLogin, passwordLogin;
    String email, password;
    Button registrationBtn, loginBtn;
    ProgressBar myProgress;
    String MY_TAG = "MY_TAG";
    boolean isSuccess = true;
    String s = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        myProgress = findViewById(R.id.my_progress);
        registrationBtn = findViewById(R.id.registration_btn);
        loginBtn = findViewById(R.id.login_btn);

        registrationBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registration_btn) {
            new RegTask().execute("/registration");
            /*if(isSuccess){
                Intent intent = new Intent(this, ViewActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PASSWORD", password);
                startActivity(intent);
            }*/
        }
        if(v.getId() == R.id.login_btn){
            new RegTask().execute("/login");
            /*if(isSuccess){
                Intent intent = new Intent(this, ViewActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PASSWORD", password);
                startActivity(intent);
            }*/
        }
    }

    class RegTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myProgress.setVisibility(View.VISIBLE);
            email = emailLogin.getText().toString();
            password = passwordLogin.getText().toString();
        }

        @Override
        protected String doInBackground(String... voids) {
            String result = "Reg ok";
            s = voids[0];
            Gson gson = new Gson();
            Auth auth = new Auth(email, password);
            String data = gson.toJson(auth);
            Log.d(MY_TAG, s);
            String baseURL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";

            try {
                URL url = new URL(baseURL + s);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);

                OutputStream os = connection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                bw.write(data);
                bw.flush();
                bw.close();
                String line, res = "";
                BufferedReader br;
                if (connection.getResponseCode() < 400) {
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        res += line;
                    }
                    AuthToken authToken = gson.fromJson(res, AuthToken.class);
                    getSharedPreferences("AUTH", MODE_PRIVATE).edit().putString("TOKEN", authToken.getToken()).commit();
                    Log.d(MY_TAG, "Do in Background: " + authToken.getToken());
                    br.close();
                    isSuccess = true;
                } else if (connection.getResponseCode() == 409) {
                    result = "User already exist!!";
                    isSuccess = false;
                } else if (connection.getResponseCode() == 401){
                    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while((line = br.readLine()) != null){
                        res += line;
                    }
/*                    JsonParser parser = new JsonParser();
                    JsonElement jsonElement = parser.parse(res);
                    JsonObject rootObject = jsonElement.getAsJsonObject();
                    //String message = rootObject.get("message").getAsString();
                    JsonObject childObject = rootObject.getAsJsonObject("message");
                    String message = childObject.get("message").getAsString();
                    Log.d(MY_TAG, message);

                    InputStream in = new ByteArrayInputStream(res.getBytes(Charset.forName("UTF-8")));
                    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                    while(reader.hasNext()){
                        JsonToken jsonToken = reader.peek();
                        if(jsonToken == JsonToken.BEGIN_OBJECT){
                            reader.beginObject();
                        }
                        else if (jsonToken == JsonToken.END_OBJECT){
                            reader.endObject();
                        }
                        if(jsonToken == JsonToken.STRING){
                            Log.d(MY_TAG, reader.nextString());
                        }
                        else{
                            reader.skipValue();
                        }
                    }
                    reader.close();

                    Error json = gson.fromJson(res, Error.class);
                    String msg = json.getMessage().getMessage().toString();

                    JsonParser parser = new JsonParser();
                    Object obj = parser.parse(res);
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray msg = null;
                    String someMsg = null;
                    try {
                        msg = (JSONArray) jsonObject.get("message");
                        someMsg = (String) msg.get(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d(MY_TAG, res);
                    Log.d(MY_TAG, someMsg);*/
                    result = "Error login/Password";
                    isSuccess = false;
                }
                else {
                    result = "Server error";
                    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = br.readLine()) != null) {
                        res += line;
                    }
                    Log.d(MY_TAG, "doInBackground: error " + res);
                    isSuccess = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = "Connection Error. Check Internet Connection";
                isSuccess = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(s);
            Toast.makeText(LoginActivity.this, str, LENGTH_LONG).show();
            myProgress.setVisibility(View.GONE);
        }
    }
}