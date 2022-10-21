package com.example.petfoodcatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    EditText emailRegister, passRegister;
    Button register, clearRegister, back;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        defCompo();
        resetCompo();

        //Clear button
        clearRegister.setOnClickListener(view -> {
            resetCompo();
            Toast.makeText(this, "Input cleared", Toast.LENGTH_SHORT).show();
        });

        //Back to login button
        back.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //Register button intent
        register.setOnClickListener(view -> {
            if(validateRegister()){
                String email = emailRegister.getText().toString();
                String password = passRegister.getText().toString();
                if (!sqliteHelper.isEmailExists(email)) {
                    sqliteHelper.addUser(new User(email, password));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Snackbar.LENGTH_LONG);
                }
                else {
                    Toast.makeText(this, "User has already exists with the same email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //assign variables to xml components
    private void defCompo(){
        emailRegister = findViewById(R.id.txtEmailRegister);
        passRegister = findViewById(R.id.txtPassRegister);
        register = findViewById(R.id.btnRegister);
        clearRegister = findViewById(R.id.btnClearRegister);
        back = findViewById(R.id.btnBackToLogin);
    }

    private void resetCompo(){
        emailRegister.getText().clear();
        passRegister.getText().clear();
        emailRegister.requestFocus();
        emailRegister.setSelection(0);
    }

    private boolean validateRegister(){
        boolean validReg = false;
        boolean valEmail = false;
        boolean valPass = false;

        String Email = emailRegister.getText().toString();
        String Password = passRegister.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Please enter a valid email!", Toast.LENGTH_SHORT).show();
            return validReg;
        }
        else {
            valEmail = true;
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_SHORT).show();
            return validReg;
        }
        else {
            if (Password.length() > 5) {
                valPass = true;
            }
            else {
                Toast.makeText(this, "Password is too short!", Toast.LENGTH_SHORT).show();
                return validReg;
            }
        }

        //If both email & password are valid, proceed
        if(valEmail && valPass){
            validReg = true;
        }
        return validReg;
    }
}