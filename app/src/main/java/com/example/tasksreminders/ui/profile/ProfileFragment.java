package com.example.tasksreminders.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tasksreminders.R;
import com.example.tasksreminders.ui.tasks.TasksViewModel;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private TasksViewModel mTasksViewModel;
    private ProfileViewModel mProfileViewModel;
    private CircleImageView mProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

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

        mProfileImage = (CircleImageView) view.findViewById(R.id.profile_image);
        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                mProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}