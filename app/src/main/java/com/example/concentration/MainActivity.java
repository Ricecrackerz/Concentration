package com.example.concentration;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnGame;
    private Button btnMusic;
    private Button btnHs;
    public static int gameSize;

    private AudioPlayer mediaPlayer = new AudioPlayer();
    private boolean isPlaying = false;

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
               goDialogBox();
            }
        });

        btnHs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHighScoreActivity();
            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    btnMusic.setText("Music ON");
                    mediaPlayer.play(MainActivity.this);
                    isPlaying = true;
                } else {
                    btnMusic.setText("Music OFF");
                    mediaPlayer.stop();
                    isPlaying = false;
                }

            }
        });

    }

    private void goDialogBox() {

        final EditText etNumber = new EditText(MainActivity.this);

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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

    private void afterTextChanged(Editable s) {
        gameSize = Integer.parseInt(s.toString());
        if (gameSize >= 4 && gameSize <=20){
            if(gameSize%2 == 0){
                goGameActivity();
            }
            else {
                Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
        }
    }


    private void goGameActivity(){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("num", gameSize);
        startActivity(i);
        finish();
    }

    private void goHighScoreActivity(){
        Intent i = new Intent(this, HighScoreActivity.class);
        startActivity(i);
        finish();
    }

}