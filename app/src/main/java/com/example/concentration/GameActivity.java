package com.example.concentration;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *  GameActitivity.java
 *  Purpose:            The main logic of the card game is here. The game starts with an amount of cards requested by the player on the screen. After clicking
 *                      the each card, it should reveal an animal and the player's goal is to find the matching animal on the rest of available cards. If a match
 *                      is not found, player can click "Try Again" button to retry. Player can also start a new game or end game to view the result of all cards.
 *                      The view of cards and instances remain unchanged when the device is rotated. The player gets 2pts for every correct pair and -1pt per incorrect
 *                      pair.
 *
 *                      If the player completes the game, prompt a dialogue box to request player's name along with the final score. The next screen should be
 *                      EndScreenActivity.java
 */
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
    public static boolean reset = false;
    TextView tvScore;
    Button btnTryAgain, btnNewGame, btnEndGame, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resetGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            gameSize = extras.getInt("num");
        }

        // To identify the buttons and score to the corresponding widgets on the xml file
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnEndGame = (Button) findViewById(R.id.btnEndGame);
        btnHome = (Button) findViewById(R.id.btnHome);
        tvScore = (TextView) findViewById(R.id.tvScore);

        // To display the cardBack and not reveal the animal
        int cardBack = R.drawable.cardback;

        // Adding all 10 animals through images
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

        // To prevent the game restarting when rotating the device
        if (!check){
            Collections.shuffle(images.subList(0, gameSize - 1 ));
            check = true;
        }

        // Creating an ID list of buttons to allocate correct buttons with proper values
        int [] idButton = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18,
                R.id.btn19, R.id.btn20};
        // To ensure the amount of cards are the same as the number the user requested
        for(int i = 0; i < idButton.length; i++){
            if(i > gameSize - 1){
                buttons[i] = (ImageButton) findViewById(idButton[i]);
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }

        // The logic to check if the match is correct
        for(int i = 0; i < idButton.length; i++) {
            buttons[i] = (ImageButton) findViewById(idButton[i]);
            buttons[i].setImageResource(cardBack);
            int finalI = i;

            if (buttons[i].getVisibility() != View.INVISIBLE) {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // The matching starts when there are 2 cards being clicked
                        if(buttons[finalI].isClickable() == true && clicks < 2) {
                            buttons[finalI].setImageResource(images.get(finalI));
                            buttons[finalI].setClickable(false);

                            // To get data from the first card that the player chooses
                            if (clicks == 0) {
                                firstImage = images.get(finalI);
                                firstClicked = finalI;
                                selectedCards[firstClicked] = 1;
                            } else if (clicks == 1) {
                                // To get data from the second card that the player chooses
                                secondImage = images.get(finalI);
                                lastClicked = finalI;
                                selectedCards[lastClicked] = 1;
                            }
                            clicks++;
                        }

                        // To compare the 2 cards animals and update score
                        if (clicks == 2) {
                            if (firstImage != secondImage) {
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
                                buttons[firstClicked].setClickable(false);
                                buttons[lastClicked].setClickable(false);
                                flags[lastClicked] = 1;
                                flags[firstClicked] = 1;
                                clicks = 0;
                                firstImage = 0;
                                secondImage = 0;
                                points += 2;
                                counter +=2;

                                // Prompt the dialogue box when the player completed the game
                                if(counter == gameSize){
                                    //gotoEndScreen();
                                    promptUsername();
                                }
                                tvScore.setText(String.valueOf(points));
                            }
                        }
                    }
                });

                // Button clicked the retry a new match after choosing an incorrect match
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
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHomeActivity();
                resetGame();
            }
        });

        // Button clicked the reveal all answers
        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< idButton.length; i++){
                    int finalI = i;
                    buttons[i].setImageResource(images.get(finalI));
                }
                //gotoEndScreen();
                //promptUsername();
            }
        });

        // Button clicked to start a new game
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGameActivity();
            }
        });

        // To ensure the game instance stays the same through different orientation
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

    // To allow player's input as their name to save along with their score
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
                gotoEndScreen();

                //afterTextChanged(number);
            }
        });
        alertDialog.show();
    }

    // To take user to End Screen after completing the game
    private void gotoEndScreen() {
        Intent i = new Intent(this, EndScreenActivity.class);
        i.putExtra("points", points);
        i.putExtra("user", user);
        i.putExtra("num", gameSize);
        startActivity(i);
        finish();
    }

    // To dispose the current game, request a new number of cards from the player and create a new game
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

    // To ensure the game is creating an even set of cards from 4 to 20
    private void afterTextChanged(Editable number) {
        gameSize = Integer.parseInt(number.toString());
        if (gameSize >= 4 && gameSize <=20){
            if(gameSize%2 == 0) {
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
            } else {
                Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
                restartGameActivity();
            }
        } else{
            Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
            restartGameActivity();
        }
    }

    // To create the game
    private void goGameActivity() {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("num", gameSize);
        startActivity(i);
        finish();
    }

    private void goHomeActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void resetGame() {
        clicks = 0;
        counter = 0;
        firstClicked = -1;
        lastClicked = -1;
        firstImage = 0;
        secondImage = 0;
        points = 0;
        check = false;
        Arrays.fill(buttons,null);
        Arrays.fill(flags,0);
        Arrays.fill(selectedCards,0);
        gameSize = 0;
        reset = true;
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