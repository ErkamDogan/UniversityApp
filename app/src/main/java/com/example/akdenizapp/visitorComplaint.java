package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class visitorComplaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_complaint);

        ((Button) findViewById(R.id.button_create_visitor_new_complaint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(visitorComplaint.this, NewComplaint.class);
                startActivity(t);
            }
        });
    }
}