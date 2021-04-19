package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.concentration.R.id.btn1;

public class GameActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ArrayList<String> wordList = new ArrayList<>();

        wordList.add("PANDA");
        wordList.add("LION");
        wordList.add("BIRD");
        wordList.add("RAT");
        wordList.add("KOALA");
        wordList.add("CAT");
        wordList.add("DOG");
        wordList.add("HIPPO");
        wordList.add("PLATYPUS");
        wordList.add("GOAT");

        Button buttons[] = {btn1, btn2, btn3, btn4};

        Collections.shuffle(wordList);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);

        for(int i = 0; i <= 4; i++){
            buttons[i].setText("cardBack");
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttons[i].text == "cardBack")
                    {
                        buttons[i].setText(wordList[i]);
                    }
                }
            });

    }
}}