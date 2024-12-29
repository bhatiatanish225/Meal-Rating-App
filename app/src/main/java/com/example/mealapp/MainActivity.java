package com.example.mealapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText restaurantName, dishName;
    private Button rateButton, saveButton;
    private RatingBar ratingBar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantName = findViewById(R.id.restaurantName);
        dishName = findViewById(R.id.dishName);
        rateButton = findViewById(R.id.rateButton);
        saveButton = findViewById(R.id.saveButton);
        ratingBar = findViewById(R.id.ratingBar);
        databaseHelper = new DatabaseHelper(this);

        // Set button click listener for rating
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setVisibility(View.VISIBLE);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if (fromUser) {
                            Toast.makeText(MainActivity.this, "Rated: " + rating + " stars!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Set button click listener for saving
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurant = restaurantName.getText().toString();
                String dish = dishName.getText().toString();
                float rating = ratingBar.getRating();

                if (restaurant.isEmpty() || dish.isEmpty() || rating == 0) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields and rate the meal.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = databaseHelper.insertData(restaurant, dish, rating);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data Saving Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
