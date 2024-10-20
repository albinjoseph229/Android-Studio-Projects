package com.example.studentdbapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextCourse;
    private Button buttonAdd, buttonUpdate, buttonDelete;
    private TextView textViewResult;
    private com.example.studentdatabase.DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextCourse = findViewById(R.id.editTextCourse);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewResult = findViewById(R.id.textViewResult);

        // Initialize the SQLite helper class
        databaseHelper = new com.example.studentdatabase.DatabaseHelper(this);

        // Set onClickListeners for Add, Update, and Delete buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });

        // Display the initial student records
        displayRecords();
    }

    private void addStudent() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String course = editTextCourse.getText().toString();

        if (databaseHelper.insertStudent(id, name, course)) {
            Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
            displayRecords();
        } else {
            Toast.makeText(this, "Error Adding Student", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudent() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String course = editTextCourse.getText().toString();

        if (databaseHelper.updateStudent(id, name, course)) {
            Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
            displayRecords();
        } else {
            Toast.makeText(this, "Error Updating Student", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        String id = editTextId.getText().toString();

        if (databaseHelper.deleteStudent(id)) {
            Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
            displayRecords();
        } else {
            Toast.makeText(this, "Error Deleting Student", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayRecords() {
        textViewResult.setText(databaseHelper.getAllStudents());
    }
}
