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

        // Include the event ID in the query
        Cursor cursor = db.rawQuery("SELECT id, name, description, date FROM events", null);

        ArrayList<String> events = new ArrayList<>();
        while (cursor.moveToNext()) {
            // Fetch event ID, name, description, and date
            int eventId = cursor.getInt(0);
            String eventName = cursor.getString(1);
            String eventDescription = cursor.getString(2);
            String eventDate = cursor.getString(3);

            // Format the event details to include the ID
            String event = "ID: " + eventId + "\nName: " + eventName + "\nDescription: " + eventDescription + "\nDate: " + eventDate;
            events.add(event);
        }
        cursor.close();

        // Set up the adapter to display the events
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        eventsListView.setAdapter(adapter);
    }
}
