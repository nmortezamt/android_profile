package com.example.profile.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.profile.R;

public class Exam4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_4);
        Button btnExam1 = findViewById(R.id.act1);
        Button btnExam2 = findViewById(R.id.act2);
        Button btnExam3 = findViewById(R.id.act3);
        Button home = findViewById(R.id.back);

        btnExam1.setOnClickListener(v -> openExamActivity(Exam1Activity.class, "Exam4Activity -> Exam1Activity"));
        btnExam2.setOnClickListener(v -> openExamActivity(Exam2Activity.class, "Exam4Activity -> Exam2Activity"));
        btnExam3.setOnClickListener(v -> openExamActivity(Exam3Activity.class, "Exam4Activity -> Exam3Activity"));
        home.setOnClickListener(v -> openExamActivity(MainActivity.class, "MainActivity"));
    }
    private void openExamActivity(Class<?> activityClass, String path) {
        SharedPreferences preferences = getSharedPreferences("UserPathPrefs", MODE_PRIVATE);
        String pathact = preferences.getString("path", "");
        pathact += " → " + path;
        preferences.edit().putString("path", pathact).apply();
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        finish();
    }
}