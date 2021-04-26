package com.example.tasksreminders.ui.tasks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tasks.class}, version = 1, exportSchema = false)
public abstract class TasksRoomDatabase extends RoomDatabase {
    public abstract TasksDao tasksDao();

    private static volatile TasksRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TasksRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TasksRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TasksRoomDatabase.class, "tasks_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more tasks, just add them.
                TasksDao dao = INSTANCE.tasksDao();
                dao.deleteAll();

                Tasks task = new Tasks("APSI", "01-04-2020", "Membuat DFD");
                dao.insert(task);

                task = new Tasks("PPL", "04-02-2023", "Membuat IOT");
                dao.insert(task);

                task = new Tasks("AndroidProgramming", "04-01-2000", "Membuat sistem berbasis Android");
                dao.insert(task);

                task = new Tasks("PKN", "03-01-2000", "Membuat makalah tentang sejarah Indonesia");
                dao.insert(task);

                task = new Tasks("MTK", "31-08-2001", "Membuat program Citra Digital");
                dao.insert(task);

                task = new Tasks("OOP", "23-03-2012", "Membuat program ATM");
                dao.insert(task);
            });
        }
    };
}
