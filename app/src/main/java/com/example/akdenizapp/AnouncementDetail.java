package com.example.akdenizapp;

import android.content.Intent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class AnouncementDetail extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement_detail);

        String url = getIntent().getStringExtra("url");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

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
                    Intent t = new Intent(AnouncementDetail.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(AnouncementDetail.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(AnouncementDetail.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(AnouncementDetail.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(AnouncementDetail.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(AnouncementDetail.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(AnouncementDetail.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(AnouncementDetail.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(AnouncementDetail.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(AnouncementDetail.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(AnouncementDetail.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });



         /* String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        TextView txtContent = (TextView)findViewById(R.id.txtContent);
        txtContent.setText(content);*/
    }
    /*
    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }*/
    /*RestClient client = new RestClient("http://www.example.com/demo.php");  //Write your url here
        client.addParam("Name", "Bhavit"); //Here I am adding key-value parameters
        client.addParam("Age", "23");

        client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

        try {
            String response = client.executePost(); // In case your server sends any response back, it will be saved in this response string.

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
