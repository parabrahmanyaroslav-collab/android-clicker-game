package com.example.clickergame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private int score = 0;
    private int clickValue = 1;
    private TextView scoreText;
    private TextView clickValueText;
    private Button clickButton;
    private Button upgradeButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Инициализация UI элементов
        scoreText = findViewById(R.id.scoreText);
        clickValueText = findViewById(R.id.clickValueText);
        clickButton = findViewById(R.id.clickButton);
        upgradeButton = findViewById(R.id.upgradeButton);
        
        updateUI();
        
        // Обработчик нажатия на кнопку клика
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score += clickValue;
                updateUI();
                
                // Анимация нажатия
                v.animate()
                    .scaleX(0.9f)
                    .scaleY(0.9f)
                    .setDuration(100)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            v.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setDuration(100)
                                .start();
                        }
                    })
                    .start();
            }
        });
        
        // Обработчик кнопки улучшения
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int upgradeCost = clickValue * 10;
                if (score >= upgradeCost) {
                    score -= upgradeCost;
                    clickValue++;
                    updateUI();
                }
            }
        });
    }
    
    /**
     * Обновление всех элементов интерфейса
     */
    private void updateUI() {
        scoreText.setText("Очки: " + score);
        clickValueText.setText("Очков за клик: " + clickValue);
        
        int upgradeCost = clickValue * 10;
        upgradeButton.setText("Улучшить (" + upgradeCost + " очков)");
        upgradeButton.setEnabled(score >= upgradeCost);
    }
}