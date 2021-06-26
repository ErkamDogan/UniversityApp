package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class NewComplaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaints);
        ArrayList<String> units = new ArrayList<String>();
        units.add("Hangi birime ulaştırmak istediğinizi seçiniz");


    }
}