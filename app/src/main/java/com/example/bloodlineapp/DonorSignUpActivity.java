package com.example.bloodlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DonorSignUpActivity extends AppCompatActivity {
private TextView backButton;
    private EditText username, age, address, mobileNumber, password;
    private Spinner bloodGroups;
    private Button signUpButton;

    DBHelper DB;
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

        DB = new DBHelper(this);

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
               String name = username.getText().toString();
               String userAge = age.getText().toString();
               String add = address.getText().toString();
               String num = mobileNumber.getText().toString();
               String bgroups = bloodGroups.getSelectedItem().toString();
               String pass = password.getText().toString();

               if(name.equals("")|userAge.equals("")|add.equals("")|num.equals("")|pass.equals(""))
                   Toast.makeText(DonorSignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                   else{
                       if(pass.equals(pass)){
                           Boolean checkname = DB.checkusername(name);
                           if(checkname==false){
                               Boolean insert = DB.insertData(name,userAge, add,num,bgroups, pass);
                               if(insert==true){
                                   Toast.makeText(DonorSignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                   startActivity(intent);
                               }else{
                                   Toast.makeText(DonorSignUpActivity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                               }
                           }
                       }
                   }
               }


        });

    }
}