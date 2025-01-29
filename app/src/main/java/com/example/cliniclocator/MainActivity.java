package com.example.cliniclocator;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView greetingTextView, locationTextView;
    BottomNavigationView bottomNavigationView;

    // Firebase instance variables
    FirebaseAuth mAuth;

    // Location services
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        greetingTextView = findViewById(R.id.greetingTextView);
        locationTextView = findViewById(R.id.locationTextView); // TextView to display location
        button = findViewById(R.id.button);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Set up bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            String selectedTitle = item.getTitle().toString();

            if (selectedTitle.equals("Home")) {
                return true;
            } else if (selectedTitle.equals("Map")) {
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
                overridePendingTransition(0, 0);
                return true;
            } else if (selectedTitle.equals("About Us")) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(aboutIntent);
                overridePendingTransition(0, 0);
                return true;
            } else if (selectedTitle.equals("Logout")) {
                SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
                return true;
            }
            return false;
        });

        // Check if the user is logged in
        if (mAuth.getCurrentUser() != null) {
            String email = mAuth.getCurrentUser().getEmail();
            if (email != null) {
                // Extract the username part before the '@' symbol
                String username = email.split("@")[0];
                greetingTextView.setText("Hello, " + username + "!");
            } else {
                greetingTextView.setText("Hello, Guest!");
            }
        } else {
            greetingTextView.setText("Hello, Guest!");
        }


        // Get user's location
        getLocation();

        // Set up the button click to navigate to MapsActivity
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });
    }

    // Method to get the user's location
    private void getLocation() {
        // Check if location permissions are granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
            return;
        }

        // Fetch the last known location
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Display the location in the TextView
                locationTextView.setText("Your location: " + latitude + ", " + longitude);
            } else {
                locationTextView.setText("Unable to fetch location.");
            }
        }).addOnFailureListener(e -> {
            locationTextView.setText("Failed to fetch location: " + e.getMessage());
        });
    }

    // Handle location permission results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch location
                getLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showToast(String s) {
    }
}
