package com.example.happeninghere;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class OrganizerDashboardActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText eventIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_dashboard);

        dbHelper = new DatabaseHelper(this);
        eventIdEditText = findViewById(R.id.eventId);
    }

    public void showAddEventDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        AddEventDialogFragment addEventDialogFragment = new AddEventDialogFragment();
        addEventDialogFragment.show(fm, "fragment_add_event");
    }

    public void removeEvent(View view) {
        String idStr = eventIdEditText.getText().toString().trim();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter the event ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int eventId = Integer.parseInt(idStr);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("events", "id = ?", new String[]{String.valueOf(eventId)});
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
        eventIdEditText.setText("");
    }
}