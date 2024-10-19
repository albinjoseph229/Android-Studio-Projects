package com.example.todolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id; // Unique ID for each task
    private String name;
    private boolean isDone;
    private String dueDate;   // Stores due date as a String (or use Date if desired)
    private String label;     // Stores task label (optional)
    private int colorCode;    // Stores color code (optional)

    // Constructor
    public Task(String name, boolean isDone, String dueDate, String label, int colorCode) {
        this.name = name;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.label = label;
        this.colorCode = colorCode;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public int getColorCode() { return colorCode; }
    public void setColorCode(int colorCode) { this.colorCode = colorCode; }
}
