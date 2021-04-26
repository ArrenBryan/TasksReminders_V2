package com.example.tasksreminders.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tasks task);

    @Query("DELETE FROM tasks_table")
    void deleteAll();

    @Query("SELECT * FROM tasks_table ORDER BY substr (deadline, 7, 10) ASC, substr (deadline, 1, 2) ASC, substr (deadline, 4, 5) ASC")
    LiveData<List<Tasks>> getAlphabetizedWords();
}
