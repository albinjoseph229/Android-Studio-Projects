package com.example.happeninghere;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText nameEditText, emailEditText, phoneEditText, usernameEditText, passwordEditText;
    private RadioGroup roleRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        roleRadioGroup = findViewById(R.id.roleRadioGroup);
    }

    public void register(View view) {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        int selectedRoleId = roleRadioGroup.getCheckedRadioButtonId();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                username.isEmpty() || password.isEmpty() || selectedRoleId == -1) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRoleButton = findViewById(selectedRoleId);
        String role = selectedRoleButton.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_USERNAME},
                DatabaseHelper.COLUMN_USERNAME + " = ?",
                new String[]{username}, null, null, null);

        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return;
        }
        cursor.close();

        String hashedPassword = hashPassword(password);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, hashedPassword);
        values.put(DatabaseHelper.COLUMN_ROLE, role);

        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private String hashPassword(String password) {
        return password; // Implement password hashing (e.g., SHA-256 or BCrypt)
    }
}
