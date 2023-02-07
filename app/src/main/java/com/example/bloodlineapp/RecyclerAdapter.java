package com.example.bloodlineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, dtevent, name, description;

        public MyViewHolder (View view){
            super(view);
            title = view.findViewById(R.id.title);
            dtevent= view.findViewById(R.id.dtevent);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
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
        holder.title.setText(event.getTitle());
        holder.dtevent.setText("When:"+ event.getDtevent());
        holder.name.setText("Where:" +event.getName());
        holder.description.setText("Agenda:" + event.getDescription());


    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}