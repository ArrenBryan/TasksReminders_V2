package com.example.tasksreminders.ui.tasks;

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

public class TasksFragment extends Fragment {

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Tasks> datas = new ArrayList<>();
        datas.add(new Tasks("APSI", "01-04-2000", "Membuat DFD"));
        datas.add(new Tasks("PPL", "02-04-2000", "Membuat IOT"));
        datas.add(new Tasks("AndroidProgramming", "01-04-2000", "Membuat sistem berbasis Android"));
        datas.add(new Tasks("PKN", "01-04-2000", "Membuat makalah tentang sejarah Indonesia"));
        datas.add(new Tasks("MTK", "31-08-2001", "Membuat program Citra Digital"));
        datas.add(new Tasks("OOP", "23-03-2012", "Membuat program ATM"));

        recyclerView.setAdapter(new TasksListAdapter(datas));

        return view;
    }
}