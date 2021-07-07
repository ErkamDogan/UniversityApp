package com.example.akdenizapp;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Events extends AppCompatActivity implements HttpResponseImpl {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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
                    Intent t = new Intent(Events.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Events.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Events.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Events.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Events.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Events.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Events.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Events.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Events.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Events.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Events.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });
        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/events.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void httpResult(String result) {
        try {
            ArrayList<EventModel> list = new ArrayList<>();
            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("events");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new EventModel(a.get("date").toString(),a.get("title").toString()));
              //  list.add(a.get("title").toString());
            }
            ListView listView = (ListView) findViewById(R.id.listView_list_of_events);
            EventListAdapter data = new EventListAdapter(this, R.layout.listview_events,list);

           /* ArrayAdapter<String> data = new ArrayAdapter<String>
                    (this, R.layout.listview_events, R.id.textView45, list);*/
            listView.setAdapter(data);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Events.this, AnouncementDetail.class);
                        t.putExtra("url", jData.get("url").toString());
                        startActivity(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}

class EventModel{
    public String date;
    public String title;
    public EventModel(String date, String title){
        this.date = date;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}