package com.example.tasksreminders.ui.profile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "profile_table")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private int value;

    public Profile(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }
}
