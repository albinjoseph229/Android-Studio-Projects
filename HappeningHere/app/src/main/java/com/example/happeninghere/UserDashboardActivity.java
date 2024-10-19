package com.example.happeninghere;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserDashboardActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText eventIdEditText;
    private ListView eventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        dbHelper = new DatabaseHelper(this);
        eventIdEditText = findViewById(R.id.eventId);
        eventsListView = findViewById(R.id.eventsListView);

        loadEvents();
    }

    private void loadEvents() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id AS _id, name, description, date FROM events", null);

        String[] from = {"_id", "name", "description", "date"};
        int[] to = {R.id.eventIdTextView, R.id.eventNameTextView, R.id.eventDescriptionTextView, R.id.eventDateTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.event_list_item, cursor, from, to, 0);
        eventsListView.setAdapter(adapter);
    }

    public void viewEvents(View view) {
        startActivity(new Intent(this, ViewEventsActivity.class));
    }

    public void registerForEvent(View view) {
        String eventId = eventIdEditText.getText().toString();
        int userId = getCurrentUserId(); // Assume this method gets the current logged-in user's ID

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("event_id", eventId);

        long newRowId = db.insert("registrations", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Registered for event", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void unregisterFromEvent(View view) {
        String eventId = eventIdEditText.getText().toString();
        int userId = getCurrentUserId(); // Assume this method gets the current logged-in user's ID

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("registrations", "user_id=? AND event_id=?", new String[]{String.valueOf(userId), eventId});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Unregistered from event", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unregistration failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewRegistrations(View view) {
        startActivity(new Intent(this, ViewUserRegistrationsActivity.class));
    }

    private int getCurrentUserId() {
        // This method should return the current logged-in user's ID.
        // For demonstration purposes, let's assume it returns a fixed user ID.
        return 1; // Replace with actual logic to get the current user's ID
    }
}