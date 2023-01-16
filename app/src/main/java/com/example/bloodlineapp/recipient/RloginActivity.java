package com.example.bloodlineapp.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bloodlineapp.donor.HomeActivity;

import java.util.HashMap;
import java.util.Map;

public class RloginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView backButton, textError;
    EditText name, pass;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlogin);

        name = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        textError = (TextView) findViewById(R.id.error);

        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RloginActivity.this, RSignUpActivity.class);
                startActivity(intent);
            }
        });
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textError.setVisibility(View.VISIBLE);
                username = name.getText().toString().trim();
                password = pass.getText().toString().trim();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.47/bloodlinenew/reclogin.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(RloginActivity.this, "User has been login successfully!", Toast.LENGTH_SHORT).
                                            show();
                                }else{
                                    textError.setText(response);
                                    textError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RloginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("username", username);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}