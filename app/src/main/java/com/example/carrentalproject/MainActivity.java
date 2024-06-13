package com.example.carrentalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignin;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btnRegister = findViewById(R.id.btnRegister);
            btnRegister.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, activity_register.class);
                startActivity(intent);
            });

            btnSignin = findViewById(R.id.btnSignin);
            btnSignin.setOnClickListener(view -> {
                edtEmail = findViewById(R.id.edtEmail);
                edtPassword = findViewById(R.id.edtPassword);

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();


            });

            return insets;
        });
    }}
