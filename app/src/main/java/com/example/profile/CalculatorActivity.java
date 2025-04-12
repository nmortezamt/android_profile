package com.example.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity
{
    EditText num1, num2;
    TextView result;
    Button addBtn, subtractBtn, multiplyBtn, divideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        num1 = findViewById(R.id.editTextNumber1);
        num2 = findViewById(R.id.editTextNumber2);
        result = findViewById(R.id.resultText);

        addBtn = findViewById(R.id.btnAdd);
        subtractBtn = findViewById(R.id.btnSubtract);
        multiplyBtn = findViewById(R.id.btnMultiply);
        divideBtn = findViewById(R.id.btnDivide);

        addBtn.setOnClickListener(view -> calculate('+'));
        subtractBtn.setOnClickListener(view -> calculate('-'));
        multiplyBtn.setOnClickListener(view -> calculate('*'));
        divideBtn.setOnClickListener(view -> calculate('/'));
    }

    private void calculate(char operator) {
        String input1 = num1.getText().toString();
        String input2 = num2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double number1 = Double.parseDouble(input1);
        double number2 = Double.parseDouble(input2);
        double res = 0;

        switch (operator) {
            case '+': res = number1 + number2; break;
            case '-': res = number1 - number2; break;
            case '*': res = number1 * number2; break;
            case '/':
                if (number2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                res = number1 / number2; break;
        }

        result.setText("Result: " + res);
    }
}
