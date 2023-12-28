package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivitymain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitymain);


    }


    public void calculatorbtn(View v){
        Intent i1 = new Intent(MainActivitymain.this, MainActivity.class);
        startActivity(i1);
    }


    public void conveterApp(View v){
        Intent i2 = new Intent(MainActivitymain.this , UnitConverter.class);
        startActivity(i2);
    }


    public void helpbtn(View v){
        Intent i3 = new Intent(MainActivitymain.this , HelpActivity.class);
        startActivity(i3);
    }
}