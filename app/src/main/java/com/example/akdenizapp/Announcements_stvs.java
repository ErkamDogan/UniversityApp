package com.example.akdenizapp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Announcements_stvs extends AppCompatActivity implements HttpResponseImpl {

    String [] Announcements={"duyuru 1", "duyuru 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_stvs);
        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/announcement.json",this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void httpResult(String result) {
        try {
            ArrayList<String> list = new ArrayList<String>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("announcements");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(a.get("title").toString());
            }
            ListView listView = (ListView) findViewById(R.id.listView_list_of_announcements_stvs);
            ArrayAdapter<String> data = new ArrayAdapter<String>
                    (this, R.layout.listview_announcements, R.id.textView11, list);
            listView.setAdapter(data);
            /* go to announcement link
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(jData.getString("url")));
                        startActivity(httpIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });*/
            /* go to announcement detail with title and content
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Announcements_stvs.this, AnouncementDetail.class);
                        t.putExtra("title", jData.get("title").toString());
                        t.putExtra("content", jData.get("content").toString());
                        startActivity(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });*/
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        JSONObject jData = (JSONObject)array.get(i);
                        Intent t = new Intent(Announcements_stvs.this, AnouncementDetail.class);
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
}