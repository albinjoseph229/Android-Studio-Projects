// src/com/example/broadcastintentapp/MyBroadcastReceiver.java
package com.example.broadcastintentapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the custom broadcast
        if (intent.getAction().equals("com.example.broadcastintentapp.CUSTOM_INTENT")) {
            String message = intent.getStringExtra("message");
            Toast.makeText(context, "Received: " + message, Toast.LENGTH_LONG).show();
            Log.d("MyBroadcastReceiver", "Broadcast received with message: " + message);
        }
    }
}
