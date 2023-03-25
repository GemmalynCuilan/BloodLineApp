package com.example.bloodlineapp.donor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodlineapp.R;
import com.example.bloodlineapp.model.Model;
import com.example.bloodlineapp.model.MyAdapter;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       adapter =new MyAdapter(dataqueue(),getApplicationContext());
       recyclerView.setAdapter(adapter);
    }
    public ArrayList<Model> dataqueue(){
        ArrayList<Model> holder = new ArrayList<>();
        Model ob1 = new Model();
        ob1.setImage(R.drawable.blood);
        ob1.setName("What can I give?");
        ob1.setDescription("Choose between plasma, blood or platelets.");
        holder.add(ob1);
        Model ob2 = new Model();
        ob2.setImage(R.drawable.tips);
        ob2.setName("Prepare and Aftercare");
        ob2.setDescription("Take a look at this handy guide to know what to expect.");
        holder.add(ob2);
        Model ob3 = new Model();
        ob3.setImage(R.drawable.req);
        ob3.setName("Requirements by Donation Type");
        ob3.setDescription("Requirements donors must meet to be eligible to donate blood based on their donation type.");
        holder.add(ob3);
        return holder;
    }
}