package com.example.profile;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MathActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Random random = new Random();

    int num1, num2, result;
    String operator;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_activity);

        textView = findViewById(R.id.text_result);

        num1 = getIntent().getIntExtra("num1", 0);
        operator = getIntent().getStringExtra("operator");

        num2 = random.nextInt(100) + 1;

        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = (num2 != 0) ? num1 / num2 : 0; break;
        }

        textView.setText("Calculating...");

        handler.postDelayed(() -> {
            String display = num1 + " " + operator + " " + num2 + " = " + result;
            textView.setText(display);
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
