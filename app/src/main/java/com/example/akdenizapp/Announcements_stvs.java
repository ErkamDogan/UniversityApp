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

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Announcements_stvs extends AppCompatActivity implements HttpResponseImpl {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_stvs);
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
                    Intent t = new Intent(Announcements_stvs.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Announcements_stvs.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Announcements_stvs.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Announcements_stvs.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Announcements_stvs.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Announcements_stvs.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Announcements_stvs.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Announcements_stvs.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Announcements_stvs.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Announcements_stvs.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Announcements_stvs.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });

        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/announcement.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            ArrayList<String> list = new ArrayList<String>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("announcements");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(a.get("title").toString());
            }
            ListView listView = (ListView) findViewById(R.id.listView_list_of_announcements_stvs);
            ArrayAdapter<String> data = new ArrayAdapter<String>
                    (this, R.layout.listview_announcements, R.id.textView11, list);
            listView.setAdapter(data);
            /* go to announcement link
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(jData.getString("url")));
                        startActivity(httpIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });*/
            /* go to announcement detail with title and content
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Announcements_stvs.this, AnouncementDetail.class);
                        t.putExtra("title", jData.get("title").toString());
                        t.putExtra("content", jData.get("content").toString());
                        startActivity(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Announcements_stvs.this, AnouncementDetail.class);
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
}