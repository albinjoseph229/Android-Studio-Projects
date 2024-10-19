package com.example.calculatordemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentNumber = new StringBuilder();
    private double operand1 = Double.NaN;
    private double operand2 = Double.NaN;
    private char currentOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        currentNumber.append(button.getText().toString());
        display.setText(currentNumber.toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        if (!Double.isNaN(operand1)) {
            operand2 = Double.parseDouble(currentNumber.toString());
            performOperation();
        } else {
            operand1 = Double.parseDouble(currentNumber.toString());
        }
        currentOperator = button.getText().toString().charAt(0);
        currentNumber.setLength(0);
    }

    public void onEqualsClick(View view) {
        if (!Double.isNaN(operand1) && !currentNumber.toString().isEmpty()) {
            operand2 = Double.parseDouble(currentNumber.toString());
            performOperation();
            operand1 = Double.NaN;
            operand2 = Double.NaN;
        }
    }

    public void onClearClick(View view) {
        operand1 = Double.NaN;
        operand2 = Double.NaN;
        currentNumber.setLength(0);
        display.setText("0");
    }

    public void onDeleteClick(View view) {
        if (currentNumber.length() > 0) {
            currentNumber.deleteCharAt(currentNumber.length() - 1);
            display.setText(currentNumber.toString());
        }
    }

    public void onDotClick(View view) {
        if (!currentNumber.toString().contains(".")) {
            currentNumber.append(".");
            display.setText(currentNumber.toString());
        }
    }

    private void performOperation() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        double result = Double.NaN;
        switch (currentOperator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    display.setText("Error");
                }
                break;
        }
        if (!Double.isNaN(result)) {
            display.setText(decimalFormat.format(result));
        }
    }
}
