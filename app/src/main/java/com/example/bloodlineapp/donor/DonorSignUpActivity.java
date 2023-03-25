package com.example.bloodlineapp.donor;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DonorSignUpActivity extends AppCompatActivity {
    ImageView reqimg;
    Bitmap bitmap;
    String encodedImage;
    EditText lname, fname, mname, pass, bday, userage, idsno, addstreet, barangays, addtm, addcity, zipcode, mobileNum, emailadd;
    EditText num, ldate, places;
    String lastname, firstname, middlename, password, birthdate, age, sex, bloodGroup, identifyno,
            street, barangay, tm, city, code, mobileNumber, email, idno, numtimes, previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7;
    Spinner bgroups, bsex, idenno;
    Spinner prev,grp1, grp2, grp3, grp4, grp5, grp6, grp7;
    TextView backButton, textError;
    Button signUpButton;
    ProgressDialog loadBar;
    Button btn_date, btnTimePicker, btn_date1;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_sign_up);


        lastname = firstname = middlename = password = birthdate = age = sex = bloodGroup = identifyno = street = barangay = tm = city = code = mobileNumber = email = idno = "";
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

        lname = (EditText) findViewById(R.id.lastname);
        fname = (EditText) findViewById(R.id.firstname);
        mname = (EditText) findViewById(R.id.middlename);
        pass = (EditText) findViewById(R.id.password);
        bday = (EditText) findViewById(R.id.birthdate);
        btn_date = (Button) findViewById(R.id.btn_date);
        userage = (EditText) findViewById(R.id.age);
        bsex = (Spinner) findViewById(R.id.sex);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);
        idenno = (Spinner) findViewById(R.id.identifyno);
        addstreet = (EditText) findViewById(R.id.street);
        barangays = (EditText) findViewById(R.id.barangay);
        addtm = (EditText) findViewById(R.id.tm);
        addcity = (EditText) findViewById(R.id.city);
        zipcode = (EditText) findViewById(R.id.code);
        mobileNum = (EditText) findViewById(R.id.mobileNumber);
        emailadd = (EditText) findViewById(R.id.email);
        idsno = (EditText) findViewById(R.id.idno);
        reqimg = (ImageView) findViewById(R.id.reqimg);
        loadBar = new ProgressDialog(this);
        signUpButton = (Button) findViewById(R.id.signUpButton);


        backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorSignUpActivity.this, LoginActivity.class);
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(DonorSignUpActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    bday.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
        btn_date1 = (Button) findViewById(R.id.btn_date1);
        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_date1) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(DonorSignUpActivity.this,
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
        Button selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(DonorSignUpActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        signUpButton.setOnClickListener(View -> {
            String lastname = lname.getText().toString();
            String firstname = fname.getText().toString();
            String middlename = mname.getText().toString();
            String password = pass.getText().toString();
            String birthdate = bday.getText().toString();
            String age = userage.getText().toString();
            String sex = bsex.getSelectedItem().toString();
            String bloodGroup = bgroups.getSelectedItem().toString();
            String identifyno = idenno.getSelectedItem().toString();
            String street = addstreet.getText().toString();
            String barangay = barangays.getText().toString();
            String tm = addtm.getText().toString();
            String city = addcity.getText().toString();
            String code = zipcode.getText().toString();
            String mobileNumber = mobileNum.getText().toString();
            String email = emailadd.getText().toString();
            String idno = idsno.getText().toString();
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
            if (isValied(lastname, firstname, middlename, password, birthdate, age, sex, bloodGroup, identifyno,
                    street, barangay, tm, city, code, mobileNumber, email, idno, numtimes,previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7)) {
                registerUser(lastname, firstname, middlename, password, birthdate, age, sex, bloodGroup, identifyno,
                        street, barangay, tm, city, code, mobileNumber, email, idno, numtimes,previous, lastdate,place, group1, group2, group3, group4, group5, group6, group7);
            }

        });
    }

    private void registerUser(String lastname, String firstname, String middlename, String password, String birthdate, String age, String sex, String bloodGroup, String identifyno, String street, String barangay, String tm, String city, String code,
                              String mobileNumber, String email, String idno, String numtimes, String previous, String lastdate, String place, String group1, String group2, String group3, String group4, String group5, String group6, String group7) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.1.34/admin1/signup.php";
        String url1 = "http://192.168.0.112/admin1/signup.php";
        String url2 = "http://192.168.0.151/database/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,response -> {
            if (response.equals("success")) {
                showMessage("Registration Successfully");
                Intent intent = new Intent(DonorSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                showMessage("Registration Successfully");
                Intent intent = new Intent(DonorSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, error -> {
        showMessage("Please check your internet connection");
        Log.d("VOLLEY", error.getMessage());
        Intent intent = new Intent(DonorSignUpActivity.this, DonorSignUpActivity.class);
        startActivity(intent);
    }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("lastname", lastname);
                params.put("firstname", firstname);
                params.put("middlename", middlename);
                params.put("sex", sex);
                params.put("birthdate", birthdate);
                params.put("age", age);
                params.put("street", street);
                params.put("barangay", barangay);
                params.put("tm", tm);
                params.put("city", city);
                params.put("code", code);
                params.put("mobileNumber", mobileNumber);
                params.put("email", email);
                params.put("bloodGroup", bloodGroup);
                params.put("password", password);
                params.put("identifyno", identifyno);
                params.put("idno", idno);
                params.put("reqimg", encodedImage);
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

    public void SignIn(View view){
        Intent intent = new Intent(DonorSignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void showMessage(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
    private boolean isValied(String lastname, String firstname, String middlename, String password, String birthdate, String age, String sex, String bloodGroup, String identifyno,
                             String street, String barangay, String tm, String city, String code, String mobileNumber, String email, String idno,
                             String numtimes, String previous, String lastdate, String place, String group1, String group2, String group3, String group4, String group5, String group6, String group7) {
        if(lastname.isEmpty()){
            lname.requestFocus();
            lname.setError("Please enter lastname");
            return false;
        }
        if(!lastname.matches("[a-zA-Z]+")){
          lname.requestFocus();
          lname.setError("Enter only alphabetical character");
          return false;
      }
        if(firstname.isEmpty()){
            fname.requestFocus();
            fname.setError("Please enter firstname");
            return false;
        }
        if(!firstname.matches("[a-zA-Z]+")){
            fname.requestFocus();
            fname.setError("Enter only alphabetical character");
            return false;
        }
        if(middlename.isEmpty()){
            mname.requestFocus();
            mname.setError("Please enter middlename");
            return false;
        }
        if(!middlename.matches("[a-zA-Z]+")){
            mname.requestFocus();
            mname.setError("Enter only alphabetical character");
            return false;
        }
        if(password.isEmpty()){
            pass.requestFocus();
            pass.setError("Please enter password");
            return false;
        }
        if(password.length()<6){
            pass.requestFocus();
            pass.setError("Password be between 6 character");
            return false;
        }
        if(birthdate.isEmpty()){
            bday.requestFocus();
            bday.setError("Please enter birthdate");
            return false;
        }
        if(age.isEmpty()){
            userage.requestFocus();
            userage.setError("Please enter age");
            return false;
        }
        if(sex.isEmpty()){
            showMessage("Please enter sex");
            return false;
        }
        if(bloodGroup.isEmpty()){
            showMessage("Please enter blood group");
            return false;
        }
        if(identifyno.isEmpty()){
            showMessage("Please enter identifyno");
            return false;
        }
        if(street.isEmpty()){
            addstreet.requestFocus();
            addstreet.setError("Please enter street");
            return false;
        }
        if(barangay.isEmpty()){
            barangays.requestFocus();
            barangays.setError("Please enter barangay");
            return false;
        }
        if(tm.isEmpty()){
            addtm.requestFocus();
            addtm.setError("Please enter town/municipality");
            return false;
        }
        if(city.isEmpty()){
            addcity.requestFocus();
            addcity.setError("Please enter city");
            return false;
        }
        if(code.isEmpty()){
            zipcode.requestFocus();
            zipcode.setError("Please enter zip code");
            return false;
        }
        if(mobileNumber.isEmpty()){
            mobileNum.requestFocus();
            mobileNum.setError("Please enter mobile number");
            return false;
        }
        if(!mobileNumber.matches("^[0-9]{11,12}")){
            mobileNum.requestFocus();
            mobileNum.setError("Correct Format: +639xxxxxxxxx");
            return false;
        }
        if(email.isEmpty()){
            emailadd.requestFocus();
            emailadd.setError("Please enter email address");
            return false;
        }
        if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            emailadd.requestFocus();
            emailadd.setError("Enter valid email address");
            return false;
        }
        if(idno.isEmpty()) {
            idsno.requestFocus();
            idsno.setError("Please enter identification");
            return false;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                reqimg.setImageBitmap(bitmap);
                imageStore(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }
}

