package com.example.bloodlineapp.donor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;

import java.util.HashMap;
import java.util.Map;

public class PasswordActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        EditText editText = findViewById(R.id.email);
        Button button = findViewById(R.id.cbutton);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            String url =  "http://192.168.0.112/admin1/resetpassword.php";

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        if(response.equals("success")){
                                            Intent intent = new Intent(getApplicationContext(), NewPassword.class);
                                            intent.putExtra("email", editText.getText().toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                }
                            }){
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> paramV = new HashMap<>();
                                    paramV.put("email", editText.getText().toString());
                                    return paramV;
                                }
                            };
                stringRequest.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 30000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 30000;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });
                queue.add(stringRequest);
            }
        });
    }
}
