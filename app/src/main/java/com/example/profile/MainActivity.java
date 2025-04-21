package com.example.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String firstName = prefs.getString("firstName", null);
        String lastName = prefs.getString("lastName", null);
        String age = prefs.getString("age", null);
        String bio = prefs.getString("bio", null);

        if (firstName != null && lastName != null && age != null && bio != null) {
            // If all fields are present, go to ProfileActivity
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("age", age);
            intent.putExtra("bio", bio);
            startActivity(intent);
            finish(); // Finish MainActivity so user can't go back to it
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText firstNameInput = findViewById(R.id.firstNameInput);
        EditText lastNameInput = findViewById(R.id.lastNameInput);
        EditText ageInput = findViewById(R.id.ageInput);
        EditText bioInput = findViewById(R.id.bioInput);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(view -> {
            String first = firstNameInput.getText().toString();
            String last = lastNameInput.getText().toString();
            String userAge = ageInput.getText().toString();
            String userBio = bioInput.getText().toString();

            // Save in SharedPreferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("firstName", first);
            editor.putString("lastName", last);
            editor.putString("age", userAge);
            editor.putString("bio", userBio);
            editor.apply();

            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            profileIntent.putExtra("firstName", first);
            profileIntent.putExtra("lastName", last);
            profileIntent.putExtra("age", userAge);
            profileIntent.putExtra("bio", userBio);
            startActivity(profileIntent);
            finish();
        });
    }
}