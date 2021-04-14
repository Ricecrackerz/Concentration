package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGame;
    private Button btnMusic;
    private Button btnHs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGame = findViewById(R.id.btnGame);
        btnMusic = findViewById(R.id.btnMusic);
        btnHs = findViewById(R.id.btnHs);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goGameActivity();
            }
        });

        btnHs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHighScoreActivity();
            }
        });

    }
    private void goGameActivity(){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();
    }

    private void goHighScoreActivity(){
        Intent i = new Intent(this, HighScoreActivity.class);
        startActivity(i);
        finish();
    }
}