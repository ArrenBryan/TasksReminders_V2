package com.example.tasksreminders.ui.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasksreminders.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TasksFragment extends Fragment implements TasksListAdapter.OnNoteListener {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TasksViewModel mTasksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        setHasOptionsMenu(true);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TasksListAdapter adapter = new TasksListAdapter(new TasksListAdapter.TasksDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mTasksViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TasksViewModel.class);
        mTasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(tasks);
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener( View -> {
            Intent intent = new Intent(getContext(), InsertTasksActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Tasks tasks = new Tasks(
                    data.getStringExtra(InsertTasksActivity.EXTRA_REPLY),
                    data.getStringExtra("description"),
                    data.getStringExtra("deadline")
            );
            mTasksViewModel.insert(tasks);
            Toast.makeText(
                    getContext(),
                    R.string.data_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNoteClick(int position) {
        List<Tasks> datas = mTasksViewModel.getAllTasks().getValue();

        Intent intent = new Intent(this.getContext(), DetailTasksActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("name", datas.get(position).getName());
        bundle.putString("deadline", datas.get(position).getDeadline());
        bundle.putString("description", datas.get(position).getDescription());
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_tasks_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete_all_button) {
            mTasksViewModel.deleteAll();
            Toast.makeText(getContext(), "All tasks has been deleted!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}