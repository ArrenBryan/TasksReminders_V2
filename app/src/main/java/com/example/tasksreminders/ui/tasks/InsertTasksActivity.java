package com.example.tasksreminders.ui.tasks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasksreminders.R;

import java.util.Calendar;

public class InsertTasksActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText mName;
    private EditText mDescription;
    private TextView mDeadline;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserttasks);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.insert_task);

        mName = findViewById(R.id.textInputName);
        mDescription = findViewById(R.id.textInputDecription);
        mDeadline = findViewById(R.id.dateInput);
        button = findViewById(R.id.button_save);

        TextWatcher dataInputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nameInput = mName.getText().toString().trim();
                String descriptionInput = mDescription.getText().toString().trim();
                String deadlineInput = mDeadline.getText().toString();

                button.setEnabled(!nameInput.isEmpty() && !descriptionInput.isEmpty() && !deadlineInput.equals("Select Deadline"));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mName.addTextChangedListener(dataInputWatcher);
        mDescription.addTextChangedListener(dataInputWatcher);
        mDeadline.addTextChangedListener(dataInputWatcher);

        mDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        InsertTasksActivity.this,
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

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            replyIntent.putExtra("name", mName.getText().toString());
            replyIntent.putExtra("deadline", mDeadline.getText().toString());
            replyIntent.putExtra("description", mDescription.getText().toString());

            setResult(RESULT_OK, replyIntent);

            finish();
        });
    }
}
