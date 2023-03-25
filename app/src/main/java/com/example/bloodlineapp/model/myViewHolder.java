package com.example.bloodlineapp.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapp.R;

public class myViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    ImageView imageView;
    TextView textView, textView2;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=(ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.textView);
        textView2 = (TextView) itemView.findViewById(R.id.textView2);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}

