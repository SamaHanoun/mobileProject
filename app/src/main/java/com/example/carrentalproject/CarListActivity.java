package com.example.carrentalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity implements carListAdapter.OnCarClickListener {

    private RecyclerView recyclerView;
    private carListAdapter carListAdapter;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carList = new ArrayList<>();
        carListAdapter = new carListAdapter(carList, this);
        recyclerView.setAdapter(carListAdapter);

        fetchCarData();
    }

    private void fetchCarData() {
        String url = ""; // fillllll

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject carObject = response.getJSONObject(i);
                                Car car = new Car();
                                car.setId(carObject.getInt("cid"));
                                car.setType(carObject.getString("type"));
                                car.setModel(carObject.getString("model"));
                                car.setManufacturer(carObject.getString("manufacturer"));
                                car.setProdYear(carObject.getString("prodYear"));
                                car.setLicense(carObject.getString("license"));
                                car.setPrice(carObject.getDouble("price"));
                                car.setBooked(Boolean.parseBoolean(carObject.getString("booked")));
                                car.setCarCondition(carObject.getString("carCondition"));
                                car.setMileage(carObject.getInt("mileage"));

                                carList.add(car);
                            }
                            carListAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CarListActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onCarClick(int position) {
        Car selectedCar = carList.get(position);
        Intent intent = new Intent(this, CarDetailsActivity.class);
        intent.putExtra("selectedCar", (CharSequence) selectedCar);
        startActivity(intent);
    }
}
