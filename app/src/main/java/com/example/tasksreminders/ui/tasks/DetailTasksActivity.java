package com.example.tasksreminders.ui.tasks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasksreminders.R;
import java.util.Calendar;

public class DetailTasksActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText mName;
    private EditText mDescription;
    private TextView mDeadline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtasks);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.edit_task);

        Bundle bundle = getIntent().getExtras();

        mName = findViewById(R.id.textName);
        mDeadline = findViewById(R.id.textDeadline);
        mDescription = findViewById(R.id.textDescription);

        mName.setText(bundle.getString("name"));
        mDeadline.setText(bundle.getString("deadline"));
        mDescription.setText(bundle.getString("description"));

        mDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        DetailTasksActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mDay = String.valueOf(dayOfMonth);
                String mMonth = String.valueOf(month + 1);

                if (Integer.parseInt(mMonth) <= 9) {
                    mMonth = "0" + mMonth;
                }

                if (Integer.parseInt(mDay) <= 9) {
                    mDay = "0" + mDay;
                }

                String deadline = mDay + "-" + mMonth + "-" + year;
                mDeadline.setText(deadline);
            }
        };

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

        if (id == R.id.delete_task) {
            Intent replyIntent = new Intent();

            replyIntent.putExtra("action", "delete");
            replyIntent.putExtra("index", bundle.getString("index"));

            setResult(RESULT_OK, replyIntent);

            finish();
        }

        if (id == R.id.update_task) {
            Intent replyIntent = new Intent();

            replyIntent.putExtra("action", "update");
            replyIntent.putExtra("index", bundle.getString("index"));
            replyIntent.putExtra("name", mName.getText().toString());
            replyIntent.putExtra("deadline", mDeadline.getText().toString());
            replyIntent.putExtra("description", mDescription.getText().toString());

            setResult(RESULT_OK, replyIntent);

            finish();
        }

        if (id == R.id.mark_as_done) {
            Intent replyIntent = new Intent();

            replyIntent.putExtra("action", "mark_as_done");
            replyIntent.putExtra("index", bundle.getString("index"));

            setResult(RESULT_OK, replyIntent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
