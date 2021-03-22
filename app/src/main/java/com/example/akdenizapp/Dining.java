package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dining extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);

        /*
        String date = getIntent().getStringExtra("date");
        String firstMeal = getIntent().getStringExtra("first_meal");
        String secondMeal = getIntent().getStringExtra("second_meal");
        String thirdMeal = getIntent().getStringExtra("third_meal");
        String fourthMeal = getIntent().getStringExtra("third_meal");
        */

        TextView today_date= findViewById(R.id.today_date);
        //today_date.setText(date);
        today_date.setText("09.03.21");
    }
}