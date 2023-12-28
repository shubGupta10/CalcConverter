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

public class timeConverter extends AppCompatActivity {

    EditText editText;

    TextView resultTextView;

    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_converter);

        Spinner spinner = findViewById(R.id.spinner_btn);
        Spinner spinner2 = findViewById(R.id.spinner_btn2);
        editText = findViewById(R.id.edit_text);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.time_converter_btn);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(timeConverter.this, "Item " +item+ " is Picked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(timeConverter.this, "Item " + item + " is Picked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> timeUnits = new ArrayList<>();
        timeUnits.add("Seconds");
        timeUnits.add("Minutes");
        timeUnits.add("Hours");
        timeUnits.add("Days");
        timeUnits.add("Weeks");
        timeUnits.add("Years");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeUnits);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTime();
            }
        });
    }


    private void convertTime() {
        Spinner spinnerFrom = findViewById(R.id.spinner_btn);
        Spinner spinnerTo = findViewById(R.id.spinner_btn2);
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        try {
            double inputValue = Double.parseDouble(editText.getText().toString());
            double result = performTimeConversion(fromUnit, toUnit, inputValue);

            String formattedResult = String.format("%.3f %s", result, toUnit);

            resultTextView.setText("Result: " + formattedResult);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private double performTimeConversion(String fromUnit, String toUnit, double value) {  //ye main function hai yha par saare methods ko call krenge
        switch (fromUnit) {
            case "Seconds":
                return convertSecondsTo(toUnit, value);
            case "Minutes":
                return convertMinutesTo(toUnit, value);
            case "Hours":
                return convertHoursTo(toUnit, value);
            case "Days":
                return convertDaysTo(toUnit, value);
            case "Weeks":
                return convertWeeksTo(toUnit, value);
            case "Years":
                return convertYearsTo(toUnit, value);
            default:
                return value;
        }
    }

    private double convertSecondsTo(String toUnit, double value) {
        switch (toUnit) {
            case "Minutes":
                return value / 60.0;
            case "Hours":
                return value / 3600.0;
            case "Days":
                return value / 86400.0;
            case "Weeks":
                return value / 604800.0;
            case "Years":
                return value / 31536000.0;
            default:
                return value;
        }
    }

    private double convertMinutesTo(String toUnit, double value) {
        switch (toUnit) {
            case "Seconds":
                return value * 60.0;
            case "Hours":
                return value / 60.0;
            case "Days":
                return value / 1440.0;
            case "Weeks":
                return value / 10080.0;
            case "Years":
                return value / 525600.0;
            default:
                return value;
        }
    }

    private double convertHoursTo(String toUnit, double value) {
        switch (toUnit) {
            case "Seconds":
                return value * 3600.0;
            case "Minutes":
                return value * 60.0;
            case "Days":
                return value / 24.0;
            case "Weeks":
                return value / 168.0;
            case "Years":
                return value / 8760.0;
            default:
                return value;
        }
    }

    private double convertDaysTo(String toUnit, double value) {
        switch (toUnit) {
            case "Seconds":
                return value * 86400.0;
            case "Minutes":
                return value * 1440.0;
            case "Hours":
                return value * 24.0;
            case "Weeks":
                return value / 7.0;
            case "Years":
                return value / 365.25;
            default:
                return value;
        }
    }

    private double convertWeeksTo(String toUnit, double value) {
        switch (toUnit) {
            case "Seconds":
                return value * 604800.0;
            case "Minutes":
                return value * 10080.0;
            case "Hours":
                return value * 168.0;
            case "Days":
                return value * 7.0;
            case "Years":
                return value / 52.1775;
            default:
                return value;
        }
    }

    private double convertYearsTo(String toUnit, double value) {
        switch (toUnit) {
            case "Seconds":
                return value * 31536000.0;
            case "Minutes":
                return value * 525600.0;
            case "Hours":
                return value * 8760.0;
            case "Days":
                return value * 365.25;
            case "Weeks":
                return value * 52.1775;
            default:
                return value;
        }
    }
}