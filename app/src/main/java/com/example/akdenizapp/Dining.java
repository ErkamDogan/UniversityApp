package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.CDATASection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Dining extends AppCompatActivity implements HttpResponseImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
        /*
        String date = getIntent().getStringExtra("date");
        String firstMeal = getIntent().getStringExtra("first_meal");
        String secondMeal = getIntent().getStringExtra("second_meal");
        String thirdMeal = getIntent().getStringExtra("third_meal");
        String fourthMeal = getIntent().getStringExtra("third_meal");
        */
        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/dining.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void httpResult(String result) {
        try {
            ArrayList<DiningModel> list = new ArrayList<DiningModel>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("dinings");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new DiningModel(a.get("date").toString(),a.get("meal1").toString(),a.get("meal2").toString(),a.get("meal3").toString(),a.get("meal4").toString()));
            }
            DiningModel today =  null;
            for (DiningModel todayObj:
                 list) {
                if(todayObj.isToday()){
                 today = todayObj;
                }
            }

            if(today != null){
                TableLayout tblToday = (TableLayout) findViewById(R.id.tblToday);
                addRow(tblToday,today,0);
            }
            TableLayout tblNext = (TableLayout) findViewById(R.id.tblNext);
            Integer i = 0;
            for (DiningModel model :
                    list) {
                addRow(tblNext, model, i++);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void  addRow(TableLayout tbl, DiningModel model, Integer index){
        TableRow row= new TableRow(this);
        row.setBackgroundColor(ContextCompat.getColor(this,R.color.light_sea_green));
         row.setPadding(10,10,10,10);

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvDate = new TextView(this);
        tvDate.setText(model.date);
        tvDate.setPadding(10,10,10,10);
        tvDate.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDate.setTextColor(ContextCompat.getColor(this,R.color.white));
        tvDate.setTextSize(14);
        row.addView(tvDate);

        TextView tvMeal1 = new TextView(this);
        tvMeal1.setText(model.meal1);
        tvMeal1.setPadding(10,10,10,10);
        row.addView(tvMeal1);
        TextView tvMeal2 = new TextView(this);
        tvMeal2.setText(model.meal2);
        tvMeal2.setPadding(10,10,10,10);
        row.addView(tvMeal2);
        TextView tvMeal3 = new TextView(this);
        tvMeal3.setText(model.meal3);
        tvMeal3.setPadding(10,10,10,10);
        row.addView(tvMeal3);
        TextView tvMeal4 = new TextView(this);
        tvMeal4.setText(model.meal4);
        tvMeal4.setPadding(10,10,10,10);
        row.addView(tvMeal4);
        tbl.addView(row, index);
    }
}
class DiningModel{
    public String date;
    public String meal1;
    public String meal2;
    public String meal3;
    public String meal4;
    public DiningModel(String date, String meal1,String meal2, String meal3, String meal4){
        this.date = date;
        this.meal1 = meal1;
        this.meal2 = meal2;
        this.meal3 = meal3;
        this.meal4 = meal4;
    }
    public boolean isToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        final String nowStr = dtf.format(now);
        return date.equals(nowStr);
    }
}