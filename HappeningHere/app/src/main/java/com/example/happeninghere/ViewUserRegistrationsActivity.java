package com.example.happeninghere;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ViewUserRegistrationsActivity extends AppCompatActivity {
    private static final String TAG = "ViewUserRegistrations";
    private DatabaseHelper dbHelper;
    private ListView registrationsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_registrations);

        dbHelper = new DatabaseHelper(this);
        registrationsListView = findViewById(R.id.registrationsListView);

        loadRegistrations();
    }

    private void loadRegistrations() {
        int userId = getCurrentUserId(); // Assume this method gets the current logged-in user's ID
        Log.d(TAG, "Current User ID: " + userId);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT events.id AS _id, events.name, events.description, events.date FROM events INNER JOIN registrations ON events.id = registrations.event_id WHERE registrations.user_id = ?", new String[]{String.valueOf(userId)});

        if (cursor != null) {
            Log.d(TAG, "Number of registered events: " + cursor.getCount());
            String[] from = {"_id", "name", "description", "date"};
            int[] to = {R.id.eventIdTextView, R.id.eventNameTextView, R.id.eventDescriptionTextView, R.id.eventDateTextView};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.event_list_item, cursor, from, to, 0);
            registrationsListView.setAdapter(adapter);
        } else {
            Log.d(TAG, "Cursor is null");
        }
    }

    private int getCurrentUserId() {
        // This method should return the current logged-in user's ID.
        // For demonstration purposes, let's assume it returns a fixed user ID.
        return 1; // Replace with actual logic to get the current user's ID
    }
}