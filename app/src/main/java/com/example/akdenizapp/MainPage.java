package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ((ImageButton) findViewById(R.id.complaints_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainPage.this,visitorComplaint.class);
                startActivity(t);
            }
        });
        ((ImageButton) findViewById(R.id.announcements_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainPage.this,Announcements_stvs.class);
                startActivity(t);
            }
        });
    }
}