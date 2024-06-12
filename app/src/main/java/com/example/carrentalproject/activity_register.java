package com.example.carrentalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class activity_register extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etPhone, etAddress, etLicense;
    private Spinner roleSpinner;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etLicense = findViewById(R.id.etLicense);
        roleSpinner = findViewById(R.id.roleSpinner);
        btnRegister = findViewById(R.id.btnRegister);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.admin_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

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
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String licenseNum = etLicense.getText().toString().trim();
        String role = roleSpinner.getSelectedItem().toString(); // Get selected item from spinner

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(address) || TextUtils.isEmpty(licenseNum) ||
                TextUtils.isEmpty(role)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        new RegisterUserTask(name, email, password, phone, address, licenseNum, role).execute();
    }

    private class RegisterUserTask extends AsyncTask<Void, Void, String> {
        private String name, email, password, phone, address, licenseNum, role;

        public RegisterUserTask(String name, String email, String password, String phone, String address, String licenseNum, String role) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.address = address;
            this.licenseNum = licenseNum;
            this.role = role;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.0.2.2/addUser.php"); // Change to your server URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("name", name);
                jsonParam.put("email", email);
                jsonParam.put("password", password);
                jsonParam.put("phone", phone);
                jsonParam.put("address", address);
                jsonParam.put("licenseNum", licenseNum);
                jsonParam.put("role", role);

                OutputStream os = connection.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.flush();
                os.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "Error: " + responseCode;
                }
            } catch (Exception e) {
                Log.e("RegisterUserTask", "Exception: " + e.getMessage());
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                String status = jsonResponse.getString("status");
                if ("success".equals(status)) {
                    Toast.makeText(activity_register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity_register.this, "Registration failed: " + jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(activity_register.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
