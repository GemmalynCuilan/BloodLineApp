package com.example.bloodlineapp.donor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView backButton, textError, forgotpassword;
    EditText emailAdd, pass;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = password = "";
        emailAdd = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);


        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });
        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DonorSignUpActivity.class);
                startActivity(intent);
            }
        });
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(View -> {

            String email = emailAdd.getText().toString();
            String password = pass.getText().toString();

            if (isValied(email, password)) {
                loginUser(email, password);
            }

        });
    }

    private void loginUser(String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.1.34/admin1/login.php";
        String url1 = "http://192.168.0.112/admin1/login.php";
        String url2 = "http://192.168.0.151/database/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if (response.equals("success")) {
                showMessage("Login Successfully");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                showMessage("Error");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, error -> {
            showMessage("Login");
            Log.d("VOLLEY", error.getMessage());
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Login(View view) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isValied(String email, String password) {
        if (email.isEmpty()) {
            emailAdd.requestFocus();
            emailAdd.setError("Please enter email address");
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            emailAdd.requestFocus();
            emailAdd.setError("Enter valid email address");
            return false;
        }
        if (password.isEmpty()) {
            pass.requestFocus();
            pass.setError("Please enter password");
            return false;
        }
        if (password.length() < 6) {
            pass.requestFocus();
            pass.setError("Password be between 6 character");
            return false;
        }
        return true;
    }
}