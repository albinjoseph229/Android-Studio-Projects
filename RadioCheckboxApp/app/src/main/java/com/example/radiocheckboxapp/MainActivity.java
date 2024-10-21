package com.example.radiocheckboxapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupGender;
    private RadioButton selectedRadioButton;
    private CheckBox checkBoxReading, checkBoxTraveling, checkBoxGaming;
    private Button buttonSubmit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkBoxReading = findViewById(R.id.checkBoxReading);
        checkBoxTraveling = findViewById(R.id.checkBoxTraveling);
        checkBoxGaming = findViewById(R.id.checkBoxGaming);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);

        // Set the submit button's OnClickListener
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected radio button for gender
                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                selectedRadioButton = findViewById(selectedGenderId);

                // Collecting gender selection
                String gender = "";
                if (selectedGenderId != -1) {
                    gender = selectedRadioButton.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Collecting hobbies from checkboxes
                StringBuilder hobbies = new StringBuilder();
                if (checkBoxReading.isChecked()) {
                    hobbies.append("Reading ");
                }
                if (checkBoxTraveling.isChecked()) {
                    hobbies.append("Traveling ");
                }
                if (checkBoxGaming.isChecked()) {
                    hobbies.append("Gaming ");
                }

                if (hobbies.length() == 0) {
                    hobbies.append("No hobbies selected");
                }

                // Displaying the results
                String result = "Gender: " + gender + "\nHobbies: " + hobbies.toString();
                textViewResult.setText(result);
            }
        });
    }
}
