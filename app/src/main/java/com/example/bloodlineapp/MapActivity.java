package com.example.bloodlineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bloodlineapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    EditText etSource, etDestination;
    Button btTrack;

    GoogleMap map;
    ArrayList<LatLng> arrayList= new ArrayList<LatLng>();
    LatLng RedCross = new LatLng(16.41207,  120.59623);
    LatLng SLU = new LatLng(16.417512810616593, 120.59784285392509);
    LatLng Notre = new LatLng(16.415608893074104, 120.5986260589845);
    LatLng BGH = new LatLng(16.401215380348432, 120.59571481210413);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                if (sSource.equals("") && sDestination.equals("")){
                    Toast.makeText(getApplicationContext()
                            , "Enter both location", Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment .getMapAsync(this);
        arrayList.add(RedCross);
        arrayList.add(SLU);
        arrayList.add(Notre);
        arrayList.add(BGH);


        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        try{
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/"
                    + sDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
      map.addMarker(new MarkerOptions().position(RedCross).title("Philippine RedCross Baguio"));

      map.addMarker(new MarkerOptions().position(SLU).title("SLU"));
      map.addMarker(new MarkerOptions().position(Notre).title("Notre"));
      map.addMarker(new MarkerOptions().position(BGH).title("BGH"));
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(RedCross, 18));
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(SLU, 18));
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(Notre, 18));
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(BGH, 18));
    }

}