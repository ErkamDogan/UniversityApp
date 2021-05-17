package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Events extends AppCompatActivity implements HttpResponseImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/events.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void httpResult(String result) {
        try {
            //ArrayList<EventModel> list = new ArrayList<EventModel>();
            ArrayList<String> list = new ArrayList<String>();
            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("events");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                //list.add(new EventModel(a.get("title").toString(),a.get("date").toString()));
                list.add(a.get("title").toString());
            }
            ListView listView = (ListView) findViewById(R.id.listView_list_of_events);
            ArrayAdapter<String> data = new ArrayAdapter<String>
                    (this, R.layout.listview_events, R.id.textView45, list);
            listView.setAdapter(data);listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Events.this, AnouncementDetail.class);
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
    public void addView(Integer index, View view,EventModel model){
        TextView tvDate = new TextView(this);
        tvDate.setText(model.date);
        TextView tvTitle = new TextView(this);
        tvTitle.setText(model.title);
    }
}

class EventModel{
    public String date;
    public String title;
    public EventModel(String date, String title){
        this.date = date;
        this.title = title;
    }
}