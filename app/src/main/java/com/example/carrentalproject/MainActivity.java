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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

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

                String url = "http://localhost:80/comp4310 mobile/signIn.php";

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JSONObject data = new JSONObject();

                try {
                    data.put("email", email);
                    data.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("jsonObjectData", data.toString());
                JsonRequest jsonRequest = new JsonRequest(Request.Method.POST, url, data.toString(),
                        new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("jsonObjectData", response.toString());
                        try {
                            JSONObject obj = response.getJSONArray(0).getJSONObject(0);

                            String uid = obj.getString("uid");
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
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        Log.d("jsonObjectData", error.toString());
                    }

                }) {
                    @Override
                    protected Response parseNetworkResponse(NetworkResponse networkResponse) {
                        return null;
                    }
                };
                queue.add(jsonRequest);


            });

            return insets;
        });
    }
}