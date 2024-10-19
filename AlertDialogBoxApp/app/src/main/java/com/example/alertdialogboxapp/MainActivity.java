package com.example.alertdialogboxapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button showDialogButton;
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

        showDialogButton=findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(v-> {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Alert Dialog");
                    builder.setMessage("Do You Want To Continue");

                    builder.setPositiveButton("YES", (dialog, which) -> {
                        Toast.makeText(getApplicationContext(),"You Clicked Yes",Toast.LENGTH_SHORT).show();
                    });

                    builder.setNegativeButton("NO",(dialog, which) -> {
                        Toast.makeText(getApplicationContext(),"You Clicked No",Toast.LENGTH_SHORT).show();
                    });

                    builder.setNeutralButton("CANCEL",(dialog, which) -> {
                        Toast.makeText(getApplicationContext(),"You Clicked Cancel",Toast.LENGTH_SHORT).show();
                    });

                    builder.show();

                });

    }


}