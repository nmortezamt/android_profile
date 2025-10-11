package com.example.v2.tasks.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.v2.R;


public class UserInfoActivity extends AppCompatActivity {

    EditText etName, etEmail, etAge;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_form);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String age = etAge.getText().toString();

            Intent intent = new Intent(this, DisplayUserInfoActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("age", age);
            startActivity(intent);
        });

    }
}
