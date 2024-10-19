// src/com/example/alarmapp/AlarmActivity.java
package com.example.alarmapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Show a simple toast message when the alarm goes off
        Toast.makeText(this, "Alarm is ringing!", Toast.LENGTH_LONG).show();
    }
}
