package com.example.akdenizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Surveys extends AppCompatActivity implements HttpResponseImpl {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveys);


        findViewById(R.id.button_survey_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(Surveys.this, HomePageActivity.class);
                startActivity(t);
            }
        });

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    Intent t = new Intent(Surveys.this, HomePageActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_announcements){
                    Intent t = new Intent(Surveys.this,Announcements_stvs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_events){
                    Intent t = new Intent(Surveys.this,Events.class);
                    startActivity(t);
                }
                if(id == R.id.nav_complaints){
                    Intent t = new Intent(Surveys.this, Complaints.class);
                    startActivity(t);
                }
                if(id == R.id.nav_maps){
                    Intent t = new Intent(Surveys.this,MapsActivity.class);
                    startActivity(t);
                }
                if(id == R.id.nav_feedback){
                    Intent t = new Intent(Surveys.this,feedback.class);
                    startActivity(t);
                }
                if(id == R.id.nav_dinings){
                    Intent t = new Intent(Surveys.this,Dining.class);
                    startActivity(t);
                }
                if(id == R.id.nav_classes){
                    Intent t = new Intent(Surveys.this,Classes.class);
                    startActivity(t);
                }
                if(id == R.id.nav_clubs){
                    Intent t = new Intent(Surveys.this,StudentClubs.class);
                    startActivity(t);
                }
                if(id == R.id.nav_Surveys){
                    Intent t = new Intent(Surveys.this,Surveys.class);
                    startActivity(t);
                }
                if(id == R.id.nav_login){
                    Intent t = new Intent(Surveys.this,login.class);
                    startActivity(t);
                }
                return true;
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}