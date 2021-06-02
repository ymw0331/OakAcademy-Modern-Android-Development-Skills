package com.wayneyong.mp3_player_application;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private Button buttonPlayPause, buttonPrevious, buttonNext;
    private TextView textViewFileNameMusic, textViewProgress, textViewTotalTime;
    private SeekBar seekBarVolume, seekBarMusic;

    //variable that pass info from adapter
    String title, filePath;
    int position;
    ArrayList<String> list;
    private MediaPlayer mediaPlayer;

    Runnable runnable;
    Handler handler;
    int totalTime;

    private Animation animation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        buttonPlayPause = findViewById(R.id.buttonPause);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);
        textViewFileNameMusic = findViewById(R.id.textViewNameMusic);
        textViewProgress = findViewById(R.id.textViewProgress);
        textViewTotalTime = findViewById(R.id.textViewTotalTime);
        seekBarVolume = findViewById(R.id.volumeSeekBar);
        seekBarMusic = findViewById(R.id.musicSeekBar);


        animation = AnimationUtils.loadAnimation(MusicActivity.this, R.anim.translate_animation);
        textViewFileNameMusic.setAnimation(animation);

        title = getIntent().getStringExtra("title");
        filePath = getIntent().getStringExtra("filepath");
        position = getIntent().getIntExtra("position", 0);
        list = getIntent().getStringArrayListExtra("list");

        textViewFileNameMusic.setText(title);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialise media player object, if not do this, app will crash
                mediaPlayer.reset();

                //if the variable position is 0, pass the variable at the last position
                if (position == 0) {
                    position = list.size() - 1;
                } else {
                    position--;
                }

                String newFilePath = list.get(position);
                try {
                    mediaPlayer.setDataSource(newFilePath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    //after previous is click, pause
                    buttonPlayPause.setBackgroundResource(R.drawable.pause);

                    //print the name of audio file to textview
                    String newTitle = newFilePath.substring(newFilePath.lastIndexOf("/") + 1);
                    textViewFileNameMusic.setText(newTitle);

                    //animation to be reset
                    textViewFileNameMusic.clearAnimation();
                    textViewFileNameMusic.startAnimation(animation);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    buttonPlayPause.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    buttonPlayPause.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.reset();

                //if the variable position is 0, pass the variable at the last position
                if (position == list.size() - 1) {
                    position = 0;
                } else {
                    position++;
                }

                String newFilePath = list.get(position);
                try {
                    mediaPlayer.setDataSource(newFilePath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    //after previous is click, pause
                    buttonPlayPause.setBackgroundResource(R.drawable.pause);

                    //print the name of audio file to textview
                    String newTitle = newFilePath.substring(newFilePath.lastIndexOf("/") + 1);
                    textViewFileNameMusic.setText(newTitle);

                    //animation to be reset
                    textViewFileNameMusic.clearAnimation();
                    textViewFileNameMusic.startAnimation(animation);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBarVolume.setProgress(progress);
                    float volumeLevel = progress / 100f;
                    mediaPlayer.setVolume(volumeLevel, volumeLevel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    //rewind or fast forward
                    mediaPlayer.seekTo(progress);
                    seekBarMusic.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                totalTime = mediaPlayer.getDuration();
                seekBarMusic.setMax(totalTime);

                int currentPostition = mediaPlayer.getCurrentPosition();
                seekBarMusic.setProgress(currentPostition);
                //every second
                handler.postDelayed(runnable, 1000);

                //print duration of audio file
                String elapsedTime = createTimeLabel(currentPostition);
                String lastTime = createTimeLabel(totalTime);

                textViewProgress.setText(elapsedTime);
                textViewTotalTime.setText(lastTime);

                //if audio file run out, go the next
                if (elapsedTime.equals(lastTime)) {

                    mediaPlayer.reset();

                    //if the variable position is 0, pass the variable at the last position
                    if (position == list.size() - 1) {
                        position = 0;
                    } else {
                        position++;
                    }

                    String newFilePath = list.get(position);
                    try {
                        mediaPlayer.setDataSource(newFilePath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                        //after previous is click, pause
                        buttonPlayPause.setBackgroundResource(R.drawable.pause);

                        //print the name of audio file to textview
                        String newTitle = newFilePath.substring(newFilePath.lastIndexOf("/") + 1);
                        textViewFileNameMusic.setText(newTitle);

                        //animation to be reset
                        textViewFileNameMusic.clearAnimation();
                        textViewFileNameMusic.startAnimation(animation);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        handler.post(runnable);
    }

    public String createTimeLabel(int currentPosition) {

        // 1 minute = 60 sec
        // 1 sec = 1000 millisec
        String timeLabel;
        int minute, second;

        minute = currentPosition / 1000 / 60;
        second = currentPosition / 1000 / 60;

        if (second < 10) {
            timeLabel = minute + ":0" + second;

        } else {
            timeLabel = minute + ":" + second;
        }
        return timeLabel;
    }
}