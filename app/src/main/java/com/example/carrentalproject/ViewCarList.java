package com.example.carrentalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrentalproject.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewCarList extends AppCompatActivity {

    private ListView carListView;
    private List<Car> carList;
    private ArrayAdapter<Car> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

        carListView = findViewById(R.id.recycler_view);
        carList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carList);
        carListView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/get_cars.php";//

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
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
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewCarList.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);

        carListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car selectedCar = carList.get(position);
                Intent intent = new Intent(ViewCarList.this, CarDetailsActivity.class);
                intent.putExtra("selectedCar", (CharSequence) selectedCar);
                startActivity(intent);
            }
        });
    }
}
