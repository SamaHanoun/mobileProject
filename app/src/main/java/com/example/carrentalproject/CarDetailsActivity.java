package com.example.carrentalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CarDetailsActivity extends AppCompatActivity {

    private ImageView carImage;
    private TextView carDetails;
    private Button btnRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_rent);

        carImage = findViewById(R.id.car_image);
        carDetails = findViewById(R.id.car_details);
        btnRent = findViewById(R.id.btnRent);

        Car selectedCar = (Car) getIntent().getSerializableExtra("selectedCar");

        if (selectedCar != null) {
            carDetails.setText(
                    "Model: " + selectedCar.getModel() + "\n" +
                            "Manufacturer: " + selectedCar.getManufacturer() + "\n" +
                            "Production Year: " + selectedCar.getProdYear() + "\n" +
                            "Price: $" + selectedCar.getPrice() + "\n" +
                            "Condition: " + selectedCar.getCarCondition() + "\n" +
                            "Mileage: " + selectedCar.getMileage()
            );

            // Load car image using a library like Picasso or Glide
            // Picasso.get().load("http://your_image_url/" + selectedCar.getImage()).into(carImage);
        }

        btnRent.setOnClickListener(v -> {
            // Handle rent button click
            Toast.makeText(CarDetailsActivity.this, "Renting Car: " + selectedCar.getModel(), Toast.LENGTH_SHORT).show();
        });
    }
}
