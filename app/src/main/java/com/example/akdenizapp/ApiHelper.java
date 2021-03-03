package com.example.akdenizapp;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiHelper extends AsyncTask<String, String, String> {

    JSONObject data = new JSONObject();
    String urlString;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private HttpResponseImpl httpResponseImpl;
    private String method;
    public ApiHelper(String s,HttpResponseImpl httpResponseImpl ,String method){
        this.httpResponseImpl = httpResponseImpl;
        urlString = s;
        this.method = method;
    }

    // Sunucuya ulaşmak için post methodu
    public String executePost(){  // If you want to use post method to hit server

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");

            // setDoInput and setDoOutput method depict handling of both send and receive
            //Bu yöntemler hem gönderme hem de alma işlemlerini gösterir
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // Append parameters to URL
            Uri.Builder builder = new Uri.Builder();
                   // .appendQueryParameter("username", params[0])
                   // .appendQueryParameter("password", params[1]);
            String query = builder.build().getEncodedQuery();
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = data.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // Open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(headerName, headerValue);
        HttpResponse response = null;
        String result = null;
        try {
            StringEntity entity = new StringEntity(data.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            result = EntityUtils.toString(entity1);
            return result;
            //Toast.makeText(MainPage.this, result, Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
*/
    return "";

    }
    //Servera bağlanmak için Get methodu
    public String executeGet(){

        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Content-Type", "application/json; utf-8");
            //conn.setRequestProperty("Accept", "application/json");


            // Append parameters to URL
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String output;

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        String result = null;
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            result = httpClient.execute(httpget, responseHandler);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;*/
        return response.toString();
    }

    @Override
    protected String doInBackground(String... strings) {
        if (method == "POST") {
            return executePost();
        }

        if (method == "GET") {
            return executeGet();
        }
        return  "";
    }
    //Android network işlemlerini farklı bir thread olarak çalıştırır. Bundan dolayı bu istekleri
    //yapan apihelper classı asynctask classından türemiştir. İstek cevabı geldiğinde onPostExecute metodu
    //çalışır, çalıştığında istek atan aktivitenin httpResult methodu çalıştırılır. Bu işlemin
    //yapılabilmesi için aktivity classı, HttpResponseImpl interfacesinden türetilir.
    protected void onPostExecute(String result) {
        httpResponseImpl.httpResult(result);
    }
}
