package com.example.v2.tasks.userinfo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.v2.R;

public class DisplayUserInfoActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvAge = findViewById(R.id.tvAge);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String age = getIntent().getStringExtra("age");

        tvName.setText("Name: " + name);
        tvEmail.setText("Email: " + email);
        tvAge.setText("Age: " + age);

        tvName.setOnClickListener(v -> saveToSharedPref("name", name));
        tvEmail.setOnClickListener(v -> saveToSharedPref("email", email));
        tvAge.setOnClickListener(v -> saveToSharedPref("age", age));
    }

    private void saveToSharedPref(String key, String value) {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();

        Toast.makeText(this, key + " saved!", Toast.LENGTH_SHORT).show();
    }
}
