package com.example.colorhuntgame;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView colorText;
    private TextView scoreText;
    private TextView timerText;
    private Button startButton;

    private int score = 0;
    private int timeLeft = 30;
    private boolean isGameActive = false;

    private Handler handler = new Handler();
    private Runnable updateTimerRunnable;
    
    private final String[] colors = {"Red", "Green", "Blue", "Yellow", "Cyan", "Magenta", "White", "Black"};
    private final int[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.WHITE, Color.BLACK};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorText = findViewById(R.id.colorText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGameActive) {
                    startGame();
                }
            }
        });

        colorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGameActive) {
                    checkColor(((TextView) v).getText().toString());
                }
            }
        });
    }

    private void startGame() {
        score = 0;
        timeLeft = 30;
        isGameActive = true;
        scoreText.setText("Score: " + score);
        timerText.setText("Time: " + timeLeft + "s");
        
        // Start timer
        updateTimerRunnable = new Runnable() {
            @Override
            public void run() {
                timeLeft--;
                timerText.setText("Time: " + timeLeft + "s");
                if (timeLeft > 0) {
                    handler.postDelayed(this, 1000);
                } else {
                    endGame();
                }
            }
        };
        handler.postDelayed(updateTimerRunnable, 1000);
        
        // Start color updates
        updateColor();
    }

    private void updateColor() {
        if (isGameActive) {
            Random random = new Random();
            int colorIndex = random.nextInt(colors.length);
            colorText.setText(colors[colorIndex]);
            colorText.setTextColor(colorValues[colorIndex]);
            
            // Change color after a short delay
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isGameActive) {
                        updateColor();
                    }
                }
            }, 1000);
        }
    }

    private void checkColor(String color) {
        String currentColor = (String) colorText.getText();
        if (color.equals(currentColor)) {
            score++;
        } else {
            score--;
        }
        scoreText.setText("Score: " + score);
    }

    private void endGame() {
        isGameActive = false;
        handler.removeCallbacks(updateTimerRunnable);
        // Show final score or any end-of-game message
    }
}
