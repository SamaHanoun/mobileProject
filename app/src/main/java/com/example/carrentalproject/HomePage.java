package com.example.carrentalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    private ImageView logo;
    private ImageButton menuIcon;
    private Button btnSignIn, btnRegister, btnGooglePlay, btnAppStore;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        /*btnSignIn = findViewById(R.id.btn_sign_in);
        btnRegister = findViewById(R.id.btn_register);
        searchBar = findViewById(R.id.search_bar);
        btnGooglePlay = findViewById(R.id.btn_google_play);
        btnAppStore = findViewById(R.id.btn_app_store);*/

        // Set click listeners
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle sign in
                Toast.makeText(HomePage.this, "Sign In clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle register
                Toast.makeText(HomePage.this, "Register clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnGooglePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Google Play download
                Toast.makeText(HomePage.this, "Download on Google Play", Toast.LENGTH_SHORT).show();
            }
        });

        btnAppStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle App Store download
                Toast.makeText(HomePage.this, "Download on App Store", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
