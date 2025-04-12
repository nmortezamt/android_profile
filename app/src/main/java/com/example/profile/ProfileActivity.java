package com.example.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    TextView fullNameText, ageText, bioText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        fullNameText = findViewById(R.id.fullNameText);
        ageText = findViewById(R.id.ageText);
        bioText = findViewById(R.id.bioText);

        SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        String firstName = prefs.getString("firstName", "");
        String lastName = prefs.getString("lastName", "");
        String age = prefs.getString("age", "");
        String bio = prefs.getString("bio", "");

        fullNameText.setText("Full Name: " + firstName + " " + lastName);
        ageText.setText("Age: " + age);
        bioText.setText("Bio: " + bio);

        Button openCalculatorBtn = findViewById(R.id.openCalculatorBtn);

        openCalculatorBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
