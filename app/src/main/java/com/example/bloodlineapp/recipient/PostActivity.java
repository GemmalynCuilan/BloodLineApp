package com.example.bloodlineapp.recipient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.bloodlineapp.donor.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    EditText name, add, num;
    String username, address, number, bloodGroup;
    Spinner bgroups;
    TextView textError;
    Button postButton;
    ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        name = (EditText) findViewById(R.id.username);
        add = (EditText) findViewById(R.id.address);
        num = (EditText) findViewById(R.id.number);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);


        loadBar = new ProgressDialog(this);

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });
        postButton = (Button) findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);


                username = String.valueOf(name.getText());
                address = String.valueOf(add.getText());
                number = String.valueOf(num.getText());
                bloodGroup = String.valueOf(bgroups.getSelectedItem());

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.47/database/req.php";
                String url1 = "https://bloodlinenew.000webhostapp.com/req.php";
                String url2 = "http://192.168.0.151/database/login.php";
                String url3 = "http://192.168.85.152/database/req.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(PostActivity.this, "posted successfully!", Toast.LENGTH_SHORT).
                                            show();
                                }else{
                                    Toast.makeText(getApplicationContext(), response.toString().trim(), Toast.LENGTH_SHORT).show();
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
                        paramV.put("address", address);
                        paramV.put("number", number);
                        paramV.put("bloodGroup", bloodGroup);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}