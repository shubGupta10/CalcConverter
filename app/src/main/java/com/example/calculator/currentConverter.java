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

public class currentConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.current_converter_btn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(currentConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(currentConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> currentUnits = new ArrayList<>();
        currentUnits.add("Amperes (A)");
        currentUnits.add("Milliamperes (mA)");
        currentUnits.add("Microamperes (μA)");
        currentUnits.add("Kiloamperes (kA)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currentUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCurrent();
            }
        });
    }



    private void convertCurrent() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performCurrentConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Result: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performCurrentConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "Amperes (A)":
                return convertAmperesTo(toUnit, value);
            case "Milliamperes (mA)":
                return convertMilliamperesTo(toUnit, value);
            case "Microamperes (μA)":
                return convertMicroamperesTo(toUnit, value);
            case "Kiloamperes (kA)":
                return convertKiloamperesTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertAmperesTo(String toUnit, double value) {
        switch (toUnit) {
            case "Milliamperes (mA)":
                return value * 1000.0;
            case "Microamperes (μA)":
                return value * 1.0e+6;
            case "Kiloamperes (kA)":
                return value * 1.0e-3;
            default:
                return value;
        }
    }

    private double convertMilliamperesTo(String toUnit, double value) {
        switch (toUnit) {
            case "Amperes (A)":
                return value / 1000.0;
            case "Microamperes (μA)":
                return value * 1000.0;
            case "Kiloamperes (kA)":
                return value * 1.0e-6;
            default:
                return value;
        }
    }

    private double convertMicroamperesTo(String toUnit, double value) {
        switch (toUnit) {
            case "Amperes (A)":
                return value / 1.0e+6;
            case "Milliamperes (mA)":
                return value / 1000.0;
            case "Kiloamperes (kA)":
                return value * 1.0e-9;
            default:
                return value;
        }
    }

    private double convertKiloamperesTo(String toUnit, double value) {
        switch (toUnit) {
            case "Amperes (A)":
                return value * 1.0e+3;
            case "Milliamperes (mA)":
                return value * 1.0e+6;
            case "Microamperes (μA)":
                return value * 1.0e+9;
            default:
                return value;
        }
    }
}