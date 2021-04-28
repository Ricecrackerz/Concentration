package com.example.concentration;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;


public class GameActivity extends AppCompatActivity {

    public static int clicks = 0;
    public static int firstClicked = -1, lastClicked = -1;
    public static int firstImage = 0, secondImage = 0;
    public static int score = 0;
    public static int game;

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

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            game = extras.getInt("num");
        }
        System.out.println(game);

        ArrayList<Integer> images = new ArrayList<>();

        Button btnTryAgain, btnNewGame, btnEndGame;
        TextView tv, tvScore;

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnEndGame = (Button) findViewById(R.id.btnEndGame);
        tv = (TextView) findViewById(R.id.tv);
        tvScore = (TextView) findViewById(R.id.tvScore);

        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tony);
        images.add(R.drawable.tony);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tony);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tinykirby);
        images.add(R.drawable.tony);
        images.add(R.drawable.tony);

        int cardBack = R.drawable.cardback;

        int [] idButton = {R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8, R.id.btn9,
                R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,
                R.id.btn19,R.id.btn20};

        Button buttons[] = new Button[];

        for(int i = 0; i < 20 - game; i++){
            buttons[i] = (Button) findViewById(idButton[i]);
            buttons[i].setVisibility(View.GONE);
        }

        Collections.shuffle(Collections.singletonList(images.get(0) - images.get(game - 1)));

        for(int i = 0; i < buttons.length-1; i++){

            buttons[i] = (Button) findViewById(idButton[i]);
            buttons[i].setCompoundDrawablesWithIntrinsicBounds(cardBack, 0, 0, 0);
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttons[finalI].isClickable() == true && clicks < 2) {
                        buttons[finalI].setCompoundDrawablesWithIntrinsicBounds(images.get(finalI), 0, 0, 0);
                        buttons[finalI].setClickable(false);

                        if (clicks == 0) {
                            firstImage = images.get(finalI);
                            firstClicked = finalI;
                            System.out.println(firstImage);
                        } else if (clicks == 1) {
                            secondImage = images.get(finalI);
                            lastClicked = finalI;
                            System.out.println(secondImage);
                        }
                        clicks++;
                    }
                    if (clicks == 2) {
                         if (firstImage != secondImage){
                             System.out.println(":(");
                             buttons[firstClicked].setClickable(false);
                             buttons[lastClicked].setClickable(false);
                             if (score == 0){
                                tvScore.setText("0");
                            }
                            else {
                                score -= 1;
                                tvScore.setText(String.valueOf(score));
                            }

                        }
                        else if (firstImage == secondImage){
                            System.out.println("lets");
                            buttons[firstClicked].setClickable(false);
                            buttons[lastClicked].setClickable(false);
                            clicks = 0;
                            firstImage = 0;
                            secondImage = 0;
                            score += 2;
                            tvScore.setText(String.valueOf(score));
                        }
                    }
                }
            });

            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clicks == 2){
                        buttons[firstClicked].setCompoundDrawablesWithIntrinsicBounds(cardBack, 0, 0, 0);
                        buttons[lastClicked].setCompoundDrawablesWithIntrinsicBounds(cardBack, 0, 0, 0);
                        buttons[firstClicked].setClickable(true);
                        buttons[lastClicked].setClickable(true);
                        clicks = 0;
                    }
                }
            });
        }

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< idButton.length; i++){
                    buttons[i].setCompoundDrawablesWithIntrinsicBounds(images.get(i), 0, 0, 0);
                }
            }
        });


    }

}