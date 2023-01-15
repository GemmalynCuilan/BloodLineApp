package com.example.bloodlineapp.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodlineapp.LoginActivity;
import com.example.bloodlineapp.R;

public class RSignUpActivity extends AppCompatActivity {
    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_sign_up);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}