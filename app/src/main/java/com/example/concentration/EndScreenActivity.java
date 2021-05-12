package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  EndScreenActivity.java
 *  Purpose:            To display the finalized score, the number of cards the user played and user's name after accepting the user's name from the dialogue box.
 *                      If the score beats the current top 3 high scores, saves the new score and user's name into the external file scores.json to keep track of the
 *                      top scores.
 */
// This is currently not working and crashes when prompted
public class EndScreenActivity extends AppCompatActivity {

    public static int points, gameSize;
    public static String user;
    TextView tvPoints, tvUsername;
    Button btnHomeHSE;
    public int newScore = 0;
    public int index;
    private File file;
    boolean save = true;

    //int userPoints[] ={'0', '0', '0'};
    //String userNames[] = {"ABC", "ABC", "ABC"};

    ArrayList<Integer> userPoints = new ArrayList<>();
    ArrayList<String> userNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen_activty);

        tvPoints = findViewById(R.id.tvPoints);
        tvUsername = findViewById(R.id.tvUsername);
        btnHomeHSE = findViewById(R.id.btnHomeHSE);

        btnHomeHSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHomeActivity();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            points = extras.getInt("points");
            user = extras.getString("user");
            gameSize = extras.getInt("num");
        }

        tvPoints.setText(String.valueOf(points));
        tvUsername.setText(String.valueOf(user));


        userPoints.add(points);
        userNames.add(user);

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

       /* case 4:
        file = new File(getFilesDir(),"scores4.txt");
        break;
        case 6:
        file = new File(getFilesDir(),"scores6.txt");
        break;
        case 8:
        file = new File(getFilesDir(),"scores8.txt");
        break;
        case 10:
        file = new File(getFilesDir(),"scores10.txt");
        break;
        case 12:
        file = new File(getFilesDir(),"scores12.txt");
        break;
        case 14:
        file = new File(getFilesDir(),"scores14.txt");
        break;
        case 16:
        file = new File(getFilesDir(),"scores16.txt");
        break;
        case 18:
        file = new File(getFilesDir(),"scores18.txt");
        break;
        case 20:
        file = new File(getFilesDir(),"scores20.txt");
        break;
        default:
        break;*/

        /*try {
            Scanner scan = new Scanner(file);
            for(int i = 0; i < userPoints.size()-1; i++){
                userPoints.add(scan.nextInt());
                System.out.println(userPoints.get(i));
            }
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        String separate = ", ";
        String space = "\n";

        /*for(int i = 0; i < userPoints.size(); i++){
            if(points > userPoints.get(i)){
                index = i;
                newScore = points;
                break;
            }
        }



        userNames.add(index, user);
        userPoints.add(index, newScore);
        System.out.println(points);*/

        FileOutputStream fos = null;
       /* try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        try {

            //fos = new FileOutputStream(file);
            fos = openFileOutput(String.valueOf(file), MODE_APPEND);
//            fos.write(userNames.get(0).getBytes());
//            fos.write(separate.getBytes());
//            fos.write(String.valueOf(userPoints.get(0)).getBytes());
//            fos.write(space.getBytes());
//            fos.write(userNames.get(1).getBytes());
//            fos.write(separate.getBytes());
//            fos.write(String.valueOf(userPoints.get(1)).getBytes());
//            fos.write(space.getBytes());
//            fos.write(userNames.get(2).getBytes());
//            fos.write(separate.getBytes());
//            fos.write(String.valueOf(userPoints.get(2)).getBytes());
//            fos.write(space.getBytes());
            //fos.write(defaultScores.getBytes());
            fos.write(user.getBytes());
            fos.write(separate.getBytes());
            fos.write(String.valueOf(points).getBytes());
            fos.write(space.getBytes());
            //fos.write(separate.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void goHomeActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}