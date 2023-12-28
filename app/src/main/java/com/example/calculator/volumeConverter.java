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

public class volumeConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        convertButton = findViewById(R.id.volume_converter_btn);
        resultTextView = findViewById(R.id.resultTextView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(volumeConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(volumeConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> volumeUnits = new ArrayList<>();
        volumeUnits.add("Meters");
        volumeUnits.add("Centimeters");
        volumeUnits.add("Liters");
        volumeUnits.add("Milliliters");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, volumeUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertVolume();
            }
        });
    }



    private void convertVolume() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performVolumeConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Results: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performVolumeConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "Meters":
                return convertMetersTo(toUnit, value);
            case "Centimeters":
                return convertCentimetersTo(toUnit, value);
            case "Liters":
                return convertLitersTo(toUnit, value);
            case "Milliliters":
                return convertMillilitersTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertMetersTo(String toUnit, double value) {
        switch (toUnit) {
            case "Centimeters":
                return value * 100.0;
            case "Liters":
                return value * 1000.0;
            case "Milliliters":
                return value * 1.0e+6;
            default:
                return value;
        }
    }

    private double convertCentimetersTo(String toUnit, double value) {
        switch (toUnit) {
            case "Meters":
                return value / 100.0;
            case "Liters":
                return value * 10.0;
            case "Milliliters":
                return value * 1000.0;
            default:
                return value;
        }
    }

    private double convertLitersTo(String toUnit, double value) {
        switch (toUnit) {
            case "Meters":
                return value / 1000.0;
            case "Centimeters":
                return value * 100.0;
            case "Milliliters":
                return value * 1000.0;
            default:
                return value;
        }
    }

    private double convertMillilitersTo(String toUnit, double value) {
        switch (toUnit) {
            case "Meters":
                return value / 1.0e+6;
            case "Centimeters":
                return value / 1000.0;
            case "Liters":
                return value / 1000.0;
            default:
                return value;
        }
    }

}