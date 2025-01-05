package com.example.cliniclocator;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the EditText and Button
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Set up the login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Basic check to validate username and password
                if (username.equals("user") && password.equals("password123")) {
                    // Pass the username to MainActivity via Intent
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username); // Pass the username to MainActivity
                    startActivity(intent); // Start MainActivity
                } else {
                    // Show error if credentials are incorrect
                    usernameEditText.setError("Invalid username or password");
                    passwordEditText.setError("Invalid username or password");
                }
            }
        });
    }
}
