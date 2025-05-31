package com.example.profile.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.profile.R;

public class Exam2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_2);
        Button btnExam1 = findViewById(R.id.act1);
        Button btnExam3 = findViewById(R.id.act3);
        Button btnExam4 = findViewById(R.id.act4);

        btnExam1.setOnClickListener(v -> openExamActivity(Exam1Activity.class, "Exam2Activity -> Exam1Activity"));
        btnExam3.setOnClickListener(v -> openExamActivity(Exam3Activity.class, "Exam2Activity -> Exam3Activity"));
        btnExam4.setOnClickListener(v -> openExamActivity(Exam4Activity.class, "Exam2Activity -> Exam4Activity"));
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
