package com.example.akdenizapp;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AnouncementDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement_detail);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        TextView txtContent = (TextView)findViewById(R.id.txtContent);
        txtContent.setText(content);
    }
    /*RestClient client = new RestClient("http://www.example.com/demo.php");  //Write your url here
        client.addParam("Name", "Bhavit"); //Here I am adding key-value parameters
        client.addParam("Age", "23");

        client.addHeader("content-type", "application/json"); // Here I am specifying that the key-value pairs are sent in the JSON format

        try {
            String response = client.executePost(); // In case your server sends any response back, it will be saved in this response string.

        } catch (Exception e) {
            e.printStackTrace();
        }*/
}