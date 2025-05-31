package com.example.profile.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.profile.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_main);

        TextView pathTextView = findViewById(R.id.pathTextView);
        Button btnExam1 = findViewById(R.id.act1);
        Button btnExam2 = findViewById(R.id.act2);
        Button btnExam3 = findViewById(R.id.act3);
        Button btnExam4 = findViewById(R.id.act4);
        Button btnClearPath = findViewById(R.id.btnClearPath);
        btnClearPath.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("UserPathPrefs", MODE_PRIVATE);
            preferences.edit().remove("path").apply();

            pathTextView.setText("Path has been cleared.");
        });

        btnExam1.setOnClickListener(v -> openExamActivity(Exam1Activity.class, "MainActivity -> Exam1Activity"));
        btnExam2.setOnClickListener(v -> openExamActivity(Exam2Activity.class, "MainActivity -> Exam2Activity"));
        btnExam3.setOnClickListener(v -> openExamActivity(Exam3Activity.class, "MainActivity -> Exam3Activity"));
        btnExam4.setOnClickListener(v -> openExamActivity(Exam4Activity.class, "MainActivity -> Exam4Activity"));
        SharedPreferences preferences = getSharedPreferences("UserPathPrefs", MODE_PRIVATE);
        String path = preferences.getString("path", null);

        if (path != null) {
            pathTextView.setText("You followed this path:\n" + path);
        } else {
            pathTextView.setText("No path followed yet.");
        }
    }
    private void openExamActivity(Class<?> activityClass, String path) {
        SharedPreferences preferences = getSharedPreferences("UserPathPrefs", MODE_PRIVATE);
        String pathact = preferences.getString("path", "Start: ");
        pathact += " → " + path;
        preferences.edit().putString("path", pathact).apply();
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
        finish();
    }
}
