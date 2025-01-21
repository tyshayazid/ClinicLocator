package com.example.cliniclocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Button for external link
        Button btnLink = findViewById(R.id.button2);
        btnLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/tyshayazid/Electric_Bill.1/tree/main/app"));
            startActivity(browserIntent);
        });

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Highlight "About Us" as the selected item
        bottomNavigationView.getMenu().findItem(R.id.nav_about).setChecked(true);

        // Handle navigation item selection using titles
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String selectedTitle = item.getTitle().toString(); // Get the title of the selected menu item

                // Navigate based on the selected title
                if (selectedTitle.equals("Home")) {
                    Intent homeIntent = new Intent(AboutUsActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    overridePendingTransition(0, 0);
                    return true;

                } else if (selectedTitle.equals("Map")) {
                    Intent mapIntent = new Intent(AboutUsActivity.this, MapsActivity.class);
                    startActivity(mapIntent);
                    overridePendingTransition(0, 0);
                    return true;

                } else if (selectedTitle.equals("About Us")) {
                    // Stay on the current activity
                    return true;

                } else if (selectedTitle.equals("Logout")) {
                    // Perform logout logic here
                    // Clear user session, SharedPreferences, or other data

                    // Example of clearing SharedPreferences:
                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();  // Clear all data
                    editor.apply();  // Apply changes

                    // Navigate to login screen
                    Intent loginIntent = new Intent(AboutUsActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();  // Optionally finish current activity to prevent going back
                    return true;
                }

                return false;
            }
        });
    }
}
