package com.example.tasksreminders.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Query("DELETE FROM profile_table")
    void deleteAll();

    @Query("SELECT * FROM profile_table")
    LiveData<List<Profile>> getCompletedValue();
}
