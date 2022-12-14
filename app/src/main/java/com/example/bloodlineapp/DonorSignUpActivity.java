package com.example.bloodlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DonorSignUpActivity extends AppCompatActivity {
private TextView backButton;
    private EditText username, age, address, mobileNumber, password;
    private Spinner bloodGroups;
    private Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_sign_up);

        username = (EditText) findViewById(R.id.username);
        age = (EditText) findViewById(R.id.age);
        address = (EditText) findViewById(R.id.address);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        bloodGroups = (Spinner) findViewById(R.id.bloodGroups);


        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorSignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        signUpButton=findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorSignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}