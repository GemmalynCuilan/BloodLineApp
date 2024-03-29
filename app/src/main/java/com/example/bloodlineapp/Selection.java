package com.example.bloodlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodlineapp.donor.DonorSignUpActivity;
import com.example.bloodlineapp.recipient.DashboardActivity;

public class Selection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Button donorButton = findViewById(R.id.donorButton);
        Button recipientButton = findViewById(R.id.recipientButton);

       donorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selection.this, DonorSignUpActivity.class);
                startActivity(intent);
            }
        });
        recipientButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selection.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}