package com.example.vladik.a10_01_18_home_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
    Button addBtn, logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        addBtn = findViewById(R.id.add_btn);
        logoutBtn = findViewById(R.id.login_btn);

        addBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.logout_btn){
            Toast.makeText(this, "Shalom", Toast.LENGTH_LONG).show();
            /*startActivity(new Intent(this, LoginActivity.class));*/
        }
        if(view.getId() == R.id.add_btn){Toast.makeText(this, "Privet", Toast.LENGTH_LONG).show();
        }
    }
}
