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
        Button logoutButton = findViewById(R.id.logoutButton);
        Button exam = findViewById(R.id.exam);
        exam.setOnClickListener(v -> {
            Intent examIntent = new Intent(this, com.example.profile.exam.MainActivity.class);
            startActivity(examIntent);
        });
        logoutButton.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // This removes all data
            editor.apply();

            // Go back to MainActivity
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish ProfileActivity so it can't be returned to
        });

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String age = intent.getStringExtra("age");
        String bio = intent.getStringExtra("bio");
        UserData userData = new UserData(firstName, lastName, age, bio);

        fullNameText.setText("Full Name: " + userData.getFullName());
        ageText.setText("Age: " + userData.getAge());
        bioText.setText("Bio: " + userData.getBio());

        Button openCalculatorBtn = findViewById(R.id.openCalculatorBtn);

        openCalculatorBtn.setOnClickListener(v -> {
            Intent calcIntent  = new Intent(ProfileActivity.this, CalculatorActivity.class);
            startActivity(calcIntent);
        });

        Button splashBtn = findViewById(R.id.splashButton);
        splashBtn.setOnClickListener(v -> {
            Intent splashIntent  = new Intent(ProfileActivity.this, SplashActivity.class);
            startActivity(splashIntent);
        });
        Button RecyclerViewBtn = findViewById(R.id.RecyclerView);
        RecyclerViewBtn.setOnClickListener(v -> {
            Intent RecyclerRecyclerViewiewIntent  = new Intent(ProfileActivity.this, RecyclerViewActivity.class);
            startActivity(RecyclerRecyclerViewiewIntent);
        });
    }
}
