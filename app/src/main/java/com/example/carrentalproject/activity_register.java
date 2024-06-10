package com.example.carrentalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class activity_register extends AppCompatActivity {

    private EditText etId, etName, etEmail, etPassword, etPhone, etAddress, etLicense;
    private Button btnRegister;
    private UserRepository userRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etId = findViewById(R.id.etId);
        etPhone = findViewById(R.id.etPhone);
        btnRegister = findViewById(R.id.btnRegister);

        userRepository = new MockUserRepository(); // Use com.example.carrentalproject.MockUserRepository

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String id = etId.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(id) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(Integer.parseInt(id), name, email, password, phone, address, "", false);

        // Use com.example.carrentalproject.MockUserRepository instead of actual database call
        boolean registrationSuccessful = userRepository.registerUser(newUser);
        if (registrationSuccessful) {
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}