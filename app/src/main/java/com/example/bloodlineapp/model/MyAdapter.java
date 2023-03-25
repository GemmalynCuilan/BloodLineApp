package com.example.bloodlineapp.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapp.PrepareActivity;
import com.example.bloodlineapp.R;
import com.example.bloodlineapp.bloodInfo;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    ArrayList<Model> data;
    Context context;
    public MyAdapter(ArrayList<Model> data, Context context){
        this.data = data;
        this.context=context;

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Model model = data.get(position);
        holder.textView.setText(data.get(position).getName());
        holder.textView2.setText(data.get(position).getDescription());
        holder.imageView.setImageResource(data.get(position).getImage());

        switch(position) {
            case 0:
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), bloodInfo.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), PrepareActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), PrepareActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView2;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}




