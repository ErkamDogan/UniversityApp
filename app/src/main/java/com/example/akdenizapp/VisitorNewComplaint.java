package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class VisitorNewComplaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_new_complaint);
        ArrayList<String> units = new ArrayList<String>();
        units.add("Hangi birime ulaştırmak istediğinizi seçiniz");


    }
}