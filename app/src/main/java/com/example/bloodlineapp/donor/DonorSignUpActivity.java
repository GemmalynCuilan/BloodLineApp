package com.example.bloodlineapp.donor;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;

import java.util.HashMap;
import java.util.Map;

public class DonorSignUpActivity extends AppCompatActivity {

    EditText name, userage, add, mobileNum, pass;
    String username, age, address,mobileNumber, bloodGroup, password;
    Spinner bgroups;
    TextView backButton, textError;
    Button signUpButton;
    ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_sign_up);
        username = age = address = mobileNumber = bloodGroup = password = "";
        name = (EditText) findViewById(R.id.username);
        userage = (EditText) findViewById(R.id.age);
        add = (EditText) findViewById(R.id.address);
        mobileNum = (EditText) findViewById(R.id.mobileNumber);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);
        pass = (EditText) findViewById(R.id.password);
        textError = (TextView) findViewById(R.id.error);

        loadBar = new ProgressDialog(this);

        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textError.setVisibility(View.VISIBLE);
                username = String.valueOf(name.getText());
                age = String.valueOf(userage.getText());
                address = String.valueOf(add.getText());
                mobileNumber = String.valueOf(mobileNum.getText());
                bloodGroup = String.valueOf(bgroups.getSelectedItem());
                password = String.valueOf(pass.getText());

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.47/bloodlinenew/signup.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(DonorSignUpActivity.this, "User has been registered successfully!", Toast.LENGTH_SHORT).
                                            show();
                                }else{
                                    textError.setText(response);
                                    textError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("username", username);
                        paramV.put("age", age);
                        paramV.put("address", address);
                        paramV.put("mobileNumber", mobileNumber);
                        paramV.put("bloodGroup", bloodGroup);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}