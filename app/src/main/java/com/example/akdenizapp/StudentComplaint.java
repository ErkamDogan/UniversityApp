package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class StudentComplaint extends AppCompatActivity {

    String [] Complaints={"şikayet 1", "şikayet 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complaint);

        ListView listView =(ListView)findViewById(R.id.listView_list_of_complaints_student);
        ArrayAdapter<String> data=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, Complaints);
        listView.setAdapter(data);
        ((Button) findViewById(R.id.button_create_student_new_complaint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(StudentComplaint.this,StudentNewComplaint.class);
                startActivity(t);
            }
        });
    /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent t = new Intent(StudentComplaint.this,.class);
                startActivity(t);
            }
        });*/

    }

}