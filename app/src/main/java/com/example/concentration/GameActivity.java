package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.concentration.R.id.btn1;
import static com.example.concentration.R.id.text;

public class GameActivity extends AppCompatActivity {

    Button btnTryAgain;
    public static int clicks = 0;
    public static int lastClicked = -1, lastClicked1 = -1;

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

        // If we want to attempt images
//        ArrayList<Image> image = new ArrayList<>();
//        Button button = (Button) findViewById(R.id.button);
//        Image tony = getImage(getDocumentBase(), );
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.setBackgroundResource(animals[0]);
//            }
//        });

        int [] idArray = {R.id.btn1, R.id.btn2, R.id.btn3};
        int [] idTextView = {R.id.tv1, R.id.tv2, R.id.tv3};


        TextView textView[] = new TextView[idTextView.length];
        Button buttons[] = new Button[idArray.length];
        Collections.shuffle(wordList);

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);

        for(int i = 0; i < idArray.length; i++){

            buttons[i] = (Button) findViewById(idArray[i]);
            textView[i] = (TextView) findViewById(idTextView[i]);
            buttons[i].setText("cardBack");
            int finalI = i;


            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buttons[finalI].getText() == "cardBack" && clicks < 2)
                    {
                        buttons[finalI].setVisibility(View.GONE);
                        textView[finalI].setText(wordList.get(finalI));
                        buttons[finalI].setText("ok");
                        if(clicks == 0){
                            lastClicked = finalI;
                        }
                        else if(clicks == 1){
                            lastClicked1 = finalI;
                        }
                        clicks++;
                    }
                }
            });

            // Need to fix this
            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clicks == 2){
                        buttons[lastClicked].setVisibility(View.VISIBLE);
                        buttons[lastClicked1].setVisibility(View.VISIBLE);
                        buttons[lastClicked].setText("cardBack");
                        buttons[lastClicked1].setText("cardBack");
                        clicks = 0;
                    }
                }
            });

    }
}}