package com.example.carrentalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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

            String url = "http:10.0.2.2:80/comp4310 mobile/signIn.php";

            RequestQueue queue = Volley.newRequestQueue(this);

            btnSignin = findViewById(R.id.btnSignin);
            btnSignin.setOnClickListener(view -> {
                edtEmail = findViewById(R.id.edtEmail);
                edtPassword = findViewById(R.id.edtPassword);

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                JSONObject data = new JSONObject();
                try {
                    data.put("email", email);
                    data.put("password", password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<String> todos = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                todos.add(obj.getString("uid"));
                            }catch(JSONException exception){
                                Log.d("taggg", exception.toString());
                            }
                        }
                        Log.d("taggg", todos.toString());
                        Intent intent = new Intent(MainActivity.this, searchCars.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("taggg", error.toString());
                    }
                });

                queue.add(request);

            });

            return insets;

        });

    }
}
