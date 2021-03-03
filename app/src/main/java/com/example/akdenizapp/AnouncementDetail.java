package com.example.akdenizapp;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AnouncementDetail extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncement_detail);

        String url = getIntent().getStringExtra("url");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



         /* String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        TextView txtContent = (TextView)findViewById(R.id.txtContent);
        txtContent.setText(content);*/
    }/*
    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }*/
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