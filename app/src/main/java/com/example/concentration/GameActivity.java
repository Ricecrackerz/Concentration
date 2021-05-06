package com.example.concentration;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GameActivity extends AppCompatActivity {

    public static int clicks = 0, counter = 0;
    public static int firstClicked = -1, lastClicked = -1;
    public static int firstImage = 0, secondImage = 0;
    public static int points = 0;
    public static int gameSize;
    public static ImageButton buttons[] = new ImageButton[20];
    public static int flags[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static int selectedCards[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static ArrayList<Integer> images = new ArrayList<>();
    public static TextView tvScore;
    public static boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            gameSize = extras.getInt("num");
        }


        images.add(R.drawable.bird);
        images.add(R.drawable.bird);
        images.add(R.drawable.cat);
        images.add(R.drawable.cat);
        images.add(R.drawable.dog);
        images.add(R.drawable.dog);
        images.add(R.drawable.goat);
        images.add(R.drawable.goat);
        images.add(R.drawable.hippo);
        images.add(R.drawable.hippo);
        images.add(R.drawable.koala);
        images.add(R.drawable.koala);
        images.add(R.drawable.lion);
        images.add(R.drawable.lion);
        images.add(R.drawable.panda);
        images.add(R.drawable.panda);
        images.add(R.drawable.platypus);
        images.add(R.drawable.platypus);
        images.add(R.drawable.rat);
        images.add(R.drawable.rat);

        if (!check){
            //Collections.shuffle(images);
            Collections.shuffle(images.subList(0, gameSize - 1 ));
            check = true;
        }

        Button btnTryAgain, btnNewGame, btnEndGame;
        TextView tv;

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnEndGame = (Button) findViewById(R.id.btnEndGame);
        tv = (TextView) findViewById(R.id.tv);
        tvScore = (TextView) findViewById(R.id.tvScore);

        int cardBack = R.drawable.cardback;

        int [] idButton = {R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8, R.id.btn9,
                R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,
                R.id.btn19,R.id.btn20};

        for(int i = 0; i < idButton.length; i++){
            if(i > gameSize - 1){
                buttons[i] = (ImageButton) findViewById(idButton[i]);
                buttons[i].setVisibility(View.INVISIBLE);
            }

        }

        //Collections.shuffle(Collections.singletonList(images.get(0) - images.get(game - 1)));


        for(int i = 0; i < idButton.length; i++){

            buttons[i] = (ImageButton) findViewById(idButton[i]);
            buttons[i].setImageResource(cardBack);
            int finalI = i;

            if (buttons[i].getVisibility() != View.INVISIBLE){
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(buttons[finalI].isClickable() == true && clicks < 2) {
                            buttons[finalI].setImageResource(images.get(finalI));
                            buttons[finalI].setClickable(false);

                            if (clicks == 0) {
                                firstImage = images.get(finalI);
                                firstClicked = finalI;
                                selectedCards[firstClicked] = 1;
                            } else if (clicks == 1) {
                                secondImage = images.get(finalI);
                                lastClicked = finalI;
                                selectedCards[lastClicked] = 1;
                            }
                            clicks++;
                        }
                        if (clicks == 2) {
                            if (firstImage != secondImage){
                                System.out.println(":(");
                                buttons[firstClicked].setClickable(false);
                                buttons[lastClicked].setClickable(false);
                                selectedCards[lastClicked] = 1;
                                selectedCards[firstClicked] = 1;
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
                                flags[lastClicked] = 1;
                                flags[firstClicked] = 1;
                                clicks = 0;
                                firstImage = 0;
                                secondImage = 0;
                                points += 2;
                                counter +=2;
                                if(counter == gameSize){
                                    gotoEndScreen();
                                }
                                tvScore.setText(String.valueOf(points));
                            }
                        }
                    }
                });

                btnTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clicks == 2){
                            buttons[firstClicked].setImageResource(cardBack);
                            buttons[lastClicked].setImageResource(cardBack);
                            buttons[firstClicked].setClickable(true);
                            buttons[lastClicked].setClickable(true);
                            selectedCards[lastClicked] = 0;
                            selectedCards[firstClicked] = 0;
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
                    buttons[i].setImageResource(cardBack);
                }
                gotoEndScreen();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goGameActivity();
            }

        });


        if(savedInstanceState != null){
            savedInstanceState.getInt("points");
            savedInstanceState.getInt("counter");
            tvScore.setText(String.valueOf(points));
            buttons = (ImageButton[]) savedInstanceState.getSerializable("buttons");
            images = savedInstanceState.getIntegerArrayList("images");
            flags = savedInstanceState.getIntArray("flags");
            check = savedInstanceState.getBoolean("check");

            for(int i = 0; i < buttons.length; i++){
                if (flags[i] == 1){
                    System.out.println(i);
                    buttons[i].setImageResource(images.get(i));
                    buttons[i].setClickable(false);
                }
                if (selectedCards[i] == 1){
                    buttons[i].setImageResource(images.get(i));
                }

            }

        }
    }

    private void gotoEndScreen() {
        Intent i = new Intent(this, EndScreenActivty.class);
        System.out.println(Arrays.toString(flags));
        startActivity(i);
        finish();
    }

    private void goGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt("points", points);
        outState.putInt("counter", counter);
        outState.putSerializable("buttons", buttons);
        outState.putIntegerArrayList("images", images);
        outState.putIntArray("flags", flags);
        outState.putBoolean("check", check);

        super.onSaveInstanceState(outState);
        //outState.putSerializable("images", images);
    }



}