package com.wayneyong.help_the_innocent_bird_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird, enemy1, enemy2, enemy3, coin1, coin2, right1, right2, right3;
    private TextView textViewScore, textViewStartInfo;
    private ConstraintLayout constraintLayout;

    private boolean touchControl = false;
    private boolean beginControl = false;

    //repeat movement of the character within specified time, we use runnable and handler
    private Runnable runnable, runnable2;
    private Handler handler, handler2;

    //positions of bird
    int birdX, enemy1X, enemy2X, enemy3X, coin1X, coin2X;
    int birdY, enemy1Y, enemy2Y, enemy3Y, coin1Y, coin2Y;

    //dimensions of bird
    int screenWidth;
    int screenHeight;

    //remaining right
    int right = 3;

    //points
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.bird);
        enemy1 = findViewById(R.id.enemy1);
        enemy2 = findViewById(R.id.enemy2);
        enemy3 = findViewById(R.id.enemy3);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        textViewScore = findViewById(R.id.textViewScore);
        textViewStartInfo = findViewById(R.id.textViewStartInfo);
        constraintLayout = findViewById(R.id.constraintLayout);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                textViewStartInfo.setVisibility(View.INVISIBLE);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchControl = true;

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchControl = false;

                }
                //screen touched for the first time
                if (!beginControl) {

                    beginControl = true;

                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight = (int) constraintLayout.getHeight();

                    birdX = (int) bird.getX();
                    birdY = (int) bird.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            moveToBird();
                            enemyControl();
                            collisionControl();
                            //specify how many second runnable need to repeat
//                            handler.postDelayed(runnable, 20);
                        }
                    };
                    handler.post(runnable);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        touchControl = true;

                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        touchControl = false;

                    }
                }
                return true;
            }
        });
    }

    //move the bird and call this method in run
    public void moveToBird() {

        //detect the if the screen is touch
        //if screen is not touched, bird will go off the screen
        if (touchControl) {
            //decrease height
            birdY = birdY - (screenHeight / 50);
        } else {
            birdY = birdY + (screenHeight / 50);
        }

        //prevent bird from going off screen
        if (birdY <= 0) {
            //remain Y at 0 if exceed
            birdY = 0;
        }
        if (birdY >= screenHeight - bird.getHeight()) {
            birdY = (screenHeight - bird.getHeight());
        }

        bird.setY(birdY);
    }

    public void enemyControl() {

        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);


        //Enemy 1 character
        //determine movement speed of character, reduce position of x axis by 150, when run method work enemy1 move
        enemy1X = enemy1X - (screenWidth / 150);

        //increase speed based on score
        if (score >= 50 & score < 100) {
            enemy1X = enemy1X - (screenWidth / 130);
        }
        if (score >= 100 && score < 150) {
            enemy1X = enemy1X - (screenWidth / 120);
        }
        if (score >= 150) {
            enemy1X = enemy1X - (screenWidth / 100);
        }


        if (enemy1X < 0) {
            enemy1X = screenWidth + 200;
            enemy1Y = (int) Math.floor(Math.random() * screenHeight);

            //prevent enemy from going off screen
            if (enemy1Y <= 0) {
                //remain Y at 0 if exceed
                enemy1Y = 0;
            }
            if (enemy1Y >= screenHeight - enemy1.getHeight()) {
                enemy1Y = (screenHeight - enemy1.getHeight());
            }
        }

        //determine final position
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);


        //Enemy 2 character
        enemy2X = enemy2X - (screenWidth / 140);

        //increase speed based on score
        if (score >= 50 & score < 100) {
            enemy2X = enemy2X - (screenWidth / 120);
        }
        if (score >= 100 && score < 150) {
            enemy2X = enemy2X - (screenWidth / 110);
        }
        if (score >= 150) {
            enemy2X = enemy2X - (screenWidth / 90);
        }

        if (enemy2X < 0) {
            enemy2X = screenWidth + 200;
            enemy2Y = (int) Math.floor(Math.random() * screenHeight);

            //prevent enemy from going off screen
            if (enemy2Y <= 0) {
                //remain Y at 0 if exceed
                enemy2Y = 0;
            }
            if (enemy2Y >= screenHeight - enemy2.getHeight()) {
                enemy2Y = (screenHeight - enemy2.getHeight());
            }
        }

        //determine final position
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);


        //Enemy 3 character
        enemy3X = enemy3X - (screenWidth / 130);

        if (score >= 50 & score < 100) {
            enemy3X = enemy3X - (screenWidth / 110);
        }
        if (score >= 100 && score < 150) {
            enemy3X = enemy3X - (screenWidth / 100);
        }
        if (score >= 150) {
            enemy3X = enemy3X - (screenWidth / 80);
        }

        if (enemy3X < 0) {
            enemy3X = screenWidth + 200;
            enemy3Y = (int) Math.floor(Math.random() * screenHeight);

            //prevent enemy from going off screen
            if (enemy3Y <= 0) {
                //remain Y at 0 if exceed
                enemy3Y = 0;
            }
            if (enemy3Y >= screenHeight - enemy3.getHeight()) {
                enemy3Y = (screenHeight - enemy3.getHeight());
            }
        }

        //determine final position
        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);

        //Coin 1 character
        coin1X = coin1X - (screenWidth / 120);

        if (coin1X < 0) {
            coin1X = screenWidth + 200;
            coin1Y = (int) Math.floor(Math.random() * screenHeight);

            //prevent enemy from going off screen
            if (coin1Y <= 0) {
                //remain Y at 0 if exceed
                coin1Y = 0;
            }
            if (coin1Y >= screenHeight - coin1.getHeight()) {
                coin1Y = (screenHeight - coin1.getHeight());
            }
        }

        //determine final position
        coin1.setX(coin1X);
        coin1.setY(coin1Y);


        //Coin 2 character
        coin2X = coin2X - (screenWidth / 110);

        if (coin2X < 0) {
            coin2X = screenWidth + 200;
            coin2Y = (int) Math.floor(Math.random() * screenHeight);

            //prevent enemy from going off screen
            if (coin2Y <= 0) {
                //remain Y at 0 if exceed
                coin2Y = 0;
            }
            if (coin2Y >= screenHeight - coin2.getHeight()) {
                coin2Y = (screenHeight - coin2.getHeight());
            }
        }

        //determine final position
        coin2.setX(coin2X);
        coin2.setY(coin2Y);


    }

    public void collisionControl() {

        //determine center point of enemy1
        int centerEnemy1X = enemy1X + enemy1.getWidth() / 2; //get position value fo x-axis at center of character
        int centerEnemy1Y = enemy1Y + enemy1.getHeight() / 2; //get position value fo y-axis at center of character

        //if center point of the character is within the area of bird, assume collision occurred
        //necessary check for position
        if (centerEnemy1X >= birdX
                && centerEnemy1X <= (birdX + bird.getWidth())
                && centerEnemy1Y >= birdY
                && centerEnemy1Y <= (birdY + bird.getHeight())) {
            //take the character off the screen and reintroduce into the game
            enemy1X = screenWidth + 200;
            //reduce right to play
            right--;
        }

        //determine center point of enemy2
        int centerEnemy2X = enemy2X + enemy2.getWidth() / 2;
        int centerEnemy2Y = enemy2Y + enemy2.getHeight() / 2;

        if (centerEnemy2X >= birdX
                && centerEnemy2X <= (birdX + bird.getWidth())
                && centerEnemy2Y >= birdY
                && centerEnemy2Y <= (birdY + bird.getHeight())) {

            enemy2X = screenWidth + 200;
            right--;
        }

        //determine center point of enemy3
        int centerEnemy3X = enemy3X + enemy3.getWidth() / 2;
        int centerEnemy3Y = enemy3Y + enemy3.getHeight() / 2;


        if (centerEnemy3X >= birdX
                && centerEnemy3X <= (birdX + bird.getWidth())
                && centerEnemy3Y >= birdY
                && centerEnemy3Y <= (birdY + bird.getHeight())) {
            enemy3X = screenWidth + 200;
            right--;
        }

        //determine center point of coin1
        int centerCoin1X = coin1X + coin1.getWidth() / 2;
        int centerCoin1Y = coin1Y + coin1.getHeight() / 2;

        if (centerCoin1X >= birdX
                && centerCoin1X <= (birdX + bird.getWidth())
                && centerCoin1Y >= birdY
                && centerCoin1Y <= (birdY + bird.getHeight())) {

            coin1X = screenWidth + 200;
            //earn 10 points
            score = score + 10;
            textViewScore.setText("" + score);

        }


        //determine center point of coin2
        int centerCoin2X = coin2X + coin2.getWidth() / 2;
        int centerCoin2Y = coin2Y + coin2.getHeight() / 2;

        if (centerCoin2X >= birdX
                && centerCoin2X <= (birdX + bird.getWidth())
                && centerCoin2Y >= birdY
                && centerCoin2Y <= (birdY + bird.getHeight())) {

            coin2X = screenWidth + 200;
            //earn 10 points
            score = score + 10;
            textViewScore.setText("" + score);
        }

        //determine the number of right condition and replace with grey heart
        if (right > 0 && score < 200) {

            if (right == 2) {
                Log.e("2 right ", "left 2 right");
                right1.setImageResource(R.drawable.favorite_grey);
            }
            if (right == 1) {
                Log.e("1 right ", "left 1 right");
                right2.setImageResource(R.drawable.favorite_grey);
            }

            handler.postDelayed(runnable, 20);
        } else if (score >= 200) {
            Log.e("score more than 200 ", "reached its max score, winner!");
            handler.removeCallbacks(runnable);
            //make screen touch passive
            constraintLayout.setEnabled(false);
            textViewStartInfo.setVisibility(View.VISIBLE);
            textViewStartInfo.setText("Congratulations. You won the game!");
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    birdX = birdX + (screenWidth / 300);
                    bird.setX(birdX);
                    bird.setY(screenHeight / 2f);

                    //if bird reach end of screen
                    if (birdX <= screenWidth) {
                        handler2.postDelayed(runnable2, 20);
                    } else {
                        handler2.removeCallbacks(runnable2);

                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);


        } else if (right == 0) {
            Log.e("0 right: ", "hit 0 aldy, should move to result");
            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.favorite_grey);

            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();

        }
    }
}



