package com.example.tasksreminders.ui.tasks;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tasksreminders.R;
import java.util.ArrayList;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {

    private final ArrayList<Tasks> datas;
    private OnNoteListener onNoteListener;

    public TasksListAdapter(ArrayList<Tasks> datas, OnNoteListener onNoteListener) {
        this.datas = datas;
        this.onNoteListener = onNoteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_tasks, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText(datas.get(position).getName());
        holder.description.setText(datas.get(position).getDescription());
        holder.deadline.setText(datas.get(position).getDeadline());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView name;
        public final TextView description;
        public final TextView deadline;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            name = (TextView) view.findViewById(R.id.textName);
            description = (TextView) view.findViewById(R.id.textDescription);
            deadline = (TextView)view.findViewById(R.id.textDeadline);
            this.onNoteListener = onNoteListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
