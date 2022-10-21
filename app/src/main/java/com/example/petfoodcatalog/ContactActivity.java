package com.example.petfoodcatalog;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ContactActivity extends AppCompatActivity {

    Button btnEmail, btnPhone, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnEmail = findViewById(R.id.btnEmail);
        btnPhone = findViewById(R.id.btnPhone);
        btnClose = findViewById(R.id.btnCloseContact);


        //Clickable email button to send email to the developer
        btnEmail.setOnClickListener(view ->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "s31180013@gmail.com" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "PetFoodCatalog - Help");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(intent, ""));
        });

        //Clickable phone button to phone the developer
        btnPhone.setOnClickListener(view ->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+6281369762805"));
            startActivity(intent);
        });

        //Button to return to catalog activity
        btnClose.setOnClickListener(view ->{
            finish();
        });
    }
}