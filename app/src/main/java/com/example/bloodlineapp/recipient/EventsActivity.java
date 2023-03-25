package com.example.bloodlineapp.recipient;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlineapp.R;
import com.example.bloodlineapp.RecyclerAdapter;
import com.example.bloodlineapp.model.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    // Variable declarations
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    private List<Event> events;
    private ProgressBar progressBar;
    private static  final String BASE_URL = "http://192.168.0.112/database/getEvents.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mToolbar = findViewById(R.id.dashboard_toolbar);
        progressBar = findViewById(R.id.progressbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        recyclerView = findViewById(R.id.events_recyclerView);
        manager = new GridLayoutManager(EventsActivity.this, 1);
        recyclerView.setLayoutManager(manager);


        events = new ArrayList<>();
        getEvents();


    }

    private void getEvents (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String event_name = object.getString("event_name");
                                String event_description = object.getString("event_description");
                                String event_venue = object.getString("event_venue");
                                String event_start_date = object.getString("event_start_date");
                                String event_end_date = object.getString("event_end_date");
                                String event_time = object.getString("event_time");


                                Event event = new Event(event_name, event_description, event_venue,event_start_date, event_end_date, event_time);
                                events.add(event);
                            }
                        }catch (Exception e){

                        }

                        mAdapter = new RecyclerAdapter(EventsActivity.this,events);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(EventsActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(EventsActivity.this).add(stringRequest);

    }

}