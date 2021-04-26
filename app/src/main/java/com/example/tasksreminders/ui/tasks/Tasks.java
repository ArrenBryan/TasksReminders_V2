package com.example.tasksreminders.ui.tasks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Tasks {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tasks")
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "deadline")
    private String deadline;

    public Tasks(String name, String deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDeadline(@NonNull String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
