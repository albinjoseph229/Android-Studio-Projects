// src/com/example/broadcastintentapp/MainActivity.java
package com.example.broadcastintentapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT_ACTION = "com.example.broadcastintentapp.CUSTOM_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBroadcast = findViewById(R.id.btn_broadcast);

        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a custom intent
                Intent intent = new Intent();
                intent.setAction(CUSTOM_INTENT_ACTION);
                intent.putExtra("message", "This is a custom broadcast message!");

                // Broadcast the custom intent
                sendBroadcast(intent);
            }
        });
    }
}
