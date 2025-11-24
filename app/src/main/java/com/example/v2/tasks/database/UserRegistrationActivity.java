package com.example.v2.tasks.database;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.v2.R;

import java.util.List;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText inputName, inputFamily, inputEmail;
    Button btnAdd, btnShow;
    TextView txtUsers;

    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        inputName = findViewById(R.id.inputName);
        inputFamily = findViewById(R.id.inputFamily);
        inputEmail = findViewById(R.id.inputEmail);

        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        txtUsers = findViewById(R.id.txtUsers);


        db = UserDatabase.getInstance(this);

        btnAdd.setOnClickListener(view -> addUser());
        btnShow.setOnClickListener(view -> showUsers());
    }

    private void addUser() {
        String name = inputName.getText().toString();
        String family = inputFamily.getText().toString();
        String email = inputEmail.getText().toString();

        if (name.isEmpty() || family.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel user = new UserModel(name, family, email);
        db.userDao().insertUser(user);

        Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();
    }

    private void showUsers() {
        List<UserModel> users = db.userDao().getAllUsers();

        StringBuilder sb = new StringBuilder();
        for (UserModel u : users) {
            sb.append(u.id)
                    .append(": ")
                    .append(u.name).append(" ")
                    .append(u.family).append(" - ")
                    .append(u.email).append("\n");
        }

        txtUsers.setText(sb.toString());
    }
}
