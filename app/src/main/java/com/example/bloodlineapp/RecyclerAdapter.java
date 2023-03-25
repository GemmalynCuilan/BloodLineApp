package com.example.bloodlineapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapp.model.Event;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Event> events = new ArrayList<>();

    public RecyclerAdapter (Context context,List<Event> events){
        this.mContext = context;
        this.events = events;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView event_name, event_description,event_venue, event_start_date, event_end_date, event_time;
        CardView card_view;
        public MyViewHolder (View view) {
            super(view);

            event_name = view.findViewById(R.id.event_name);
            event_description = view.findViewById(R.id.event_description);
            event_venue = view.findViewById(R.id.event_venue);
            event_start_date = view.findViewById(R.id.event_start_date);
            event_end_date = view.findViewById(R.id.event_end_date);
            event_time = view.findViewById(R.id.event_time);

            card_view = view.findViewById(R.id.card_view);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Event event = events.get(position);
        holder.event_name.setText("Title: "+event.getEvent_name());
        holder.event_description.setText("Description:"+ event.getEvent_description());
        holder.event_venue.setText("Venue:"+ event.getEvent_venue());
        holder.event_start_date.setText("Event Start Date:" +event.getEvent_start_date());
        holder.event_end_date.setText("Event End Date:" + event.getEvent_end_date());
        holder.event_time.setText("Time:"+ event.getEvent_time());

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, MainActivity2.class);
                intent.putExtra("event_name",events.get(position).getEvent_name());
                intent.putExtra("event_description",events.get(position).getEvent_description());
                intent.putExtra("event_venue",events.get(position).getEvent_venue());
                intent.putExtra("event_start_date",events.get(position).getEvent_start_date());
                intent.putExtra("event_end_date",events.get(position).getEvent_end_date());
                intent.putExtra("event_time",events.get(position).getEvent_time());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return events.size();
    }
}