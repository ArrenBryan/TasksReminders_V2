package com.example.tasksreminders.ui.tasks;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksRepository {
    private TasksDao mTasksDao;
    private LiveData<List<Tasks>> mAllTasks;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    TasksRepository(Application application) {
        TasksRoomDatabase db = TasksRoomDatabase.getDatabase(application);
        mTasksDao = db.tasksDao();
        mAllTasks = mTasksDao.getSortedTasks();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Tasks>> getAllTasks() {
        return mAllTasks;
    }

    LiveData<List<Tasks>> getFilteredTasks(String searchQuery) {
        return mTasksDao.getSearchSortedTasks(searchQuery);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Tasks task) {
        TasksRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTasksDao.insert(task);
        });
    }

    void delete(Tasks task) {
        TasksRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTasksDao.delete(task);
        });
    }

    void update(Tasks task) {
        TasksRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTasksDao.update(task);
        });
    }

    void deleteAll() {
        TasksRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTasksDao.deleteAll();
        });
    }
}
