package com.example.vladik.a10_01_18_home_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText adressEdit, descriptionEdit, emailEdit, fullNameEdit, phoneNumberEdit;
    Button cancelBtn, updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        adressEdit = findViewById(R.id.edit_adress);
        descriptionEdit = findViewById(R.id.edit_description);
        emailEdit = findViewById(R.id.edit_email);
        fullNameEdit = findViewById(R.id.edit_fullName);
        phoneNumberEdit = findViewById(R.id.edit_phoneNumber);
        cancelBtn = findViewById(R.id.cancel_btn);
        updateBtn = findViewById(R.id.update_btn);


    }
}
