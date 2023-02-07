package com.example.bloodlineapp.recipient;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    EditText name, add, num, hname, cc_name;
    String username, address, number, bloodGroup, hospital, ccname;
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
        hname = (EditText) findViewById(R.id.hospital);
        cc_name = (EditText) findViewById(R.id.ccname);
        bgroups = (Spinner) findViewById(R.id.bloodGroup);
        reqimg = (ImageView) findViewById(R.id.reqimg);

        loadBar = new ProgressDialog(this);

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
                hospital = String.valueOf(hname.getText());
                ccname = String.valueOf(cc_name.getText());
                bloodGroup = String.valueOf(bgroups.getSelectedItem());

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.47/admin1/req.php";
                String url1 = "http://192.168.43.71/admin1/req.php";
                String url2 = "http://192.168.0.151/database/req.php";
                String url3 = "https://bloodlineapp.000webhostapp.com/req_new.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(PostActivity.this, "You Have Posted Successfully!", Toast.LENGTH_SHORT).
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
                        paramV.put("hospital", hospital);
                        paramV.put("ccname", ccname);
                        paramV.put("bloodGroup", bloodGroup);
                        paramV.put("reqimg", encodedImage);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
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