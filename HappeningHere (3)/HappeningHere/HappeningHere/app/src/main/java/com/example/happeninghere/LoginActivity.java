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
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Here, you should hash the password before checking it against the database
        String hashedPassword = hashPassword(password); // Implement this method to hash the password

        Cursor cursor = db.rawQuery("SELECT role FROM users WHERE username=? AND password=?", new String[]{username, hashedPassword});

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

    private String hashPassword(String password) {
        // Implement password hashing logic here, e.g., using BCrypt or SHA-256
        return password; // Placeholder: replace with actual hashing logic
    }

    public void goToRegistration(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
