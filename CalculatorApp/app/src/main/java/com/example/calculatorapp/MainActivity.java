package com.example.calculatorapp;

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

    TextView result;
    EditText number1,number2;
    Button add, subtract,multiply,divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add=findViewById(R.id.add);
        multiply=findViewById(R.id.multiply);
        divide=findViewById(R.id.divide);
        subtract =findViewById(R.id.subtract);
        result=findViewById(R.id.result);
        number1=findViewById(R.id.num1);
        number2=findViewById(R.id.num2);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=Double.parseDouble(number1.getText().toString());
                double num2=Double.parseDouble(number2.getText().toString());
                double sum=num1+num2;
                result.setText("Result:"+sum);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=Double.parseDouble(number1.getText().toString());
                double num2=Double.parseDouble(number2.getText().toString());
                double difference=num1-num2;

                result.setText("Result:"+difference);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=Double.parseDouble(number1.getText().toString());
                double num2=Double.parseDouble(number2.getText().toString());
                double product=num1*num2;

                result.setText("Result:"+product);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1=Double.parseDouble(number1.getText().toString());
                double num2=Double.parseDouble(number2.getText().toString());
                if(num2 != 0){
                    double quotient=num1/num2;
                    result.setText("Result"+quotient);
                }
                else{
                    result.setText("Cannot divide by zero");
                }



            }
        });






    }
}