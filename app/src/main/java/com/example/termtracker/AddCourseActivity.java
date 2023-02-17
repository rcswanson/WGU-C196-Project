package com.example.termtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import Database.DatabaseHelper;
import Models.Course;
import Models.Term;

public class AddCourseActivity extends AppCompatActivity {

    private EditText courseNameEditText;
    private EditText instNameEditText;
    private EditText instPhoneEditText;
    private EditText instEmailEditText;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Spinner statusSpinner;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseNameEditText = findViewById(R.id.edit_text_title);
        instNameEditText = findViewById(R.id.edit_text_instructor_name);
        instPhoneEditText = findViewById(R.id.edit_text_instructor_phone);
        instEmailEditText = findViewById(R.id.edit_text_instructor_email);
        startDatePicker = findViewById(R.id.start_date_picker);
        endDatePicker = findViewById(R.id.end_date_picker);
        statusSpinner = findViewById(R.id.status_spinner);
        Button saveButton = findViewById(R.id.button_save_course);

        databaseHelper = new DatabaseHelper(this);

        // Get the term object passed from the TermDetailActivity
        Intent intent = getIntent();
        if (intent.hasExtra("term")) {
            Term term = (Term) intent.getSerializableExtra("term");
        }

        saveButton.setOnClickListener(v -> {
            String name = courseNameEditText.getText().toString();
            String instructorName = instNameEditText.getText().toString();
            String instructorPhone = instPhoneEditText.getText().toString();
            String instructorEmail = instEmailEditText.getText().toString();

            // Get start date from date picker
            int startYear = startDatePicker.getYear();
            int startMonth = startDatePicker.getMonth();
            int startDay = startDatePicker.getDayOfMonth();
            Calendar startDate = Calendar.getInstance();
            startDate.set(startYear, startMonth, startDay);

            // Get end date from date picker
            int endYear = endDatePicker.getYear();
            int endMonth = endDatePicker.getMonth();
            int endDay = endDatePicker.getDayOfMonth();
            Calendar endDate = Calendar.getInstance();
            endDate.set(endYear, endMonth, endDay);

            String status = statusSpinner.getSelectedItem().toString();

            // Add course to the database and close activity
            databaseHelper.insertCourse(name, startDate, endDate, status,
                    instructorName, instructorPhone, instructorEmail, Term.getId());
            setResult(RESULT_OK);
            finish();
        });
    }
}
