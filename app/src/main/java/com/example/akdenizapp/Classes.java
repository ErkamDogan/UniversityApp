package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Classes extends AppCompatActivity implements HttpResponseImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
    }

    @Override
    public void httpResult(String result) {
        try {
            ArrayList<ClassSchedule> list = new ArrayList<ClassSchedule>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("Schedule");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new ClassSchedule(a.get("date").toString(),a.get("class").toString()));
            }
            ClassSchedule today =  null;
            for (ClassSchedule todayObj:
                    list) {
                if(todayObj.isToday()){
                    today = todayObj;
                }
            }

            if(today != null){
                TableLayout tblTodayLecture = (TableLayout) findViewById(R.id.tblTodayLecture);
                addRow(tblTodayLecture,today,0);
            }
            TableLayout tblNext = (TableLayout) findViewById(R.id.tblNext);
            Integer i = 0;
            for (ClassSchedule model :
                    list) {
                addRow(tblNext, model, i++);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void  addRow(TableLayout tbl, ClassSchedule model, Integer index) {
        TableRow row = new TableRow(this);
        row.setBackgroundColor(ContextCompat.getColor(this, R.color.light_sea_green));
        row.setPadding(10, 10, 10, 10);

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvDate = new TextView(this);
        tvDate.setText(model.date);
        tvDate.setPadding(10, 10, 10, 10);
        tvDate.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDate.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvDate.setTextSize(14);
        row.addView(tvDate);

        TextView tvLecture = new TextView(this);
        tvLecture.setText(model.lecture);
        tvLecture.setPadding(10, 10, 10, 10);
        row.addView(tvLecture);
        TextView tvMeal2 = new TextView(this);
    }
}
class ClassSchedule{
    public String date;
    public String lecture;
    public ClassSchedule(String date, String lecture){
        this.date = date;
        this.lecture = lecture;

    }
    public boolean isToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        final String nowStr = dtf.format(now);
        return date.equals(nowStr);
    }
}