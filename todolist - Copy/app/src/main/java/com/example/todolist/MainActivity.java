package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnAddTask;
    private RecyclerView rvTasks;
    private TaskAdapter taskAdapter;
    private TaskDatabase taskDatabase;
    private List<Task> taskList;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        rvTasks = findViewById(R.id.rvTasks);

        taskDatabase = TaskDatabase.getInstance(this);
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);

        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskAdapter);

        executorService = Executors.newSingleThreadExecutor();

        loadTasks();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTask.getText().toString();
                if (!taskName.isEmpty()) {
                    addTask(taskName);
                }
            }
        });
    }

    private void loadTasks() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final List<Task> tasks = taskDatabase.taskDao().getAllTasks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskList.clear();
                        taskList.addAll(tasks);
                        taskAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void addTask(final String taskName) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Task task = new Task(taskName, false);
                taskDatabase.taskDao().insert(task);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskList.add(task);
                        taskAdapter.notifyItemInserted(taskList.size() - 1);
                        etTask.setText("");
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}