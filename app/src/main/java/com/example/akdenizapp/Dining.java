package com.example.akdenizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.CDATASection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Dining extends AppCompatActivity implements HttpResponseImpl {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
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
                    Intent t = new Intent(Dining.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Dining.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Dining.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Dining.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Dining.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Dining.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Dining.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Dining.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Dining.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Dining.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Dining.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });
        /*
        String date = getIntent().getStringExtra("date");
        String firstMeal = getIntent().getStringExtra("first_meal");
        String secondMeal = getIntent().getStringExtra("second_meal");
        String thirdMeal = getIntent().getStringExtra("third_meal");
        String fourthMeal = getIntent().getStringExtra("third_meal");
        */
        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/dining.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void httpResult(String result) {
        try {
            ArrayList<DiningModel> list = new ArrayList<DiningModel>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("dinings");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new DiningModel(a.get("date").toString(),a.get("meal1").toString(),a.get("meal2").toString(),a.get("meal3").toString(),a.get("meal4").toString()));
            }
            DiningModel today =  null;
            for (DiningModel todayObj:
                 list) {
                if(todayObj.isToday()){
                 today = todayObj;
                }
            }

            if(today != null){
                TableLayout tblToday = (TableLayout) findViewById(R.id.tblToday);
                addRow(tblToday,today,0);
            }
            TableLayout tblNext = (TableLayout) findViewById(R.id.tblNext);
            Integer i = 0;
            for (DiningModel model :
                    list) {
                addRow(tblNext, model, i++);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void  addRow(TableLayout tbl, DiningModel model, Integer index){
        TableRow row= new TableRow(this);
        row.setBackgroundColor(ContextCompat.getColor(this,R.color.light_sea_green));
         row.setPadding(10,10,10,10);

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvDate = new TextView(this);
        tvDate.setText(model.date);
        tvDate.setPadding(10,10,10,10);
        tvDate.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDate.setTextColor(ContextCompat.getColor(this,R.color.white));
        tvDate.setTextSize(14);
        row.addView(tvDate);

        TextView tvMeal1 = new TextView(this);
        tvMeal1.setText(model.meal1);
        tvMeal1.setPadding(10,10,10,10);
        row.addView(tvMeal1);
        TextView tvMeal2 = new TextView(this);
        tvMeal2.setText(model.meal2);
        tvMeal2.setPadding(10,10,10,10);
        row.addView(tvMeal2);
        TextView tvMeal3 = new TextView(this);
        tvMeal3.setText(model.meal3);
        tvMeal3.setPadding(10,10,10,10);
        row.addView(tvMeal3);
        TextView tvMeal4 = new TextView(this);
        tvMeal4.setText(model.meal4);
        tvMeal4.setPadding(10,10,10,10);
        row.addView(tvMeal4);
        tbl.addView(row, index);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
class DiningModel{
    public String date;
    public String meal1;
    public String meal2;
    public String meal3;
    public String meal4;
    public DiningModel(String date, String meal1,String meal2, String meal3, String meal4){
        this.date = date;
        this.meal1 = meal1;
        this.meal2 = meal2;
        this.meal3 = meal3;
        this.meal4 = meal4;
    }
    public boolean isToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        final String nowStr = dtf.format(now);
        return date.equals(nowStr);
    }

}