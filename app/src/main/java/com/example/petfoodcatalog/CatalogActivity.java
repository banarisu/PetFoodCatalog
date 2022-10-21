package com.example.petfoodcatalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<ItemModel> data;

    Button exit, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //Assign recyclerView to component
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        //Assign button to component
        exit = findViewById(R.id.btnExit);
        contact = findViewById(R.id.btnContact);

        //Button to back to login activity
        exit.setOnClickListener(view ->{
            Intent intent = new Intent(CatalogActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        //Button to go to contact activity
        contact.setOnClickListener(view ->{
            Intent intent = new Intent(CatalogActivity.this, ContactActivity.class);
            startActivity(intent);
        });

        //Assign array data for each item
        data = new ArrayList<>();
        for (int i = 0; i < MyItem.names.length; i++){
            data.add(new ItemModel(
                MyItem.names[i],
                MyItem.prices[i],
                MyItem.images[i],
                MyItem.ids[i]
            ));
        }


        // Show items using grid layout adapter
        recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        AdapterRecyclerViewGrid adapterRecyclerViewGrid = new AdapterRecyclerViewGrid(this,data);
        recyclerView.setAdapter(adapterRecyclerViewGrid);
    }
}