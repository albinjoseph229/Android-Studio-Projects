package com.example.namedispapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText mark1,mark2,mark3;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mark1=findViewById(R.id.mark1);
        mark2=findViewById(R.id.mark2);
        mark3=findViewById(R.id.mark3);
        calculate=findViewById(R.id.calculate);
        result=findViewById(R.id.result);

        calculate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

            int marks1=Integer.parseInt(mark1.getText().toString());
            int marks2=Integer.parseInt(mark2.getText().toString());
            int marks3=Integer.parseInt(mark3.getText().toString());

            int sum=marks1+marks2+marks3;

            int avg=sum/3;

            result.setText("sum:"+sum+"average:"+avg);

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}