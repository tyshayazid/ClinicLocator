package com.example.cliniclocator;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.cliniclocator.R;  // Ensure this import is present

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView for the greeting and the Button
        greetingTextView = findViewById(R.id.greetingTextView);
        button = findViewById(R.id.button);

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
