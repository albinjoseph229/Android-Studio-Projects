// src/com/example/asynctaskapp/MainActivity.java
package com.example.asynctaskapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView resultText;
    private Button startTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        resultText = findViewById(R.id.resultText);
        startTaskButton = findViewById(R.id.startTaskButton);

        // Set a click listener to start AsyncTask
        startTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AsyncTask
                new SimpleAsyncTask().execute();
            }
        });
    }

    // AsyncTask class to perform a background task
    private class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

        // Before the task starts, show the progress bar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);  // Show progress bar
            resultText.setText("Task running...");
        }

        // Perform the background task
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Simulate a time-consuming task (e.g., downloading a file)
                Thread.sleep(3000);  // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Return a result after the task is done
            return "Task Completed";
        }

        // After the task is complete, update the UI with the result
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);  // Hide progress bar
            resultText.setText(result);  // Display the result
        }
    }
}
