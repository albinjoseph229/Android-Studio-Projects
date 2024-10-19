// src/com/example/progressapp/MainActivity.java
package com.example.progressapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private Button startButton;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        startButton = findViewById(R.id.startButton);

        // Set a click listener on the Start Button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the progress in a new thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Reset progress status
                        progressStatus = 0;

                        // Simulate a task by incrementing progress in intervals
                        while (progressStatus < 100) {
                            progressStatus += 1;

                            // Update progress on the UI thread
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the progress bar and text view
                                    progressBar.setProgress(progressStatus);
                                    progressText.setText("Progress: " + progressStatus + "%");
                                }
                            });

                            try {
                                // Sleep for 100 milliseconds to simulate task duration
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();  // Start the thread
            }
        });
    }
}
