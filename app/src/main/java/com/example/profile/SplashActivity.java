package com.example.profile;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private View rootView;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA };
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);
        rootView = findViewById(android.R.id.content);

        runnable = new Runnable() {
            @Override
            public void run() {
                int color = colors[random.nextInt(colors.length)];
                rootView.setBackgroundColor(color);
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OperatorActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
