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

public class lengthConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.length_converter_btn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(lengthConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(lengthConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> lengthUnits = new ArrayList<>();
        lengthUnits.add("Meters");
        lengthUnits.add("Kilometers");
        lengthUnits.add("Centimeters");
        lengthUnits.add("Inches");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertLength();
            }
        });


    }


    private void convertLength() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performLengthConversion(fromUnit, toUnit, inputValue);

            resultTextView.setText("Result: " + String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performLengthConversion(String fromUnit, String toUnit, double value) {

        switch (fromUnit) {
            case "Meters":
                switch (toUnit) {
                    case "Kilometers":
                        return value * 0.001;
                    case "Centimeters":
                        return value * 100;
                    case "Inches":
                        return value * 39.3701;
                    default:
                        return value;
                }
            case "Kilometers":
                switch (toUnit) {
                    case "Meters":
                        return value * 1000;
                    case "Centimeters":
                        return value * 100000;
                    case "Inches":
                        return value * 39370.1;
                    default:
                        return value;
                }


            case "Centimeters":
                switch (toUnit) {
                    case "Meters":
                        return value * 0.01;
                    case "Kilometers":
                        return value * 0.00001;
                    case "Inches":
                        return value * 0.393701;
                    default:
                        return value;
                }
            case "Inches":
                switch (toUnit) {
                    case "Meters":
                        return value * 0.0254;
                    case "Kilometers":
                        return value * 2.54e-5;
                    case "Centimeters":
                        return value * 2.54;
                    default:
                        return value;
                }
            default:
                return value;
        }

    }

}