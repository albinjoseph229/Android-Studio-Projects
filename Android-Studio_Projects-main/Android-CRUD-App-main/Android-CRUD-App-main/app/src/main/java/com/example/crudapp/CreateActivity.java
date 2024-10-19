package com.example.crudapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateActivity extends AppCompatActivity {

    DbHelper dbHelper;
    EditText itemName, qnty;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DbHelper(this);
        itemName = findViewById(R.id.item);
        qnty = findViewById(R.id.qnty);
        btn = findViewById(R.id.createbtn);

        btn.setOnClickListener(v -> {
            String item = itemName.getText().toString().trim();
            String quantity = qnty.getText().toString().trim();

            if( item.isEmpty() || quantity.isEmpty()){

                //toast class . maketext method (this activity or context, string msg, length) . show();
                Toast.makeText(CreateActivity.this, "PLEASE FILL ALL THE FIELDS" , Toast.LENGTH_LONG).show();
                return;
            }
            int quant = Integer.parseInt(quantity);
            if( dbHelper.createItem(item,quant)){

                Toast.makeText(CreateActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(CreateActivity.this, "FAILED", Toast.LENGTH_LONG).show();
                itemName.setText("");
                qnty.setText("");
            }
        });

    }
}