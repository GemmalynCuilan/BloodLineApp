package com.example.bloodlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createButton = findViewById(R.id.createButton);

        createButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent intent = new Intent(LoginActivity.this, DonorSignUpActivity.class);
                startActivity(intent);
           }
       });
    }
}