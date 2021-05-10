package com.example.tasksreminders.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasksreminders.R;
import com.example.tasksreminders.ui.tasks.TasksViewModel;

public class ProfileFragment extends Fragment {
    private TasksViewModel mTasksViewModel;
    private ProfileViewModel mProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView pendingTasks = view.findViewById(R.id.pending_tasks);
        TextView completedTasks = view.findViewById(R.id.completed_tasks);

        mTasksViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TasksViewModel.class);
        mTasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            pendingTasks.setText(String.valueOf(tasks.size()));
        });

        mProfileViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ProfileViewModel.class);
        mProfileViewModel.getAllProfile().observe(getViewLifecycleOwner(), profiles -> {
            // Update the cached copy of the words in the adapter.
            completedTasks.setText(String.valueOf(profiles.get(0).getValue()));
        });

        return view;
    }
}