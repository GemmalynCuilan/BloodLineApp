package com.example.bloodlineapp.donor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DonorActivity extends AppCompatActivity {
    TextView backButton;
    Button btn_date, btnTimePicker, savebtn;
    private int mYear, mMonth, mDay;
    EditText num, ldate, places;
    String numtimes,previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7;
    Spinner prev,grp1, grp2, grp3, grp4, grp5, grp6, grp7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        numtimes = previous = lastdate = place = group1 = group2 = group3 = group4 = group5 = group6 = group7 = "";
        num = (EditText) findViewById(R.id.numtimes);
        ldate = (EditText) findViewById(R.id.lastdate);
        places = (EditText) findViewById(R.id.place);
        prev = (Spinner) findViewById(R.id.previous);
        grp1 = (Spinner) findViewById(R.id.group1);
        grp2 = (Spinner) findViewById(R.id.group2);
        grp3 = (Spinner) findViewById(R.id.group3);
        grp4 = (Spinner) findViewById(R.id.group4);
        grp5 = (Spinner) findViewById(R.id.group5);
        grp6 = (Spinner) findViewById(R.id.group6);
        grp7 = (Spinner) findViewById(R.id.group7);
        savebtn = (Button) findViewById(R.id.savebtn);

        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorActivity.this, DonorSignUpActivity.class);
                startActivity(intent);
            }
        });
        btn_date = (Button) findViewById(R.id.btn_date);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_date) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(DonorActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    ldate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
        savebtn.setOnClickListener(View ->{
            String numtimes = num.getText().toString();
            String previous = prev.getSelectedItem().toString();
            String lastdate = ldate.getText().toString();
            String place = places.getText().toString();
            String group1 = grp1.getSelectedItem().toString();
            String group2 = grp2.getSelectedItem().toString();
            String group3 = grp3.getSelectedItem().toString();
            String group4 = grp4.getSelectedItem().toString();
            String group5 = grp5.getSelectedItem().toString();
            String group6 = grp6.getSelectedItem().toString();
            String group7 = grp7.getSelectedItem().toString();
            if (isValied(numtimes,previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7)) {
                history(numtimes,previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7);
            }
        });
    }

    private void history(String numtimes, String previous, String lastdate, String place, String group1, String group2, String group3, String group4, String group5, String group6, String group7) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.1.34/admin1/history.php";
        String url1 = "http://192.168.0.112/admin1/history.php";
        String url2 = "http://192.168.0.151/database/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if (response.equals("success")) {
                showMessage("Registration Successfully");
                Intent intent = new Intent(DonorActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                showMessage("User already exists");
                Intent intent = new Intent(DonorActivity.this, DonorActivity.class);
                startActivity(intent);
            }
        }, error -> {
            showMessage("Please check your internet connection");
            Log.d("VOLLEY", error.getMessage());
            Intent intent = new Intent(DonorActivity.this, DonorSignUpActivity.class);
            startActivity(intent);
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("numtimes", numtimes);
                params.put("previous", previous);
                params.put("lastdate", lastdate);
                params.put("place", place);
                params.put("group1", group1);
                params.put("group2", group2);
                params.put("group3", group3);
                params.put("group4", group4);
                params.put("group5", group5);
                params.put("group6", group6);
                params.put("group7", group7);
                return params;
            }
        };
        queue.add(stringRequest);
    }
    public void History(View view){
        Intent intent = new Intent(DonorActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void showMessage(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
    private boolean isValied(String numtimes, String previous, String lastdate, String place, String group1, String group2, String group3, String group4, String group5, String group6, String group7) {
        if(numtimes.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(previous.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(lastdate.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(place.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group1.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group2.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group3.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group4.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group5.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group6.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        if(group7.isEmpty()){
            showMessage("Yes or No");
            return false;
        }
        return true;
    }
}