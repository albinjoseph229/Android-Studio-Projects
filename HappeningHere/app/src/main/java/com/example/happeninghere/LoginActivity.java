package com.example.happeninghere;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private DatabaseHelper dbHelper;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }

    public void login(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT role FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            String role = cursor.getString(0).trim().toLowerCase();
            Log.d(TAG, "User role: " + role); // Debugging log
            if ("organizer".equals(role)) {
                startActivity(new Intent(this, OrganizerDashboardActivity.class));
            } else if ("user".equals(role)) {
                startActivity(new Intent(this, UserDashboardActivity.class));
            } else {
                Toast.makeText(this, "Unknown role: " + role, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public void goToRegistration(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}