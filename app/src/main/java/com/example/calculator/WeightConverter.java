package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeightConverter extends AppCompatActivity {


    EditText editText;
    TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        Button  convertButton = findViewById(R.id.weight_converter_btn);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(WeightConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position2, long l) {
                if (position2 != AdapterView.INVALID_POSITION) {
                    String item = adapterView.getItemAtPosition(position2).toString();
                    Toast.makeText(WeightConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> units = new ArrayList<>();
        units.add("Grams");
        units.add("Kilogram");
        units.add("Ounces");
        units.add("Pounds");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


         convertButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 convertWeight();
             }
         });
    }



    private void convertWeight() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performConversion(fromUnit, toUnit, inputValue);

            //yha hum result display kr rhe
            resultTextView.setText("Result: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performConversion(String fromUnit, String toUnit, double value) {


        double gramValue = 0.0;

        switch (fromUnit) {
            case "Grams":
                gramValue = value;
                break;
            case "Kilograms":
                gramValue = value * 1000;
                break;
            case "Ounces":
                gramValue = value * 28.3495;
                break;
            case "Pounds":
                gramValue = value * 453.592;
                break;
        }

        double result = 0.0;

        switch (toUnit) {
            case "Grams":
                result = gramValue;
                break;
            case "Kilogram":
                result = gramValue / 1000;
                break;
            case "Ounces":
                result = gramValue / 28.3495;
                break;
            case "Pounds":
                result = gramValue / 453.592;
                break;
        }

        return result;
    }


}