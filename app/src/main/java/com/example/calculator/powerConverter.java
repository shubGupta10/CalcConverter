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

public class powerConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;

    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.power_converter_btn);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(powerConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(powerConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> powerUnits = new ArrayList<>();
        powerUnits.add("Watts");
        powerUnits.add("Kilowatts");
        powerUnits.add("Megawatts");
        powerUnits.add("Horsepower");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, powerUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertPower();
            }
        });
    }


    private void convertPower() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performPowerConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Result: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performPowerConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "Watts":
                return convertWattsTo(toUnit, value);
            case "Kilowatts":
                return convertKilowattsTo(toUnit, value);
            case "Megawatts":
                return convertMegawattsTo(toUnit, value);
            case "Horsepower":
                return convertHorsepowerTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertWattsTo(String toUnit, double value) {
        switch (toUnit) {
            case "Kilowatts":
                return value / 1000.0;
            case "Megawatts":
                return value / 1.0e+6;
            case "Horsepower":
                return value / 745.7;
            default:
                return value;
        }
    }

    private double convertKilowattsTo(String toUnit, double value) {
        switch (toUnit) {
            case "Watts":
                return value * 1000.0;
            case "Megawatts":
                return value / 1000.0;
            case "Horsepower":
                return value / 0.7457;
            default:
                return value;
        }
    }

    private double convertMegawattsTo(String toUnit, double value) {
        switch (toUnit) {
            case "Watts":
                return value * 1.0e+6;
            case "Kilowatts":
                return value * 1000.0;
            case "Horsepower":
                return value / 0.0007457;
            default:
                return value;
        }
    }

    private double convertHorsepowerTo(String toUnit, double value) {
        switch (toUnit) {
            case "Watts":
                return value * 745.7;
            case "Kilowatts":
                return value * 0.7457;
            case "Megawatts":
                return value * 0.0007457;
            default:
                return value;
        }
    }
}