package com.example.todolist;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TaskCreationActivity extends AppCompatActivity {

    private EditText etTaskName, etDueDate, etLabel;
    private Spinner spinnerColor;
    private Button btnCreateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_creation_layout);

        etTaskName = findViewById(R.id.etTaskName);
        etDueDate = findViewById(R.id.etDueDate);
        etLabel = findViewById(R.id.etLabel);
        spinnerColor = findViewById(R.id.spinnerColor);
        btnCreateTask = findViewById(R.id.btnCreateTask);

        // Set up the color spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapter);

        btnCreateTask.setOnClickListener(v -> {
            String taskName = etTaskName.getText().toString();
            String dueDate = etDueDate.getText().toString();
            String label = etLabel.getText().toString();
            int colorCode = getColorCodeFromSpinner();

            Task newTask = new Task(taskName, false, dueDate, label, colorCode);
            saveTask(newTask);
        });
    }

    private int getColorCodeFromSpinner() {
        String selectedColor = spinnerColor.getSelectedItem().toString();
        switch (selectedColor) {
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Blue":
                return Color.BLUE;
            default:
                return Color.TRANSPARENT; // Default color
        }
    }

    private void saveTask(Task task) {
        new Thread(() -> {
            TaskDatabase.getInstance(TaskCreationActivity.this).taskDao().insert(task);
            runOnUiThread(() -> {
                Toast.makeText(TaskCreationActivity.this, "Task created", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            });
        }).start();
    }
}
