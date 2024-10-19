package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddTask = findViewById(R.id.btnAddTask);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList, this);  // Correctly passing taskList and context

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskCreationActivity.class);
                startActivity(intent);
            }
        });

        // Load tasks from database (if applicable)
        loadTasks();
    }

    private void loadTasks() {
        // Load tasks from the database and update taskList
        // Notify adapter about the data change
        adapter.notifyDataSetChanged();
    }
}
