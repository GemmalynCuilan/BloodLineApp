package com.example.bloodlineapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    TextView name, desc, venue, start, end, time;
    ImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name= findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        venue = findViewById(R.id.venue);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        time = findViewById(R.id.time);
        profile_image = findViewById(R.id.profile_image);

        name.setText(getIntent().getExtras().getString("event_name"));
        desc.setText(getIntent().getExtras().getString("event_description"));
        venue.setText(getIntent().getExtras().getString("event_venue"));
        start.setText(getIntent().getExtras().getString("event_start_date"));
        end.setText(getIntent().getExtras().getString("event_end_date"));
        time.setText(getIntent().getExtras().getString("event_time"));
    }
}