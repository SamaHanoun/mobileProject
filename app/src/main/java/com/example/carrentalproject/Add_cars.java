package com.example.carrentalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class Add_cars extends AppCompatActivity {

    private EditText etType, etManufacturer, etModel, etProdYear, etLicense, etPrice, etCarCondition, etMileage;
    private Button btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);

        etType = findViewById(R.id.etType);
        etManufacturer = findViewById(R.id.etManufacturer);
        etModel = findViewById(R.id.etModel);
        etProdYear = findViewById(R.id.etProdYear);
        etLicense = findViewById(R.id.etLicense);
        etPrice = findViewById(R.id.etPrice);
        etCarCondition = findViewById(R.id.etCarCondition);
        etMileage = findViewById(R.id.etMileage);
        btnAddCar = findViewById(R.id.btnAddCar);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });
    }

    private void addCar() {
        String type = etType.getText().toString().trim();
        String manufacturer = etManufacturer.getText().toString().trim();
        String model = etModel.getText().toString().trim();
        Date prodYear = new Date(); // You can parse the date from etProdYear as needed
        String license = etLicense.getText().toString().trim();
        double price = Double.parseDouble(etPrice.getText().toString().trim());
        String carCondition = etCarCondition.getText().toString().trim();
        int mileage = Integer.parseInt(etMileage.getText().toString().trim());

        // Validate input
        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(manufacturer) || TextUtils.isEmpty(model) ||
                TextUtils.isEmpty(license) || TextUtils.isEmpty(carCondition)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Car object
        Car newCar = new Car();
        newCar.setType(type);
        newCar.setManufacturer(manufacturer);
        newCar.setModel(model);
        newCar.setProdYear(prodYear);
        newCar.setLicense(license);
        newCar.setPrice(price);
        newCar.setCarCondition(carCondition);
        newCar.setMileage(mileage);

        // Handle saving the car to database or repository here
        // For demo purposes, just show a toast message
        Toast.makeText(this, "Car added successfully", Toast.LENGTH_SHORT).show();

        // Optionally, you can navigate back to the previous screen or perform other actions
        finish();
    }
}
