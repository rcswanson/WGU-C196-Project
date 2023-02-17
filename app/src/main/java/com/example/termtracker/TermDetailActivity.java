package com.example.termtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Database.DatabaseHelper;
import Models.Course;
import Models.Term;

public class TermDetailActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private int termId;
    private TextView termTitleView;
    private TextView startDateView;
    private TextView endDateView;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        db = new DatabaseHelper(this);

        termTitleView = findViewById(R.id.term_title);
        startDateView = findViewById(R.id.start_date);
        endDateView = findViewById(R.id.end_date);
        deleteButton = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        termId = intent.getIntExtra("termId", 0);
        Term term = db.getTermWithCourses(termId);

        Date selectedStartDate = term.getStartDate().getTime(); // Get the selected start date
        Date selectedEndDate = term.getEndDate().getTime(); // Get the selected end date

        termTitleView.setText(term.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String startDateString = dateFormat.format(selectedStartDate);
        String endDateString = dateFormat.format(selectedEndDate);

        startDateView.setText(startDateString);
        endDateView.setText(endDateString);

        Button createCourseButton = findViewById(R.id.create_course_button);
        createCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to handle the button click, for example, starting a new activity to create a course
                Intent createCourseIntent = new Intent(TermDetailActivity.this, AddCourseActivity.class);
                startActivity(createCourseIntent);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteTerm(termId);
                finish();
            }
        });
    }
}