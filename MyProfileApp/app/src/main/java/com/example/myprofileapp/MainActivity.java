package com.example.myprofileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText subject1EditText;
    private EditText subject2EditText;
    private EditText subject3EditText;
    private Button calculateButton;
    private TextView sumTextView;
    private TextView averageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subject1EditText = findViewById(R.id.subject1EditText);
        subject2EditText = findViewById(R.id.subject2EditText);
        subject3EditText = findViewById(R.id.subject3EditText);
        calculateButton = findViewById(R.id.calculateButton);
        sumTextView = findViewById(R.id.sumTextView);
        averageTextView = findViewById(R.id.averageTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject1Str = subject1EditText.getText().toString();
                String subject2Str = subject2EditText.getText().toString();
                String subject3Str = subject3EditText.getText().toString();

                if (!subject1Str.isEmpty() && !subject2Str.isEmpty() && !subject3Str.isEmpty()) {
                    int subject1 = Integer.parseInt(subject1Str);
                    int subject2 = Integer.parseInt(subject2Str);
                    int subject3 = Integer.parseInt(subject3Str);

                    int sum = subject1 + subject2 + subject3;
                    double average = sum / 3.0;

                    sumTextView.setText("Sum: " + sum);
                    averageTextView.setText("Average: " + average);
                }
            }
        });
    }
}

