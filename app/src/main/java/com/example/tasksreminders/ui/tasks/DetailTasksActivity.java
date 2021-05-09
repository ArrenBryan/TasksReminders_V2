package com.example.tasksreminders.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasksreminders.R;

public class DetailTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtasks);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.edit_task);

        Bundle bundle = getIntent().getExtras();

        TextView name = findViewById(R.id.textName);
        TextView deadline = findViewById(R.id.textDeadline);
        TextView description = findViewById(R.id.textDescription);

        name.setText(bundle.getString("name"));
        deadline.setText(bundle.getString("deadline"));
        description.setText(bundle.getString("description"));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_detailtasks_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = getIntent().getExtras();

        if (id == R.id.mark_as_done) {
            Toast.makeText(getApplicationContext(), "Welldone! Your task is done now!", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.delete_task) {
            Intent replyIntent = new Intent();

            replyIntent.putExtra("action", "delete");
            replyIntent.putExtra("index", bundle.getString("index"));

            setResult(RESULT_OK, replyIntent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
