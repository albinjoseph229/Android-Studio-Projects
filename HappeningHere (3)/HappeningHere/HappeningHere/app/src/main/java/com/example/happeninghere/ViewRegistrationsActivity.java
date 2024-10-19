package com.example.happeninghere;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewRegistrationsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView registrationsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registrations);

        dbHelper = new DatabaseHelper(this);
        registrationsListView = findViewById(R.id.registrationsListView);

        loadRegistrations();
    }

    private void loadRegistrations() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to fetch event name, username, and phone for all registrations
        String query = "SELECT events.name AS eventName, users.username AS username, users.phone AS phone " +
                "FROM " + DatabaseHelper.TABLE_REGISTRATIONS + " AS registrations " +
                "INNER JOIN " + DatabaseHelper.TABLE_EVENTS + " AS events " +
                "ON registrations.event_id = events.id " +
                "INNER JOIN " + DatabaseHelper.TABLE_USERS + " AS users " +
                "ON registrations.user_id = users.id";

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> registrations = new ArrayList<>();
        while (cursor.moveToNext()) {
            // Fetch event name, username, and phone
            String eventName = cursor.getString(cursor.getColumnIndexOrThrow("eventName"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            String registration = "Event: " + eventName + "\n" +
                    "User: " + username + "\n" +
                    "Phone: " + phone;
            registrations.add(registration);
        }
        cursor.close();

        // Set up the adapter to display the registrations
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, registrations);
        registrationsListView.setAdapter(adapter);
    }
}
