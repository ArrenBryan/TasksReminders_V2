package com.example.tasksreminders.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tasksreminders.R;
import java.util.ArrayList;
import java.util.Collections;

public class TasksFragment extends Fragment implements TasksListAdapter.OnNoteListener {

    private RecyclerView recyclerView;
    private ArrayList<Tasks> datas = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        datas.add(new Tasks("APSI", "01-04-2020", "Membuat DFD"));
        datas.add(new Tasks("PPL", "02-04-2023", "Membuat IOT"));
        datas.add(new Tasks("AndroidProgramming", "01-04-2000", "Membuat sistem berbasis Android"));
        datas.add(new Tasks("PKN", "01-04-2000", "Membuat makalah tentang sejarah Indonesia"));
        datas.add(new Tasks("MTK", "31-08-2001", "Membuat program Citra Digital"));
        datas.add(new Tasks("OOP", "23-03-2012", "Membuat program ATM"));

        Collections.sort(datas);
        recyclerView.setAdapter(new TasksListAdapter(datas, this));

        return view;
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this.getContext(), DetailTasksActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("name", datas.get(position).getName());
        bundle.putString("deadline", datas.get(position).getDeadline());
        bundle.putString("description", datas.get(position).getDescription());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}