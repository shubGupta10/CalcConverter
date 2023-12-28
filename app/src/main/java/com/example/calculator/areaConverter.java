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

public class areaConverter extends AppCompatActivity {

    EditText editText;
    TextView resultTextView;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.area_converter_btn);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(areaConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(areaConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayList<String> areaUnits = new ArrayList<>();
        areaUnits.add("m");
        areaUnits.add("km");
        areaUnits.add("cm");
        areaUnits.add("inch");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areaUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertArea();
            }
        });
    }

    private void convertArea() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);

        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performAreaConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Result: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performAreaConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "m":
                return convertMetersTo(toUnit, value);
            case "km":
                return convertKilometersTo(toUnit, value);
            case "cm":
                return convertCentimetersTo(toUnit, value);
            case "inch":
                return convertInchesTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertMetersTo(String toUnit, double value) {
        switch (toUnit) {
            case "km":
                return value / 1000.0;
            case "cm":
                return value * 100.0;
            case "inch":
                return value * 39.3701;
            default:
                return value;
        }
    }

    private double convertKilometersTo(String toUnit, double value) {
        switch (toUnit) {
            case "m":
                return value * 1000.0;
            case "cm":
                return value * 100000.0;
            case "inch":
                return value * 39370.1;
            default:
                return value;
        }
    }

    private double convertCentimetersTo(String toUnit, double value) {
        switch (toUnit) {
            case "m":
                return value / 100.0;
            case "km":
                return value / 100000.0;
            case "inch":
                return value / 2.54;
            default:
                return value;
        }
    }

    private double convertInchesTo(String toUnit, double value) {
        switch (toUnit) {
            case "m":
                return value * 0.0254;
            case "km":
                return value * 2.54e-5;
            case "cm":
                return value * 2.54;
            default:
                return value;
        }
    }
}
