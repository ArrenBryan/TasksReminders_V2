package com.example.tasksreminders.ui.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tasksreminders.R;

public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView mName;
    public final TextView mDescription;
    public final TextView mDeadline;
    TasksListAdapter.OnNoteListener onNoteListener;

    private TasksViewHolder(View view, TasksListAdapter.OnNoteListener onNoteListener) {
        super(view);
        mName = view.findViewById(R.id.textName);
        mDescription = view.findViewById(R.id.textDescription);
        mDeadline = view.findViewById(R.id.textDeadline);
        this.onNoteListener = onNoteListener;

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }

    public void bind(String name, String description, String deadline) {
        mName.setText(name);
        mDescription.setText(description);
        mDeadline.setText(deadline);
    }

    static TasksViewHolder create(ViewGroup parent, TasksListAdapter.OnNoteListener onNoteListener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_tasks, parent, false);
        return new TasksViewHolder(view, onNoteListener);
    }
}
