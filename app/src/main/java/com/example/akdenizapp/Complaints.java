package com.example.akdenizapp;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class Complaints extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    String [] Complaints={"şikayet 1", "şikayet 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        ListView listView =(ListView)findViewById(R.id.listView_list_of_complaints_student);
        ArrayAdapter<String> data=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, Complaints);
        listView.setAdapter(data);
        ((Button) findViewById(R.id.button_create_student_new_complaint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(com.example.akdenizapp.Complaints.this, NewComplaint.class);
                startActivity(t);
            }
        });

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    Intent t = new Intent(Complaints.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Complaints.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Complaints.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Complaints.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Complaints.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Complaints.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Complaints.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Complaints.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Complaints.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Complaints.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Complaints.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });

    /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent t = new Intent(Complaints.this,.class);
                startActivity(t);
            }
        });*/

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}