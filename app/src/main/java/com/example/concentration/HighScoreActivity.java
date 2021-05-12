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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  HighScoreActivity.java
 *  Purpose:            To display the current top 3 high scores along with the player's name for specific game size (amount of cards per game) 
 */
public class HighScoreActivity extends AppCompatActivity {

    File file;
    public static int gameSize;
    TextView textView;
    Button btnHomeHS;

    ArrayList<Integer> userPoints = new ArrayList<>();
    ArrayList<Byte> userNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        gotoDialogBox();

        btnHomeHS = (Button) findViewById(R.id.btnHomeHS);

        btnHomeHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHomeActivity();
            }
        });
    }

    private void goHomeActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    private void gotoDialogBox() {
        final EditText etNumber = new EditText(HighScoreActivity.this);

        AlertDialog alertDialog = new AlertDialog.Builder(HighScoreActivity.this).create();
        alertDialog.setTitle("Choose an even number between 4 and 20");
        alertDialog.setView(etNumber);
        etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

        textView = findViewById(R.id.textView);

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
                showText(gameSize);
            }
            else {
                Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(this, "OUT OF RANGE AND TRY AGAIN", Toast.LENGTH_SHORT).show();
        }
    }

    private void showText(int gameSize) {
        switch(gameSize){
            case 4:
                file = new File("scores4.txt");
                break;
            case 6:
                file = new File("scores6.txt");
                break;
            case 8:
                file = new File("scores8.txt");
                break;
            case 10:
                file = new File("scores10.txt");
                break;
            case 12:
                file = new File("scores12.txt");
                break;
            case 14:
                file = new File("scores14.txt");
                break;
            case 16:
                file = new File("scores16.txt");
                break;
            case 18:
                file = new File("scores18.txt");
                break;
            case 20:
                file = new File("scores20.txt");
                break;
            default:
                break;
        }

        /*try {
            Scanner scanner = new Scanner(file);
            Scanner scanner1 = new Scanner(file);
            for(int i = 0; i  < 4; i++){
                userNames.add(scanner.nextByte());
                System.out.println(userNames.get(i));
            }
            scanner.close();
            for(int i = 0; i  < file.length(); i++){
                userPoints.add(scanner.nextInt());
                System.out.println(userPoints.get(i));
            }
            scanner1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        FileInputStream fis = null;

        /*try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            score = new String(bytes);
            kb = new Scanner(score);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++ ) {
            highScores[i] = kb.nextLine();
            System.out.println(highScores[i]);
        }*/
        try {
            /*if(file.length() == 0 || !file.exists()){
                fos = new FileOutputStream(file);
                fos.write("ABC...5\nABC...4\nABC...3\nABC...2\nABC...1".getBytes());
            }*/
            fis = this.openFileInput(String.valueOf(file));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
        while ((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }
        textView.setText(sb.toString());
        //System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fis!=null){
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}