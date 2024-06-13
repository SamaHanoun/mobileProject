package com.example.carrentalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

                String url = "http://10.0.2.2:80/comp4310 mobile/signIn.php";

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JSONArray data = new JSONArray();

                data.put(email);
                data.put(password);

                Log.d("jsonObjectData", data.toString());
                JsonArrayRequest request = new JsonArrayRequest(
                        Request.Method.POST, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("jsonObjectData", response.toString());

                                ArrayList<String> todos = new ArrayList<>();
                                for (int i = 0; i < 20; i++) {
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        todos.add(obj.getString("title"));

                                        /*JSONObject obj = response.getJSONObject("data");*/
                                        String uid = obj.toString();
                                        Log.d("jsonObjectData", uid);
                                        Intent intent = new Intent(
                                            MainActivity.this,
                                            HomePage.class
                                        );
                                        startActivity(intent);

                                    } catch (JSONException exception) {
                                        Log.d("jsonObjectData", exception.toString());
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError e) {
                                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                                    Log.d("jsonObjectData", e.toString());
                                }

                            }
                            });
                            queue.add(request);


                        });

                        return insets;
            });
        }
    }