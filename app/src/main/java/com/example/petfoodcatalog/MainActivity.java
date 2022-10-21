package com.example.petfoodcatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView RegHere;
    EditText emailLogin, passLogin;
    Button login, clearLogin, exit;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        defCompo();
        resetCompo();

        //Clear button
        clearLogin.setOnClickListener(view -> {
            resetCompo();
            Toast.makeText(this, "Input cleared", Toast.LENGTH_SHORT).show();
        });

        //Exit button
        exit.setOnClickListener(view -> {
            finishAffinity();
        });

        //Register activity intent
        RegHere.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        //Login button intent
        login.setOnClickListener(view -> {
            if(validateLogin()){
                String email = emailLogin.getText().toString();
                String pass = passLogin.getText().toString();

                User currentUser =sqliteHelper.Authenticate(new User(email, pass));

                if (currentUser != null) {
                    //User Logged in Successfully Launch You home screen activity
                    Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    // User Logged in Failed
                    Toast.makeText(this, "Failed to log in , please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //assign variables to xml components
    private void defCompo(){
        RegHere = findViewById(R.id.textRegisterHere);
        emailLogin = findViewById(R.id.txtEmailLogin);
        passLogin = findViewById(R.id.txtPassLogin);
        login = findViewById(R.id.btnLogin);
        clearLogin = findViewById(R.id.btnClearLogin);
        exit = findViewById(R.id.btnCancel);
    }

    //default value at start for components
    private void resetCompo(){
        emailLogin.getText().clear();
        passLogin.getText().clear();
        emailLogin.requestFocus();
        emailLogin.setSelection(0);
    }

    //validate login
    private boolean validateLogin() {
        boolean validuser = false;
        boolean validEmail = false;
        boolean validPass = false;

        //Get values from EditText fields
        String Email = emailLogin.getText().toString();
        String Password = passLogin.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
            return validuser;
        }
        else {
            validEmail = true;
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_SHORT).show();
            return validuser;
        }
        else {
            if (Password.length() > 5) {
                validPass = true;
            } else {
                Toast.makeText(this, "Password is too short!", Toast.LENGTH_SHORT).show();
                return validuser;
            }
        }

        //If both email and password are valid, proceed
        if (validEmail && validPass){
            validuser = true;
        }
        return validuser;
    }
}