package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class HomePageStudent extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    public HomePageStudent() {
    }

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}