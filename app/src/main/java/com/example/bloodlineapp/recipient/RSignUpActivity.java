package com.example.bloodlineapp.recipient;

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
import com.example.bloodlineapp.donor.LoginActivity;
import com.example.bloodlineapp.R;

import java.util.HashMap;
import java.util.Map;

public class RSignUpActivity extends AppCompatActivity {
    private TextView backButton, textError;
    EditText name, pass;
    String username, bloodGroup, password;
    Spinner bgroups;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_sign_up);

        name = (EditText) findViewById(R.id.username);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);
        pass = (EditText) findViewById(R.id.password);
        textError = (TextView) findViewById(R.id.error);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RSignUpActivity.this, RloginActivity.class);
                startActivity(intent);
            }
        });
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textError.setVisibility(View.VISIBLE);
                username = String.valueOf(name.getText());
                bloodGroup = String.valueOf(bgroups.getSelectedItem());
                password = String.valueOf(pass.getText());

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.47/bloodlinenew/register.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), RloginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(RSignUpActivity.this, "User has been registered successfully!", Toast.LENGTH_SHORT).
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
                        Map<String, String> data = new HashMap<>();
                        data.put("username", username);
                        data.put("bloodGroup", bloodGroup);
                        data.put("password", password);
                        return data;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}