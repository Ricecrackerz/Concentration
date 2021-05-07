package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *  HighScoreActivity.java
 *  Purpose:            To display the current top 3 high scores along with the player's name for specific game size (amount of cards per game) 
 */
public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
    }
}