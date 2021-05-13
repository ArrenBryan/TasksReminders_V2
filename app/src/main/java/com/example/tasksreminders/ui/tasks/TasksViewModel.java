package com.example.tasksreminders.ui.tasks;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {
    private TasksRepository mRepository;
    private final LiveData<List<Tasks>> mAllTasks;

    public TasksViewModel (Application application) {
        super(application);
        mRepository = new TasksRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    public LiveData<List<Tasks>> getAllTasks() { return mAllTasks; }

    public LiveData<List<Tasks>> getFilteredTasks(String searchQuery) { return mRepository.getFilteredTasks(searchQuery); }

    public void insert(Tasks task) { mRepository.insert(task); }

    public void delete(Tasks task) { mRepository.delete(task); }

    public void update(Tasks task) { mRepository.update(task); }

    public void deleteAll() { mRepository.deleteAll(); }
}
