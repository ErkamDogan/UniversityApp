package com.example.akdenizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Surveys extends AppCompatActivity implements HttpResponseImpl {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveys);

        try {
            ApiHelper client = new ApiHelper(getResources().getString(R.string.baseUrl) + "/survey.json", this, "GET");  //Write your url here
            client.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void httpResult(String result) {
        try {
            ArrayList<SurveyQuestions> list = new ArrayList<SurveyQuestions>();

            JSONObject obj = new JSONObject(result);
            final JSONArray array = obj.getJSONArray("surveys");
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = (JSONObject) array.get(i);
                list.add(new SurveyQuestions(a.get("surveyQuestions").toString(),a.get("option1").toString(),a.get("option2").toString(),a.get("option3").toString()));
            }
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.surveyLinearLayout);
            Integer i = 0;
            for (SurveyQuestions question :
                    list) {
                addQuestionGroup(linearLayout, question, i++);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void  addQuestionGroup(LinearLayout linearLayout, SurveyQuestions question, Integer index){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView);
        textView.setText(question.surveyQuestions);
        textView.setPadding(20,20,20,20);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(ContextCompat.getColor(this,R.color.white));
        textView.setTextSize(14);


        RadioGroup radioGroup= new RadioGroup(this);
        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(radioGroup);


            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setPadding(10,10,10,10);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setText(question.option1);
            radioButton.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);

            radioButton = new RadioButton(this);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setPadding(10,10,10,10);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setText(question.option2);
            radioButton.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);

            radioButton = new RadioButton(this);
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setPadding(10,10,10,10);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setText(question.option3);
            radioButton.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);
    }
}