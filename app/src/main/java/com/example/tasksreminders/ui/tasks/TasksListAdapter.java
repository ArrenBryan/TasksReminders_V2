package com.example.tasksreminders.ui.tasks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import android.view.ViewGroup;

public class TasksListAdapter extends ListAdapter<Tasks, TasksViewHolder> {

    private OnNoteListener onNoteListener;

    public TasksListAdapter(@NonNull DiffUtil.ItemCallback<Tasks> diffCallback, OnNoteListener onNoteListener) {
        super(diffCallback);
        this.onNoteListener = onNoteListener;
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Tasks current = getItem(position);
        holder.bind(current.getName(), current.getDescription(), current.getDeadline());
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TasksViewHolder.create(parent, onNoteListener);
    }

    static class TasksDiff extends DiffUtil.ItemCallback<Tasks> {
        @Override
        public boolean areItemsTheSame(@NonNull Tasks oldItem, @NonNull Tasks newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tasks oldItem, @NonNull Tasks newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
