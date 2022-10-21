package com.example.petfoodcatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {

    private int a;
    TextView itemName, itemPrice, itemDesc;
    ImageView itemImage;
    Button closeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        itemName = findViewById(R.id.detailName);
        itemPrice = findViewById(R.id.detailPrice);
        itemImage = findViewById(R.id.detailImage);
        itemDesc = findViewById(R.id.detailDesc);
        closeDetail = findViewById(R.id.btnCloseDetail);


        //Get intent bundle id, to display clicked item data
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            a = bundle.getInt("Id");
            itemName.setText(MyItem.names[a]);
            itemPrice.setText(MyItem.prices[a]);
            itemImage.setImageResource(MyItem.images[a]);
            itemDesc.setText(MyItem.descs[a]);
        }

        //Button to return to catalog activity
        closeDetail.setOnClickListener(view ->{
            finish();
        });
    }
}