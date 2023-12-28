package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kotlin.Unit;

public class UnitConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);


        Button weight = findViewById(R.id.weight_btn);
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(UnitConverter.this , WeightConverter.class);
                startActivity(i1);
            }
        });

        Button temperature = findViewById(R.id.temperature_btn);
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(UnitConverter.this, temperatureConverter.class);
                startActivity(i2);
            }
        });


        Button length = findViewById(R.id.length_btn);
        length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(UnitConverter.this, lengthConverter.class);
                startActivity(i3);
            }
        });


        Button speed = findViewById(R.id.speed_btn);
        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(UnitConverter.this, speedConverter.class);
                startActivity(i4);
            }
        });

        Button area = findViewById(R.id.Area_btn);
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(UnitConverter.this, areaConverter.class);
                startActivity(i5);
            }
        });


        Button volume = findViewById(R.id.Volume_btn);
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(UnitConverter.this, volumeConverter.class);
                startActivity(i6);
            }
        });


        Button Power = findViewById(R.id.Power_btn);
        Power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i7 = new Intent(UnitConverter.this, powerConverter.class);
                startActivity(i7);
            }
        });


        Button Time = findViewById(R.id.time_btn);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i8 = new Intent(UnitConverter.this, timeConverter.class);
                startActivity(i8);
            }
        });


        Button Current = findViewById(R.id.Current_btn);
        Current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i9 = new Intent(UnitConverter.this, currentConverter.class);
                startActivity(i9);
            }
        });

    }
}