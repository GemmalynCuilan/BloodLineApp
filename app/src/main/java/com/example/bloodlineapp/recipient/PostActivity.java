package com.example.bloodlineapp.recipient;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    ImageView reqimg;
    Bitmap bitmap;
    String encodedImage;
    EditText lname, fname, addstreet, barangays, addtm, addcity,  units,relatives,num, cc_name,hname, emailAdd, reasons;
    String lastname, firstname, sex, street, barangay, tm,city, unit, relative, renum, bloodGroup, ccname,hospital, email, reason;
    Spinner bgroups, bsex;
    TextView textError;
    Button postButton;
    ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        lname = (EditText) findViewById(R.id.lastname);
        fname = (EditText) findViewById(R.id.firstname);
        emailAdd = (EditText) findViewById(R.id.email);
        bsex = (Spinner) findViewById(R.id.sex);
        addstreet = (EditText) findViewById(R.id.street);
        barangays = (EditText) findViewById(R.id.barangay);
        addtm = (EditText) findViewById(R.id.tm);
        addcity = (EditText) findViewById(R.id.city);
        units = (EditText) findViewById(R.id.unit);
        relatives = (EditText) findViewById(R.id.relative);
        num = (EditText) findViewById(R.id.renum);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);
        cc_name = (EditText) findViewById(R.id.ccname);
        hname = (EditText) findViewById(R.id.hospital);
        reqimg = (ImageView) findViewById(R.id.reqimg);
        reasons = (EditText) findViewById(R.id.reason);

        loadBar = new ProgressDialog(this);
        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback_profile);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });
        Button selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(PostActivity.this)
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


        postButton = (Button) findViewById(R.id.postButton);
        postButton.setOnClickListener(View -> {


            String lastname = lname.getText().toString();
            String firstname = fname.getText().toString();
            String email = emailAdd.getText().toString();
            String sex = bsex.getSelectedItem().toString();
            String street = addstreet.getText().toString();
            String barangay = barangays.getText().toString();
            String tm = addtm.getText().toString();
            String city = addcity.getText().toString();
            String unit = units.getText().toString();
            String relative = relatives.getText().toString();
            String renum = num.getText().toString();
            String bloodGroup = bgroups.getSelectedItem().toString();
            String ccname = cc_name.getText().toString();
            String hospital = hname.getText().toString();
            String reason = reasons.getText().toString();

            if (isValied(lastname, firstname, sex, street, barangay, tm,city, unit, relative, renum, bloodGroup, ccname,hospital, email, reason)) {
                postUser(lastname, firstname, sex, street, barangay, tm,city, unit, relative, renum, bloodGroup, ccname,hospital, email, reason);
            }

        });
    }

    private void postUser(String lastname, String firstname, String sex, String street, String barangay, String tm, String city, String unit, String relative, String renum, String bloodGroup, String ccname, String hospital, String email, String reason) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.1.36/admin1/req.php";
        String url1 = "http://192.168.0.112/admin1/req.php";
        String url2 = "http://192.168.0.151/database/req.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,response -> {
            if (response.equals("success")) {
                showMessage("Registration Successfully");
                Intent intent = new Intent(PostActivity.this, DashboardActivity.class);
                startActivity(intent);
            } else {
                showMessage("Error");
                Intent intent = new Intent(PostActivity.this, PostActivity.class);
                startActivity(intent);
            }
        }, error -> {
            showMessage("Please check your internet connection");
            Log.d("VOLLEY", error.getMessage());
            Intent intent = new Intent(PostActivity.this, PostActivity.class);
            startActivity(intent);
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("lastname", lastname);
                params.put("firstname", firstname);
                params.put("email", email);
                params.put("sex", sex);
                params.put("street", street);
                params.put("barangay", barangay);
                params.put("tm", tm);
                params.put("city", city);
                params.put("unit", unit);
                params.put("relative", relative);
                params.put("renum", renum);
                params.put("bloodGroup", bloodGroup);
                params.put("ccname", ccname);
                params.put("hospital", hospital);
                params.put("reason", reason);
                params.put("reqimg", encodedImage);
                return params;
            }
        };
        queue.add(stringRequest);
    }
    public void Post(View view){
        Intent intent = new Intent(PostActivity.this, PostActivity.class);
        startActivity(intent);
    }
    public void showMessage(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    private boolean isValied(String lastname, String firstname, String sex, String street, String barangay, String tm, String city, String unit, String relative, String renum, String bloodGroup, String ccname, String hospital, String email, String reason) {
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
        if(email.isEmpty()){
            emailAdd.requestFocus();
            emailAdd.setError("Please enter email address");
            return false;
        }
        if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            emailAdd.requestFocus();
            emailAdd.setError("Enter valid email address");
            return false;
        }
        if(sex.isEmpty()){
            showMessage("Please enter sex");
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
        if(unit.isEmpty()){
            units.requestFocus();
            units.setError("Please enter unit");
            return false;
        }
        if(relative.isEmpty()){
            relatives.requestFocus();
            relatives.setError("Please enter relative/guardian name");
            return false;
        }
        if(renum.isEmpty()){
            num.requestFocus();
            num.setError("Please enter relative/guardian number");
            return false;
        }
        if(!renum.matches("^[0-9]{11,12}")){
            num.requestFocus();
            num.setError("Correct Format: +639xxxxxxxxx");
            return false;
        }
        if(bloodGroup.isEmpty()){
            showMessage("Please enter blood group");
            return false;
        }

        if(ccname.isEmpty()){
            cc_name.requestFocus();
            cc_name.setError("Please enter cc");
            return false;
        }
        if(hospital.isEmpty()){
            hname.requestFocus();
            hname.setError("Please enter hospital");
            return false;
        }

        if(reason.isEmpty()) {
            reasons.requestFocus();
            reasons.setError("Please enter reason");
            return false;
        }
        if(reason.length()<250){
            reasons.requestFocus();
            reasons.setError("Limit between 250 characters");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                reqimg.setImageBitmap(bitmap);
                imageStore(bitmap);
            }catch (FileNotFoundException e){
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