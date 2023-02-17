package com.example.termtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHelper;
import Models.Term;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ListView listView;
    private List<String> termList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        listView = findViewById(R.id.list_terms);
        termList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String term = termList.get(position);
                Intent intent = new Intent(MainActivity.this, TermDetailActivity.class);
                intent.putExtra("termId", Term.getId());
                startActivity(intent);
            }
        });

        Button addTermBtn = findViewById(R.id.btn_add_term);
        addTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTermActivity.class);
                startActivity(intent);
            }
        });

        updateTermList();
    }

    private void updateTermList() {
        termList.clear();
        List<Term> terms = db.getAllTerms();
        for (Term term : terms) {
            termList.add(term.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTermList();
    }
}
