package com.example.carrentalproject;

import static java.util.Locale.filter;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class searchCars extends AppCompatActivity {

    private SearchView searchView;
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_cars);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            //removing the status bar
            insets = insets.replaceSystemWindowInsets(
                    systemBars.left, 0, systemBars.right, systemBars.bottom
            );

            itemList = new ArrayList<>();

            //finding the search view and setting the listener
            searchView = findViewById(R.id.search);
            searchView.clearFocus();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    filterText(newText);
                    return true;
                }
            });

            return insets;
        });
    }

    private void filterText(String text) {

        List<Item> filteredList = new ArrayList<>();

        }
        /*if (filteredList.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        }*/

    }
//}