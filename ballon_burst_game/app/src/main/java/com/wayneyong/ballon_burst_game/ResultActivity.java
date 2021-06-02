package com.wayneyong.ballon_burst_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewInfo, textViewMyScore, textViewHighestScore;
    private Button buttonPlayAgain, buttonQuitGame;
    int myScore;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewInfo = findViewById(R.id.textViewInfo);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonQuitGame = findViewById(R.id.buttonQuitGame);

        myScore = getIntent().getIntExtra("score", 0);
        textViewMyScore.setText("Your Score: " + myScore);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0);

        //check to see the score is highest score
        if (myScore >= highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore).apply();
            textViewHighestScore.setText("Highest Score: " + myScore);
            textViewInfo.setText("Congratufuckinglation. The new high score. Do you want to get better score?");

        } else {
            textViewHighestScore.setText("Highest Score: " + highestScore);
            if ((highestScore - myScore) > 10) {
                textViewInfo.setText("You need to fucking speed up!");
            }
            if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10) {
                textViewInfo.setText("Good. How about a little faster?");
            }
            if ((highestScore - myScore) < 3) {
                textViewInfo.setText("Goddamnit, this is you have got for this far?!");
            }
        }

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }
}