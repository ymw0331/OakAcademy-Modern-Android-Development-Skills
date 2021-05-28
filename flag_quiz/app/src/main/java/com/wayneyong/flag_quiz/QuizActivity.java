package com.wayneyong.flag_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewCorrect, textViewWrong, textViewEmpty, textViewQuestion;
    private ImageView imageViewFlag, imageViewNext;
    private Button buttonA, buttonB, buttonC, buttonD;

    private FlagsDatabase fdatabase;
    private ArrayList<FlagsModel> questionsList;

    int correct = 0;
    int wrong = 0;
    int empty = 0;
    int question = 0;

    //create an object from model class
    private FlagsModel correctFlag;
    private ArrayList<FlagsModel> wrongOptionsList;

    HashSet<FlagsModel> mixOptions = new HashSet<>();
    ArrayList<FlagsModel> options = new ArrayList<>();

    boolean buttonControl = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewWrong = findViewById(R.id.textViewWrong);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        textViewQuestion = findViewById(R.id.textViewQuestion);

        imageViewFlag = findViewById(R.id.imageViewFlag);
        imageViewNext = findViewById(R.id.imageViewNext);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        fdatabase = new FlagsDatabase(QuizActivity.this);
        questionsList = new FlagsDAO().getRandomTenQuestion(fdatabase);


        //load the questions
        loadQuestions();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerControl(buttonA);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerControl(buttonB);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerControl(buttonC);
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerControl(buttonD);
            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question++;
                //no opitons selected by user, no of question is less than 10
                if (!buttonControl && question < 10) {
                    empty++;
                    textViewEmpty.setText("Empty : " + empty);
                    loadQuestions();
                } else if (buttonControl && question < 10) {

                    loadQuestions();

                    //return the button original color and set to clickable
                    buttonA.setClickable(true);
                    buttonB.setClickable(true);
                    buttonC.setClickable(true);
                    buttonD.setClickable(true);

                    buttonA.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
                    buttonB.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
                    buttonC.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
                    buttonD.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
                }
                //user reaches last question, pass everything to result
                else if (question == 10) {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correct); //send the no of correct and worng to result
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("empty", empty);
                    startActivity(intent);
                    finish();
                }
                buttonControl = false;
            }
        });
    }

    public void loadQuestions() {
        //questionList is the arrayList of FlagsModel
        //question[0] as in array[0] is made as correct flag
        textViewQuestion.setText("Question: " + (question + 1));
        correctFlag = questionsList.get(question);//first question is 0


        //load flag image form db to imageView
        //flag name in db will be compared to name in drawable folder
        //image name in db must be same as name in drawable folder
        imageViewFlag.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(), "drawable", getPackageName()));
        //first flag will loaded from imageView flag

        wrongOptionsList = new FlagsDAO().getRandomThreeOptions(fdatabase, correctFlag.getFlag_id());

        //Hashset, place things randomly
        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionsList.get(0));
        mixOptions.add(wrongOptionsList.get(1));
        mixOptions.add(wrongOptionsList.get(2));

        //Cannot print in hashset by index number, create an arrayList
        options.clear();
        //transfered to options array in mixed form
        for (FlagsModel flg : mixOptions) {
            options.add(flg);
        }

        buttonA.setText(options.get(0).getFlag_name());
        buttonB.setText(options.get(1).getFlag_name());
        buttonC.setText(options.get(2).getFlag_name());
        buttonD.setText(options.get(3).getFlag_name());

    }

    public void answerControl(Button button) {

        String buttonText = button.getText().toString();
        String correctAnswer = correctFlag.getFlag_name();

        if (buttonText.equals(correctAnswer)) {
            correct++;
            button.setBackgroundColor(Color.GREEN);
        } else {
            wrong++;
            button.setBackgroundColor(Color.RED);

            if (buttonA.getText().toString().equals(correctAnswer)) {
                buttonA.setBackgroundColor(Color.GREEN);
            }
            if (buttonB.getText().toString().equals(correctAnswer)) {
                buttonB.setBackgroundColor(Color.GREEN);
            }
            if (buttonC.getText().toString().equals(correctAnswer)) {
                buttonC.setBackgroundColor(Color.GREEN);
            }
            if (buttonD.getText().toString().equals(correctAnswer)) {
                buttonD.setBackgroundColor(Color.GREEN);
            }
        }
        buttonA.setClickable(false); //prevent user from choosing another option
        buttonB.setClickable(false);
        buttonC.setClickable(false);
        buttonD.setClickable(false);

        textViewCorrect.setText("Correct: " + correct);
        textViewWrong.setText("Wrong: " + wrong);

        buttonControl = true;

    }

}