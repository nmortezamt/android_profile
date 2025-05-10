package com.example.profile;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class OperatorActivity extends AppCompatActivity {
    int randomNum;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_activity);

        randomNum = random.nextInt(100) + 1;

        Button addBtn = findViewById(R.id.btn_add);
        Button subBtn = findViewById(R.id.btn_subtract);
        Button mulBtn = findViewById(R.id.btn_multiply);
        Button divBtn = findViewById(R.id.btn_divide);

        View.OnClickListener operatorClickListener = v -> {
            String operator = "";

            if (v.getId() == R.id.btn_add) {
                operator = "+";
            } else if (v.getId() == R.id.btn_subtract) {
                operator = "-";
            } else if (v.getId() == R.id.btn_multiply) {
                operator = "*";
            } else if (v.getId() == R.id.btn_divide) {
                operator = "/";
            }

            Intent intent = new Intent(OperatorActivity.this, MathActivity.class);
            intent.putExtra("num1", randomNum);
            intent.putExtra("operator", operator);
            startActivity(intent);
        };

        addBtn.setOnClickListener(operatorClickListener);
        subBtn.setOnClickListener(operatorClickListener);
        mulBtn.setOnClickListener(operatorClickListener);
        divBtn.setOnClickListener(operatorClickListener);
    }
}
