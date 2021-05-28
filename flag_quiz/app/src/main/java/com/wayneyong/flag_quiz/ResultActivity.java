package com.wayneyong.flag_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewTotalCorrect, textViewTotalWrong, textViewTotalEmpty, textViewSuccess;
    private Button buttonAgain, buttonQuit;

    int totalCorrect, totalWrong, totalEmpty, successRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewTotalCorrect = findViewById(R.id.textViewTotalCorrect);
        textViewTotalWrong = findViewById(R.id.textViewTotalWrong);
        textViewTotalEmpty = findViewById(R.id.textViewTotalEmpty);
        textViewSuccess = findViewById(R.id.textViewTotalSuccess);
        buttonAgain = findViewById(R.id.buttonAgain);
        buttonQuit = findViewById(R.id.buttonQuit);

        totalCorrect = getIntent().getIntExtra("correct", 0);
        totalWrong = getIntent().getIntExtra("wrong", 0);
        totalEmpty = getIntent().getIntExtra("empty", 0);
        successRate = ((totalCorrect * 10));


        textViewTotalCorrect.setText("Total Correct Answer: " + totalCorrect);
        textViewTotalWrong.setText("Total Wrong Answer: " + totalWrong);
        textViewTotalEmpty.setText("Total Empty Answer:  " + totalEmpty);
        textViewSuccess.setText("Success Rate: " + successRate + "%");


        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newIntent);
                finish();

            }
        });


    }
}