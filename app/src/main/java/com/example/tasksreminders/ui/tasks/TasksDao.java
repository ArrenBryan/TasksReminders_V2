package com.example.tasksreminders.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tasks task);

    @Delete
    void delete(Tasks task);

    @Update
    void update(Tasks task);

    @Query("DELETE FROM tasks_table")
    void deleteAll();

    @Query("SELECT * FROM tasks_table ORDER BY substr (deadline, 7, 10) ASC, substr (deadline, 1, 2) ASC, substr (deadline, 4, 5) ASC")
    LiveData<List<Tasks>> getSortedTasks();

    @Query("SELECT * FROM tasks_table WHERE name LIKE :searchQuery ORDER BY substr (deadline, 7, 10) ASC, substr (deadline, 1, 2) ASC, substr (deadline, 4, 5) ASC")
    LiveData<List<Tasks>> getSearchSortedTasks(String searchQuery);
}
