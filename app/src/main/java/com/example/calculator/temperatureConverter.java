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

public class temperatureConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

    Button convertButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        convertButton = findViewById(R.id.temperature_converter_btn);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(temperatureConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(temperatureConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> temperatureUnits = new ArrayList<>();
        temperatureUnits.add("Celsius");
        temperatureUnits.add("Fahrenheit");
        temperatureUnits.add("Kelvin");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperatureUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature(view);
            }
        });
    }


// Formulas we are using baby:-
//    Celsius to Fahrenheit: F = (C * 9/5) + 32
//    Celsius to Kelvin: K = C + 273.15
//    Fahrenheit to Celsius: C = (F - 32) * 5/9
//    Fahrenheit to Kelvin: K = (F - 32) * 5/9 + 273.15
//    Kelvin to Celsius: C = K - 273.15
//    Kelvin to Fahrenheit: F = (K - 273.15) * 9/5 + 32








    private double performTemperatureConversion(String fromUnit, String toUnit, double value) { // fromunit take converting from , tounit take converting to and  double value returns the double value
        switch (fromUnit) {
            case "Celsius": //ye case celsius to all check krega and formula perform krega
                switch (toUnit) {
                    case "Fahrenheit":
                        return (value * 9/5) + 32;
                    case "Kelvin":
                        return value + 273.15;
                    default:
                        return value; // Celsius to Celsius
                }
            case "Fahrenheit":
                switch (toUnit) {
                    case "Celsius":
                        return (value - 32) * 5/9;
                    case "Kelvin":
                        return (value - 32) * 5/9 + 273.15;
                    default:
                        return value; // Fahrenheit to Fahrenheit
                }
            case "Kelvin":
                switch (toUnit) {
                    case "Celsius":
                        return value - 273.15;
                    case "Fahrenheit":
                        return (value - 273.15) * 9/5 + 32;
                    default:
                        return value; // Kelvin to Kelvin return krega
                }
            default:
                return value;
        }
    }

    public void convertTemperature(View view) {

        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performTemperatureConversion(fromUnit, toUnit, inputValue);


            resultTextView.setText("Result: " + String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }
}