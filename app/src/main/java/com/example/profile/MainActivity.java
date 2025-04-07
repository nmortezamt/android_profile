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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText firstNameInput = findViewById(R.id.firstNameInput);
        EditText lastNameInput = findViewById(R.id.lastNameInput);
        EditText ageInput = findViewById(R.id.ageInput);
        EditText bioInput = findViewById(R.id.bioInput);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("firstName", firstNameInput.getText().toString());
            editor.putString("lastName", lastNameInput.getText().toString());
            editor.putString("age", ageInput.getText().toString());
            editor.putString("bio", bioInput.getText().toString());
            editor.apply();

            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });
    }
}