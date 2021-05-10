package com.example.tasksreminders.ui.profile;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileRepository {
    private ProfileDao mProfileDao;
    private LiveData<List<Profile>> mAllProfile;

    ProfileRepository(Application application) {
        ProfileRoomDatabase db = ProfileRoomDatabase.getDatabase(application);
        mProfileDao = db.profileDao();
        mAllProfile = mProfileDao.getCompletedValue();
    }

    LiveData<List<Profile>> getCompletedValue() {
        return mAllProfile;
    }

    void insert(Profile profile) {
        ProfileRoomDatabase.databaseWriteExecutor.execute(() -> {
            mProfileDao.insert(profile);
        });
    }

    void update(Profile profile) {
        ProfileRoomDatabase.databaseWriteExecutor.execute(() -> {
            mProfileDao.update(profile);
        });
    }
}
