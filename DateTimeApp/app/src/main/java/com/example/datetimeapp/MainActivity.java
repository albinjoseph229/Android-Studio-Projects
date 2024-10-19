package com.example.datetimeapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        TextView dateView,timeView,altDateView,altTimeView;

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dateView=findViewById(R.id.dateView);
        timeView=findViewById(R.id.timeView);
        altTimeView=findViewById(R.id.altTimeView);
        altDateView=findViewById(R.id.altDateView);

        Date currentdate=new Date();

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        SimpleDateFormat altTimeFormat=new SimpleDateFormat("E, MMM dd yyy",Locale.getDefault());
        SimpleDateFormat altDateFormat=new SimpleDateFormat("hh:mm a",Locale.getDefault());

        dateView.setText(dateFormat.format(currentdate));
        timeView.setText(timeFormat.format(currentdate));
        altTimeView.setText(altTimeFormat.format(currentdate));
        altDateView.setText(altDateFormat.format(currentdate));


    }

}