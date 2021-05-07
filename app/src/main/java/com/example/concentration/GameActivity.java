package com.example.concentration;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


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
    public static boolean check = false;
    public static String user;
    TextView tvScore;
    Button btnTryAgain, btnNewGame, btnEndGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            gameSize = extras.getInt("num");
        }

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnEndGame = (Button) findViewById(R.id.btnEndGame);
        tvScore = (TextView) findViewById(R.id.tvScore);

        int cardBack = R.drawable.cardback;

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
            Collections.shuffle(images.subList(0, gameSize - 1 ));
            check = true;
        }

        int [] idButton = {R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8, R.id.btn9,
                R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,
                R.id.btn19,R.id.btn20};

        for(int i = 0; i < idButton.length; i++){
            if(i > gameSize - 1){
                buttons[i] = (ImageButton) findViewById(idButton[i]);
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }

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
                                    //gotoEndScreen();
                                    promptUsername();
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
                //promptUsername();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGameActivity();
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
                    buttons[i].setImageResource(images.get(i));
                    buttons[i].setClickable(false);
                }
                if (selectedCards[i] == 1){
                    buttons[i].setImageResource(images.get(i));
                }
            }
        }
    }

    private void promptUsername() {
        final EditText etUsername = new EditText(GameActivity.this);

        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle("Please put in your username");
        alertDialog.setView(etUsername);
       //etUsername.setInputType(InputType.TYPE_CLASS_TEXT);

        //Editable number = etNumber.getText();




        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                user = etUsername.getText().toString();
                System.out.println(user);
                System.out.println(gameSize);
                gotoEndScreen();

                //afterTextChanged(number);
            }
        });

        alertDialog.show();
    }

    private void gotoEndScreen() {
        Intent i = new Intent(this, EndScreenActivty.class);
        i.putExtra("points", points);
        i.putExtra("user", user);
        i.putExtra("num", gameSize);
        startActivity(i);
        finish();
    }

    private void restartGameActivity() {

        final EditText etNumber = new EditText(GameActivity.this);

        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle("Choose an even number between 4 and 20");
        alertDialog.setView(etNumber);
        etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

        Editable number = etNumber.getText();

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                afterTextChanged(number);
            }
        });

        alertDialog.show();

    }

    private void afterTextChanged(Editable number) {
        gameSize = Integer.parseInt(number.toString());
        if (gameSize >= 4 && gameSize <=20){
            if(gameSize%2 == 0){
                counter = 0;
                clicks = 0;
                firstClicked = -1;
                lastClicked = -1;
                firstImage = 0;
                secondImage = 0;
                points = 0;
                counter = 0;
                check = false;
                Collections.sort(images);
                for(int i = 0; i < buttons.length; i++){
                    flags[i] = 0;
                    selectedCards[i] = 0;
                }
                goGameActivity();
            }
            else{
                Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
                restartGameActivity();
            }
        }
        else{
            Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
            restartGameActivity();
        }
    }

    private void goGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("num", gameSize);
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
    }

}