package com.example.cliniclocator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton;

    // Define a HashMap to store multiple usernames and passwords
    HashMap<String, String> credentials = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the EditText and Button
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Add valid usernames and passwords to the HashMap
        credentials.put("tysha", "tysha123");
        credentials.put("ain", "ain123");
        credentials.put("mazidah", "maz123");
        credentials.put("aiman","aiman123");

        // Set up the login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered username and password
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate the entered credentials
                if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                    // Pass the username to MainActivity via Intent
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username); // Pass the username to MainActivity
                    startActivity(intent); // Start MainActivity
                } else {
                    // Show error if credentials are incorrect
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    usernameEditText.setError("Invalid username or password");
                    passwordEditText.setError("Invalid username or password");
                }
            }
        });
    }
}
