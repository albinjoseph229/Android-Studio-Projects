package com.example.foodappui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Sample data
        ArrayList<FoodItem> foodList = new ArrayList<>();

        foodList.add(new FoodItem("Biryani", "Spicy rice dish with meat or vegetables", R.drawable.biryani));
        foodList.add(new FoodItem("Chana", "Delicious Chana Masala", R.drawable.chana));
        foodList.add(new FoodItem("Chatt", "Spicy and Tangy Chatt", R.drawable.chatt));
        foodList.add(new FoodItem("Chicken Tikka", "Juicy Chicken Tikka", R.drawable.chickentikka));
        foodList.add(new FoodItem("Chole", "North Indian chickpea curry", R.drawable.chole));
        foodList.add(new FoodItem("Dosa", "South Indian Dosa", R.drawable.dosha));
        foodList.add(new FoodItem("Golgappa", "Crunchy Golgappa", R.drawable.golgappa));
        foodList.add(new FoodItem("Kerala", "Traditional Kerala Meal", R.drawable.kerala));
        foodList.add(new FoodItem("Roti", "Indian Roti", R.drawable.roti));
        foodList.add(new FoodItem("Sadya", "Grand Sadya Meal", R.drawable.sadya));

        FoodListAdapter adapter = new FoodListAdapter(this, foodList);

        ListView listView = findViewById(R.id.foodListView);
        listView.setAdapter(adapter);
    }
}