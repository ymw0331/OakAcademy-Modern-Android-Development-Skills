package com.wayneyong.ballon_burst_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTime, textViewCountDown, textViewScore;
    private ImageView ballon1, ballon2, ballon3, ballon4, ballon5, ballon6, ballon7, ballon8, ballon9;
    private GridLayout gridLayout;

    int score = 0;
    Runnable runnable;
    Handler handler;

    //transfer all ballons into an array
    ImageView[] balloonsArray;

    MediaPlayer mediaPlayer;

    boolean soundStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        textViewScore = findViewById(R.id.textViewScore);
        ballon1 = findViewById(R.id.ballon1);
        ballon2 = findViewById(R.id.ballon2);
        ballon3 = findViewById(R.id.ballon3);
        ballon4 = findViewById(R.id.ballon4);
        ballon5 = findViewById(R.id.ballon5);
        ballon6 = findViewById(R.id.ballon6);
        ballon7 = findViewById(R.id.ballon7);
        ballon8 = findViewById(R.id.ballon8);
        ballon9 = findViewById(R.id.ballon9);
        gridLayout = findViewById(R.id.gridLayout);

        mediaPlayer = MediaPlayer.create(this, R.raw.balloon_popping);

        balloonsArray = new ImageView[]{ballon1, ballon2, ballon3, ballon4, ballon5, ballon6, ballon7, ballon8, ballon9};


        //total time, how much time to decrease
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //method that determine what to be done as time decreases
//                textViewCountDown.setText(String.valueOf(millisUntilFinished / 1000));
                textViewCountDown.setText(String.valueOf(millisUntilFinished / 1000));
                Log.e("Count down in 5", String.valueOf(millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                ballonsControl();
                Log.e("Count down in 5", "exiting now");


                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        textViewTime.setText("Remaining Time: " + millisUntilFinished / 1000);
                        Log.e("Count down in 30", String.valueOf(millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        Log.e("Exit 30", "heading to result now");
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        finish();
                    }
                }.start();
            }
        }.start();
    }

    //increase score when ballon is clicked
    //view object represent each component
    public void increaseScoreByOne(View view) {
        score++;
        textViewScore.setText("Score: " + score);

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
        //if not playing
        mediaPlayer.start();

        if (view.getId() == ballon1.getId()) {
            ballon1.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon2.getId()) {
            ballon2.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon3.getId()) {
            ballon3.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon4.getId()) {
            ballon4.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon5.getId()) {
            ballon5.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon6.getId()) {
            ballon6.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon7.getId()) {
            ballon7.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon8.getId()) {
            ballon8.setImageResource(R.drawable.burst);
        }
        if (view.getId() == ballon9.getId()) {
            ballon9.setImageResource(R.drawable.burst);
        }

    }

    public void ballonsControl() {
        textViewCountDown.setVisibility(View.INVISIBLE);
        textViewTime.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);
//        gridLayout.setVisibility(View.VISIBLE);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView balloon : balloonsArray) {
                    balloon.setVisibility(View.INVISIBLE);
                    balloon.setImageResource(R.drawable.ballon);
                }
                gridLayout.setVisibility(View.VISIBLE);

                //make ballon visible randomly
                Random random = new Random();
                int i = random.nextInt(balloonsArray.length);
                balloonsArray[i].setVisibility(View.VISIBLE);

                if (score <= 5) {
                    handler.postDelayed(runnable, 2000); //ballon moving every 2 seconds
                } else if (score > 5 && score <= 10) {
                    handler.postDelayed(runnable, 1500);
                } else if (score > 10 && score <= 15) {
                    handler.postDelayed(runnable, 1000);
                } else if (score > 15) {
                    handler.postDelayed(runnable, 500);
                }
                //higher the score the harder it will be
            }
        };

        handler.post(runnable);
    }

    //introducing menu.xml to java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.volume) {
            if (!soundStatus) {
                mediaPlayer.setVolume(0, 0); //complete turn off
                item.setIcon(R.drawable.ic_baseline_volume_off_24);
                soundStatus = true;
            } else {
                mediaPlayer.setVolume(1, 1); //complete turn off
                item.setIcon(R.drawable.ic_baseline_volume_up_24);
                soundStatus = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}