package com.example.cliniclocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView greetingTextView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        greetingTextView = findViewById(R.id.greetingTextView);
        button = findViewById(R.id.button);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set up the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                String selectedTitle = item.getTitle().toString(); // Get the title of the selected menu item

                // Handle item clicks based on selected title
                if (selectedTitle.equals("Home")) {
                    return true;  // Stay on the current activity

                } else if (selectedTitle.equals("Map")) {
                    // Open MapsActivity
                    Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(mapIntent);
                    overridePendingTransition(0, 0);  // Optionally prevent transition animations
                    return true;

                } else if (selectedTitle.equals("About Us")) {
                    // Open AboutUsActivity
                    Intent aboutIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                    startActivity(aboutIntent);
                    overridePendingTransition(0, 0);  // Optionally prevent transition animations
                    return true;

                } else if (selectedTitle.equals("Logout")) {
                    // Perform logout logic here
                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();  // Clear all data from SharedPreferences
                    editor.apply();  // Apply changes

                    // Navigate to LoginActivity
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();  // Finish the current activity to prevent going back
                    return true;
                }

                return false; // Return false if no matching item is found
            }
        });

        // Retrieve the user's name from the Intent (assuming it's passed from the login screen)
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // If the user's name is available, show a greeting
        if (username != null) {
            greetingTextView.setText("Hello, " + username + "!");
        } else {
            greetingTextView.setText("Hello, Guest!");
        }

        // Set up the button click to navigate to MapsActivity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open MapsActivity
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent); // Start the MapsActivity
            }
        });
    }
}
