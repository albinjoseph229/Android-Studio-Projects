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
        Cursor cursor = db.rawQuery("SELECT events.name, users.username FROM registrations INNER JOIN events ON registrations.event_id = events.id INNER JOIN users ON registrations.user_id = users.id", null);

        ArrayList<String> registrations = new ArrayList<>();
        while (cursor.moveToNext()) {
            String registration = "Event: " + cursor.getString(0) + "\nUser: " + cursor.getString(1);
            registrations.add(registration);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, registrations);
        registrationsListView.setAdapter(adapter);
    }
}