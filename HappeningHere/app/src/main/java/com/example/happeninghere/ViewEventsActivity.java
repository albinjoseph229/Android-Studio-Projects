package com.example.happeninghere;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewEventsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView eventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        dbHelper = new DatabaseHelper(this);
        eventsListView = findViewById(R.id.eventsListView);

        loadEvents();
    }

    private void loadEvents() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, description, date FROM events", null);

        ArrayList<String> events = new ArrayList<>();
        while (cursor.moveToNext()) {
            String event = "Name: " + cursor.getString(0) + "\nDescription: " + cursor.getString(1) + "\nDate: " + cursor.getString(2);
            events.add(event);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        eventsListView.setAdapter(adapter);
    }
}