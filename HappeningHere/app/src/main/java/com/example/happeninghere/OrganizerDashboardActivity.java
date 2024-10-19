package com.example.happeninghere;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class OrganizerDashboardActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText eventNameEditText, eventDescriptionEditText, eventDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_dashboard);

        dbHelper = new DatabaseHelper(this);
        eventNameEditText = findViewById(R.id.eventName);
        eventDescriptionEditText = findViewById(R.id.eventDescription);
        eventDateEditText = findViewById(R.id.eventDate);
    }

    public void showAddEventDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        AddEventDialogFragment addEventDialogFragment = new AddEventDialogFragment();
        addEventDialogFragment.show(fm, "fragment_add_event");
    }

    public void removeEvent(View view) {
        String name = eventNameEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter the event name", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("events", "name = ?", new String[]{name});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Event removed", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to remove event", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewEvents(View view) {
        startActivity(new Intent(this, ViewEventsActivity.class));
    }

    public void viewRegistrations(View view) {
        startActivity(new Intent(this, ViewRegistrationsActivity.class));
    }

    private void clearFields() {
        eventNameEditText.setText("");
        eventDescriptionEditText.setText("");
        eventDateEditText.setText("");
    }
}