package com.example.akdenizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Classes extends AppCompatActivity implements HttpResponseImpl {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
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
                    Intent t = new Intent(Classes.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Classes.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Classes.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Classes.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Classes.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Classes.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Classes.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Classes.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Classes.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Classes.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Classes.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void httpResult(String result) {
        try {
            ArrayList<ClassSchedule> list = new ArrayList<ClassSchedule>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("Schedule");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new ClassSchedule(a.get("date").toString(),a.get("class").toString()));
            }
            ClassSchedule today =  null;
            for (ClassSchedule todayObj:
                    list) {
                if(todayObj.isToday()){
                    today = todayObj;
                }
            }

            if(today != null){
                TableLayout tblTodayLecture = (TableLayout) findViewById(R.id.tblTodayLecture);
                addRow(tblTodayLecture,today,0);
            }
            TableLayout tblNext = (TableLayout) findViewById(R.id.tblNext);
            Integer i = 0;
            for (ClassSchedule model :
                    list) {
                addRow(tblNext, model, i++);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void  addRow(TableLayout tbl, ClassSchedule model, Integer index) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(ContextCompat.getColor(this, R.color.light_sea_green));
        row.setPadding(10, 10, 10, 10);

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvDate = new TextView(this);
        tvDate.setText(model.date);
        tvDate.setPadding(10, 10, 10, 10);
        tvDate.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDate.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvDate.setTextSize(14);
        row.addView(tvDate);

        TextView tvLecture = new TextView(this);
        tvLecture.setText(model.lecture);
        tvLecture.setPadding(10, 10, 10, 10);
        row.addView(tvLecture);
        TextView tvMeal2 = new TextView(this);
    }
}
class ClassSchedule{
    public String date;
    public String lecture;
    public ClassSchedule(String date, String lecture){
        this.date = date;
        this.lecture = lecture;

    }
    public boolean isToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        final String nowStr = dtf.format(now);
        return date.equals(nowStr);
    }
}