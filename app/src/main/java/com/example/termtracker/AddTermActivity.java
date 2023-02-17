package com.example.termtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import Database.DatabaseHelper;

public class AddTermActivity extends AppCompatActivity {

    private EditText termTitleEditText;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        termTitleEditText = findViewById(R.id.termNameEditText);
        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);
        databaseHelper = new DatabaseHelper(this);

        // Gives the save button a function
        Button saveTermButton = findViewById(R.id.addTermButton);
        saveTermButton.setOnClickListener(view -> {
            String termTitle = termTitleEditText.getText().toString();

            int startYear = startDatePicker.getYear();
            int startMonth = startDatePicker.getMonth();
            int startDay = startDatePicker.getDayOfMonth();
            Calendar startDate = Calendar.getInstance();
            startDate.set(startYear, startMonth, startDay);

            int endYear = endDatePicker.getYear();
            int endMonth = endDatePicker.getMonth();
            int endDay = endDatePicker.getDayOfMonth();
            Calendar endDate = Calendar.getInstance();
            startDate.set(endYear, endMonth, endDay);

            boolean isInserted = databaseHelper.insertTerm(termTitle, startDate, endDate);
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            boolean result = db.insertTerm(termTitle, startDate, endDate);

            // display a message to the user indicating whether the term was added successfully
            if (result) {
                Toast.makeText(getApplicationContext(), "Term added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to add term", Toast.LENGTH_SHORT).show();
            }

            // navigate back to the term list activity
            Intent intent = new Intent(this, TermDetailActivity.class);
            startActivity(intent);
        });
    }
}
