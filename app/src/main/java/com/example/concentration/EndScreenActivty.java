package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EndScreenActivty extends AppCompatActivity {

    public static int points;
    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen_activty);

        tvPoints = findViewById(R.id.tvPoints);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            points = extras.getInt("points");
        }

        tvPoints.setText(String.valueOf(points));

    }
}