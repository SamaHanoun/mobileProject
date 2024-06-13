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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

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

        String url = "localhost/comp4310 mobile/addUser.php";

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
                RequestQueue queue = Volley.newRequestQueue(activity_register.this);
                queue.add(jsonRequest);
            }
        });
    }
}