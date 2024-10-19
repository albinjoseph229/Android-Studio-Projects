package com.example.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();

                    Log.d(TAG, "Attempting login with email: " + email);

                    // Retrieve stored credentials from SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    String storedEmail = sharedPreferences.getString("email", null);
                    String storedPassword = sharedPreferences.getString("password", null);

                    Log.d(TAG, "Stored email: " + storedEmail);
                    Log.d(TAG, "Stored password: " + storedPassword);

                    if (storedEmail == null || storedPassword == null) {
                        Toast.makeText(LoginActivity.this, "No registered user found. Please register first.", Toast.LENGTH_SHORT).show();
                    } else if (email.equals(storedEmail) && password.equals(storedPassword)) {
                        Log.d(TAG, "Login successful");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.d(TAG, "Invalid credentials");
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error during login", e);
                    Toast.makeText(LoginActivity.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}