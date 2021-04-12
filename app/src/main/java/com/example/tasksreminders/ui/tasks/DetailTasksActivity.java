package com.example.tasksreminders.ui.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasksreminders.R;

public class DetailTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtasks);

        Bundle bundle = getIntent().getExtras();

        TextView name = findViewById(R.id.textName);
        TextView deadline = findViewById(R.id.textDeadline);
        TextView description = findViewById(R.id.textDescription);

        name.setText(bundle.getString("name"));
        deadline.setText(bundle.getString("deadline"));
        description.setText(bundle.getString("description"));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
