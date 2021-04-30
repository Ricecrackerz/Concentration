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
    public static int points = 0;
    public static int gameSize;

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
            gameSize = extras.getInt("num");
        }

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

        int cardBack = R.drawable.cardback;

        int [] idButton = {R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8, R.id.btn9,
                R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,
                R.id.btn19,R.id.btn20};

        Button buttons[] = new Button[idButton.length];

        for(int i = 0; i < idButton.length; i++){
            if(i > gameSize - 1){
                buttons[i] = (Button) findViewById(idButton[i]);
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }

        //Collections.shuffle(Collections.singletonList(images.get(0) - images.get(game - 1)));
        Collections.shuffle(images.subList(0, gameSize - 1 ));

        for(int i = 0; i < idButton.length; i++){

            buttons[i] = (Button) findViewById(idButton[i]);
            buttons[i].setCompoundDrawablesWithIntrinsicBounds(cardBack, 0, 0, 0);
            int finalI = i;
            if (buttons[i].getVisibility() != View.INVISIBLE){
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(buttons[finalI].isClickable() == true && clicks < 2) {
                            buttons[finalI].setCompoundDrawablesWithIntrinsicBounds(images.get(finalI), 0, 0, 0);
                            buttons[finalI].setClickable(false);

                            if (clicks == 0) {
                                firstImage = images.get(finalI);
                                firstClicked = finalI;
                            } else if (clicks == 1) {
                                secondImage = images.get(finalI);
                                lastClicked = finalI;
                            }
                            clicks++;
                        }
                        if (clicks == 2) {
                            if (firstImage != secondImage){
                                System.out.println(":(");
                                buttons[firstClicked].setClickable(false);
                                buttons[lastClicked].setClickable(false);
                                if (points == 0){
                                    tvScore.setText("0");
                                }
                                else {
                                    points -= 1;
                                    tvScore.setText(String.valueOf(points));
                                }

                            }
                            else if (firstImage == secondImage){
                                System.out.println("lets go");
                                buttons[firstClicked].setClickable(false);
                                buttons[lastClicked].setClickable(false);
                                clicks = 0;
                                firstImage = 0;
                                secondImage = 0;
                                points += 2;
                                tvScore.setText(String.valueOf(points));
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
            else{
                System.out.println("hi");
            }

        }

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< idButton.length; i++){
                    buttons[i].setCompoundDrawablesWithIntrinsicBounds(images.get(i), 0, 0, 0);
                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goGameActivity();
            }
        });
    }

    private void goGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();
    }

}