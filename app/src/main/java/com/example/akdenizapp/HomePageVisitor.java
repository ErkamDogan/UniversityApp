package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class HomePageVisitor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_visitor);

        ((ImageButton) findViewById(R.id.announcements_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageVisitor.this,Announcements_stvs.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.complaints_button_visitor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(HomePageVisitor.this,visitorComplaint.class);
                startActivity(t);
            }
        });
    }
}