package com.example.v2.tasks.guessword;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.v2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuessWordActivity extends AppCompatActivity {

    private static final char[] PERSIAN_LETTERS = {
            'ا','ب','پ','ت','ث','ج','چ','ح','خ','د','ذ','ر','ز',
            'ژ','س','ش','ص','ض','ط','ظ','ع','غ','ف','ق','ک','گ',
            'ل','م','ن','و','ه','ی'
    };

    private static final int GRID_SIZE = 12;
    private static final int MAX_ATTEMPTS = 3;
    private static final long TIME_PER_WORD_MS = 30000; // 30 seconds

    private List<String> wordList = Arrays.asList("سلام", "کتاب", "خانه", "مدرسه", "دوست", "گل");
    private int currentWordIndex = 0;
    private int score = 0;
    private int attemptsLeft = MAX_ATTEMPTS;

    private String correctWord;
    private StringBuilder userAnswer;

    private GridLayout lettersGrid;
    private TextView txtHiddenWord;
    private TextView txtScore;
    private TextView txtAttempts;
    private TextView txtTimer;
    private Button btnHint;

    private final Random random = new Random();
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guess_word);

        initViews();
        startNextWord();
    }

    private void initViews() {
        lettersGrid = findViewById(R.id.lettersGrid);
        txtHiddenWord = findViewById(R.id.txtHiddenWord);
        txtScore = findViewById(R.id.txtScore);
        txtAttempts = findViewById(R.id.txtAttempts);
        txtTimer = findViewById(R.id.txtTimer);
        btnHint = findViewById(R.id.btnHint);

        btnHint.setOnClickListener(v -> useHint());
    }

    private void startNextWord() {
        if (currentWordIndex >= wordList.size()) {
            Toast.makeText(this, "تمام کلمات تمام شد! امتیاز شما: " + score, Toast.LENGTH_LONG).show();
            currentWordIndex = 0;
            score = 0;
        }

        correctWord = wordList.get(currentWordIndex);
        currentWordIndex++;
        userAnswer = new StringBuilder();
        attemptsLeft = MAX_ATTEMPTS;

        updateHiddenWord();
        updateScoreAndAttempts();
        showLetters();
        startTimer();
    }

    private void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();

        countDownTimer = new CountDownTimer(TIME_PER_WORD_MS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText("زمان: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(GuessWordActivity.this, "زمان تمام شد! کلمه: " + correctWord, Toast.LENGTH_SHORT).show();
                startNextWord();
            }
        }.start();
    }

    private void showLetters() {
        lettersGrid.removeAllViews();

        List<Character> letters = new ArrayList<>();

        // Step 1: Add each letter of the word, including duplicates
        for (char c : correctWord.toCharArray()) {
            letters.add(c);
        }

        // Step 2: Add random letters until grid is full, avoid adding duplicates unnecessarily
        while (letters.size() < GRID_SIZE) {
            char randomLetter = PERSIAN_LETTERS[random.nextInt(PERSIAN_LETTERS.length)];
            long count = letters.stream().filter(l -> l == randomLetter).count();
            if (count < 2) letters.add(randomLetter);
        }

        // Step 3: Shuffle the letters
        Collections.shuffle(letters);

        // Step 4: Create buttons
        for (char letter : letters) {
            Button button = createLetterButton(letter);
            lettersGrid.addView(button);
        }
    }


    private Button createLetterButton(char letter) {
        Button btn = new Button(this);
        btn.setText(String.valueOf(letter));

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(8, 8, 8, 8);
        btn.setLayoutParams(params);
        btn.setPadding(0, 16, 0, 16);

        btn.setOnClickListener(v -> {
            v.setEnabled(false);
            onLetterClick(letter);
        });

        return btn;
    }

    private void onLetterClick(char letter) {
        userAnswer.append(letter);
        updateHiddenWord();

        if (userAnswer.length() == correctWord.length()) {
            checkAnswer();
        }
    }

    private void checkAnswer() {
        if (userAnswer.toString().equals(correctWord)) {
            score++;
            updateScoreAndAttempts();
            Toast.makeText(this, "آفرین 🎉", Toast.LENGTH_SHORT).show();
            startNextWord();
        } else {
            attemptsLeft--;
            if (attemptsLeft == 0) {
                Toast.makeText(this, "کلمه درست: " + correctWord, Toast.LENGTH_SHORT).show();
                startNextWord();
            } else {
                Toast.makeText(this, "اشتباه ❌، دوباره سعی کنید", Toast.LENGTH_SHORT).show();
                userAnswer.setLength(0);
                updateHiddenWord();
                showLetters();
            }
        }
        updateScoreAndAttempts();
    }

    private void updateHiddenWord() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < correctWord.length(); i++) {
            if (i < userAnswer.length()) {
                display.append(userAnswer.charAt(i)).append(" ");
            } else {
                display.append("_ ");
            }
        }
        txtHiddenWord.setText(display.toString());
    }

    private void updateScoreAndAttempts() {
        txtScore.setText("امتیاز: " + score);
        txtAttempts.setText("تعداد تلاش: " + attemptsLeft);
    }

    private void useHint() {
        if (userAnswer.length() < correctWord.length()) {
            userAnswer.append(correctWord.charAt(userAnswer.length()));
            updateHiddenWord();
        } else {
            Toast.makeText(this, "کلمه کامل است", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onDestroy() {
        if (countDownTimer != null) countDownTimer.cancel();
        super.onDestroy();
    }
}