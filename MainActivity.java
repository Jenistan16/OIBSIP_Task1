package com.example.unitconverter;

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

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromSpinner, toSpinner;
    Button convertBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Elements
        inputValue = findViewById(R.id.inputValue);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        // Set up Spinners with Unit Options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Convert Button Click Action
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        try {
            double input = Double.parseDouble(inputValue.getText().toString());
            String fromUnit = fromSpinner.getSelectedItem().toString();
            String toUnit = toSpinner.getSelectedItem().toString();
            double result = 0;

            // Length Conversions
            if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
                result = input / 100;
            } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
                result = input * 100;
            } else if (fromUnit.equals("Kilometers") && toUnit.equals("Meters")) {
                result = input * 1000;
            } else if (fromUnit.equals("Meters") && toUnit.equals("Kilometers")) {
                result = input / 1000;
            }

            // Weight Conversions
            else if (fromUnit.equals("Grams") && toUnit.equals("Kilograms")) {
                result = input / 1000;
            } else if (fromUnit.equals("Kilograms") && toUnit.equals("Grams")) {
                result = input * 1000;
            } else if (fromUnit.equals("Pounds") && toUnit.equals("Kilograms")) {
                result = input * 0.453592;
            } else if (fromUnit.equals("Kilograms") && toUnit.equals("Pounds")) {
                result = input * 2.20462;
            }

            // Temperature Conversions
            else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
                result = (input * 9/5) + 32;
            } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
                result = (input - 32) * 5/9;
            } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
                result = input + 273.15;
            } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
                result = input - 273.15;
            }

            // Display Result
            resultText.setText(String.format("%.2f %s = %.2f %s", input, fromUnit, result, toUnit));

        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid number!", Toast.LENGTH_SHORT).show();
        }
    }
}