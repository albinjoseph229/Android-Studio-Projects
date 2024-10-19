package com.example.happeninghere;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddEventDialogFragment extends DialogFragment {

    private DatabaseHelper dbHelper;
    private EditText eventNameEditText, eventDescriptionEditText, eventDateEditText, eventPlaceEditText, eventIdEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_event, container, false);

        dbHelper = new DatabaseHelper(getContext());

        // Initialize all fields
        eventIdEditText = view.findViewById(R.id.eventId);
        eventNameEditText = view.findViewById(R.id.eventName);
        eventDescriptionEditText = view.findViewById(R.id.eventDescription);
        eventDateEditText = view.findViewById(R.id.eventDate);
        eventPlaceEditText = view.findViewById(R.id.eventPlace);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> addEvent());

        return view;
    }

    private void addEvent() {
        String name = eventNameEditText.getText().toString().trim();
        String description = eventDescriptionEditText.getText().toString().trim();
        String date = eventDateEditText.getText().toString().trim();
        String place = eventPlaceEditText.getText().toString().trim();

        // Check if all fields are filled
        if (name.isEmpty() || description.isEmpty() || date.isEmpty() || place.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("date", date);
        values.put("place", place);  // Add place to the database

        // Insert the event into the database
        long newRowId = db.insert("events", null, values);
        if (newRowId != -1) {
            Toast.makeText(getContext(), "Event added", Toast.LENGTH_SHORT).show();
            eventIdEditText.setText(String.valueOf(newRowId));  // Display the auto-generated Event ID
            dismiss();
        } else {
            Toast.makeText(getContext(), "Failed to add event", Toast.LENGTH_SHORT).show();
        }
    }
}
