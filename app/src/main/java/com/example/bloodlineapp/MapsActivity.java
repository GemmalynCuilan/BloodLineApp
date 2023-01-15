package com.example.bloodlineapp;

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

import com.example.bloodlineapp.donor.HomeActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements  OnMapReadyCallback {
    EditText etSource, etDestination;
    Button btTrack;
    private GoogleMap mMap;
    ArrayList<LatLng> arrayList= new ArrayList<LatLng>();
    LatLng RedCross = new LatLng(16.41207,  120.59623);
    LatLng SLU = new LatLng(16.417512810616593, 120.59784285392509);
    LatLng Notre = new LatLng(16.415491943954716, 120.59865809915024);
    LatLng BGH = new LatLng(16.401215380348432, 120.59571481210413);

    ArrayList<String> title = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        ImageButton arrowBack = (ImageButton) findViewById(R.id.arrowback);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        SupportMapFragment supportMapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap);
       supportMapFragment.getMapAsync(this);
        arrayList.add(RedCross);
        arrayList.add(SLU);
        arrayList.add(Notre);
        arrayList.add(BGH);
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
    public void onMapReady(final GoogleMap googleMap) {
        mMap= googleMap;
        mMap.addMarker(new MarkerOptions().position(RedCross).title("Philippine RedCross Baguio"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(RedCross, 18.0f));

        mMap.addMarker(new MarkerOptions().position(SLU).title("Saint Louis University Sacred Heart Medical Center"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SLU, 18.0f));

        mMap.addMarker(new MarkerOptions().position(Notre).title("Notre Dame de Chartres Hospital"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Notre, 18.0f));

        mMap.addMarker(new MarkerOptions().position(BGH).title("Baguio General Hospital and Medical Center"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(BGH, 18.0f));
       mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
           @Override
           public boolean onMarkerClick(Marker marker) {
               String markerTitle = marker.getTitle();
               if (marker.getTitle().equals("Philippine RedCross Baguio")) {
                   Uri uri = Uri.parse("google.streetview:cbll=16.412104635914055,120.59622564456369");
                   Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                   intent.setPackage("com.google.android.apps.maps");
                   startActivity(intent);

               }
               if(marker.getTitle().equals("Saint Louis University Sacred Heart Medical Center")){
                   Uri uri = Uri.parse("google.streetview:cbll=16.416910076646563,120.59768154093732");
                   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                   intent.setPackage("com.google.android.apps.maps");
                   startActivity(intent);
               }
               if(marker.getTitle().equals("Notre Dame de Chartres Hospital")){
                   Uri uri = Uri.parse("google.streetview:cbll=16.415490006564436,120.59933733976439");
                   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                   intent.setPackage("com.google.android.apps.maps");
                   startActivity(intent);
               }
               else if(marker.getTitle().equals("Baguio General Hospital and Medical Center")){
                   Uri uri = Uri.parse("google.streetview:cbll=16.401146311322208,120.59549188396777");
                   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                   intent.setPackage("com.google.android.apps.maps");
                   startActivity(intent);
               }

               return false;
           }
       });
    }
}