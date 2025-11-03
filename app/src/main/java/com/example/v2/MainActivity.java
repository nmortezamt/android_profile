package com.example.v2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.v2.tasks.dialog.DialogActivity;
import com.example.v2.tasks.userinfo.UserInfoActivity;

public class MainActivity extends AppCompatActivity {
    Button btnTaskOne, btnTaskTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnTaskOne = findViewById(R.id.btnTaskOne);
        btnTaskTwo = findViewById(R.id.btnTaskTwo);
        btnTaskOne.setOnClickListener(v ->
                startActivity(new Intent(this, UserInfoActivity.class))
        );
        btnTaskTwo.setOnClickListener(v ->
            startActivity(new Intent(this, DialogActivity.class))
        );

    }
}