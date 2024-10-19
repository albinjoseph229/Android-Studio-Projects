package com.example.todolist;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private ExecutorService executorService;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.tvTaskName.setText(task.getName());
        holder.cbDone.setChecked(task.isDone());

        // Handle task completion checkbox
        holder.cbDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setDone(isChecked);
            updateTask(task);
        });

        // Handle delete button click event
        holder.btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(task, holder.getAdapterPosition(), holder.itemView);
            }
        });
    }

    // Method to delete the task from the database and remove it from the list
    private void deleteTask(final Task task, final int position, final View view) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                TaskDatabase.getInstance(null).taskDao().delete(task);
                taskList.remove(position); // Remove the task from the list

                // Use Handler to run on the main thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyItemRemoved(position); // Notify the adapter about the removed item
                    }
                });
            }
        });
    }

    // Method to update a task in the database
    private void updateTask(final Task task) {
        executorService.execute(() -> {
            TaskDatabase.getInstance(null).taskDao().update(task);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // Clean up the executor when no longer needed
    public void shutdown() {
        executorService.shutdown();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskName;
        CheckBox cbDone;
        Button btnDeleteTask; // Reference to the delete button

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            cbDone = itemView.findViewById(R.id.cbDone);
            btnDeleteTask = itemView.findViewById(R.id.btnDeleteTask); // Initialize delete button
        }
    }
}
