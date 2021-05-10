package com.example.tasksreminders.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository mRepository;
    private final LiveData<List<Profile>> mAllProfile;

    public ProfileViewModel (Application application) {
        super(application);
        mRepository = new ProfileRepository(application);
        mAllProfile = mRepository.getCompletedValue();
    }

    public LiveData<List<Profile>> getAllProfile() { return mRepository.getCompletedValue(); }

    public void insert(Profile profile) { mRepository.insert(profile);  }

    public void update(Profile profile) { mRepository.update(profile); }
}
