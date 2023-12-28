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

public class speedConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.speed_converter_btn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(speedConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(speedConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayList<String> speedUnits = new ArrayList<>();
        speedUnits.add("Meters");
        speedUnits.add("Kilometers");
        speedUnits.add("Miles");
        speedUnits.add("Knots");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, speedUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertSpeed();
            }
        });
    }

    private void convertSpeed() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performSpeedConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Result: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performSpeedConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "Meters":
                return convertMetersTo(toUnit, value);
            case "Kilometers":
                return convertKilometersTo(toUnit, value);
            case "Miles":
                return convertMilesTo(toUnit, value);
            case "Knots":
                return convertKnotsTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertMetersTo(String toUnit, double value) {

        switch (toUnit) {
            case "Kilometers":
                return value / 1000.0;
            case "Miles":
                return value * 0.000621371;
            case "Knots":
                return value * 0.000539957;
            default:
                return value;
        }
    }

    private double convertKilometersTo(String toUnit, double value) {

        switch (toUnit) {
            case "Meters":
                return value * 1000.0;
            case "Miles":
                return value * 0.621371;
            case "Knots":
                return value * 0.539957;
            default:
                return value;
        }
    }

    private double convertMilesTo(String toUnit, double value) {

        switch (toUnit) {
            case "Meters":
                return value / 0.000621371;
            case "Kilometers":
                return value / 0.621371;
            case "Knots":
                return value * 1.15078;
            default:
                return value;
        }
    }

    private double convertKnotsTo(String toUnit, double value) {

        switch (toUnit) {
            case "Meters":
                return value / 0.000539957;
            case "Kilometers":
                return value / 0.539957;
            case "Miles":
                return value / 1.15078;
            default:
                return value;
        }
    }
}
