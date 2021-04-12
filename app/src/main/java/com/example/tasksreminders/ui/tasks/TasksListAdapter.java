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

    public TasksListAdapter(ArrayList<Tasks> datas) {
        this.datas = datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView description;
        public final TextView deadline;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textName);
            description = (TextView) view.findViewById(R.id.textDescription);
            deadline = (TextView)view.findViewById(R.id.textDeadline);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_tasks, parent, false);
        return new ViewHolder(view);
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
}
