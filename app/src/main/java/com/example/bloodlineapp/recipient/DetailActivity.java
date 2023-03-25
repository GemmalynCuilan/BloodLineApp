package com.example.bloodlineapp.recipient;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodlineapp.R;

public class DetailActivity extends AppCompatActivity {
    TextView title, description, venue, start, end, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent =getIntent();
        String event_name = intent.getStringExtra("event_name");
        title.setText(event_name);
        String event_description = intent.getStringExtra("event_description");
        description.setText(event_description);
        String event_venue = intent.getStringExtra("event_venue");
        venue.setText(event_venue);
        String event_start_date = intent.getStringExtra("event_start_date");
        start.setText(event_start_date);
        String event_end_date = intent.getStringExtra("event_end_date");
        end.setText(event_end_date);
        String event_time = intent.getStringExtra("event_time");
        time.setText(event_time);

         title = findViewById(R.id.title);
         description = findViewById(R.id.description);
         venue = findViewById(R.id.venue);
         start = findViewById(R.id.start);
         end = findViewById(R.id.end);
         time = findViewById(R.id.time);

    }
}