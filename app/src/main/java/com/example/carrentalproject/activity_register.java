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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class activity_register extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etPhone, etAddress, etLicense;
    //private EditText etId;
    private Button btnRegister;
    private UserRepository userRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String url = "http://localhost/addUser.php";

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etLicense = findViewById(R.id.etLicense);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        etName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etPhone.getText().toString(),
                        etAddress.getText().toString(),
                        etLicense.getText().toString()
                );
                JSONObject data = new JSONObject();

                try {
                    data.put("name", user.getName());
                    data.put("email", user.getEmail());
                    data.put("password", user.getPassword());
                    data.put("phone", user.getPhone());
                    data.put("address", user.getAddress());
                    data.put("license", user.getLicense());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonRequest jsonRequest = new JsonRequest(Request.Method.POST, url, data.toString(),
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object o) {
                                Log.d("php Response", "succeed");
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("php Response", "fail");
                            }
                        }
                ) {
                    @Override
                    protected Response parseNetworkResponse(NetworkResponse networkResponse) {
                        return null;
                    }
                };


            }
        });





        /*etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        tPassword = findViewById(R.id.etPassword);
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
        }*/
    }
}