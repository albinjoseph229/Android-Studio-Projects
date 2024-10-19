package com.example.functionsample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private EditText inputText;
    private Button  setNameBtn,resetName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.Header);
        tv.setBackgroundColor(Color.BLACK);
        inputText = findViewById(R.id.inputText);
        setNameBtn = findViewById(R.id.set);
        resetName = findViewById(R.id.reset);
        resetName.setOnClickListener(v -> { tv.setText("Sample App");});
        setNameBtn.setOnClickListener(v -> setName());
    }
    private void setName(){
        String str = inputText.getText().toString();
        if(!str.isEmpty())
            tv.setText(str);
    }

}