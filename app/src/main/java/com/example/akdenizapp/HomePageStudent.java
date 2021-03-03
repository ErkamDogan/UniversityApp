package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class HomePageStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_student);

        ((ImageButton) findViewById(R.id.announcements_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Announcements_stvs.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.events)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Events.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.complaints_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,StudentComplaint.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.shuttle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Shuttle.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.feedback)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,feedback.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.classes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Classes.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.dining)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Dining.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.clubs)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,StudentClubs.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.survey)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageStudent.this,Surveys.class);
                startActivity(t);
            }
        });
    }
}