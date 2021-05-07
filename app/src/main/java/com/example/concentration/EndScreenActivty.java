package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class EndScreenActivty extends AppCompatActivity {

    public static int points, gameSize;
    public static String user;
    TextView tvPoints, tvUsername;

    private Context mContext;
    private String mFilename;

    public EndScreenActivty (Context c, String f){
        mContext = c;
        mFilename = f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen_activty);

        tvPoints = findViewById(R.id.tvPoints);
        tvUsername = findViewById(R.id.tvUsername);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            points = extras.getInt("points");
            user = extras.getString("user");
            gameSize = extras.getInt("num");
        }

        tvPoints.setText(String.valueOf(points));
        tvUsername.setText(String.valueOf(user));
        System.out.println(gameSize);

        JSONObject highScore = new JSONObject();
        try {
            highScore.put("id", gameSize);
            highScore.put("user", user);
            highScore.put("points", points);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();

        jsonArray.put(highScore);
        JSONObject score = new JSONObject();
        try {
            score.put("score", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //String json = score.toString();

        //System.out.println(json);

        try(FileWriter file = new FileWriter("scores.json")){
            file.write(score.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}