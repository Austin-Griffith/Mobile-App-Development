package com.example.austingriffith.galaxyinvadersandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    @Override
    public void run() {
        //game will run as long as playing == true
        while (playing) {
            update();
            draw();
            control();
        }
    }

//    volatile boolean playing;
    boolean playing;
    private Thread gameThread = null;

    //adding the playerObject to this class
    private PlayerActivity playerObject;

    //objects used for drawing to screen
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    //Adding enemies object array
    private EnemyActivity[] enemies;
    private EnemyActivity[] aliens ;

    //spawning 2 aliens per update call
    private int enemyCount = 2;

    //Adding an stars list
    private ArrayList<GalaxyBackground> galaxy = new ArrayList<GalaxyBackground>();

    //defining a boom object to display blast
    private Explosion explosion;

    private ArrayList<Bullet> mBullets;
    private ArrayList<EnemyActivity> mEnemies;
    private int mScreenSizeX, mScreenSizeY;


    public GameView(Context context, int screenX, int screenY)
    {
        super(context);

        //initializing playerObject object
        //passing screen size to playerObject constructor
        playerObject = new PlayerActivity(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        mScreenSizeX = screenX;
        mScreenSizeY = screenY;

        //adding 400 stars for enhanced background effect
        int starsInGalaxy = 400;
        for (int i = 0; i < starsInGalaxy; i++) {
            GalaxyBackground s  = new GalaxyBackground(screenX, screenY);
            galaxy.add(s);
        }

        //initializing object array with objects
        enemies = new EnemyActivity[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new EnemyActivity(context, screenX, screenY);
        }

        aliens = new EnemyActivity[enemyCount];
        for (int i=0; i < enemyCount; i++) {
            Log.d("Debugging", "INSIDE FOR LOOP") ;
            aliens[i] = new EnemyActivity(context, screenX, screenY) ;
        }

        //initializing explosion object
        explosion = new Explosion(context);

    }

    private void update() {
        //updating playerObject position within while game loop
        playerObject.update();

        //setting explosion outside the user screen
        explosion.setX(-250);
        explosion.setY(-250);

        boolean deleting = true;

        //Updating the stars with playerObject speed
        for (GalaxyBackground s : galaxy) {
//            s.scrolling(playerObject.getSpeed());
            s.scrolling(0);
        }

        //updating the enemy coordinate with respect to player speed
        for(int i=0; i < enemyCount; i++){
            enemies[i].update(playerObject.getSpeed());

            //if collision occurs with player
            if (Rect.intersects(playerObject.getDetectCollision(), enemies[i].getDetectCollision()))
            {
                //displaying boom at that location
                explosion.setX(enemies[i].getX());
                explosion.setY(enemies[i].getY());

                //moving enemy outside the left edge
                enemies[i].setX(-200);
            }
        }

        for (int i=0 ; i < enemyCount; i++) {
            aliens[i].update(playerObject.getSpeed());
            //if collision occurs with player

            if (Rect.intersects(playerObject.getDetectCollision(), aliens[i].getDetectCollision())) {

                //displaying boom at that location
                explosion.setX(aliens[i].getX());
                explosion.setY(aliens[i].getY());

                //moving enemy outside the left edge
                aliens[i].setX(-200);
            }
        }





//        for (EnemyActivity e : mEnemies) {
//            //e.update();
//            if (Rect.intersects(e.getDetectCollision(), playerObject.getDetectCollision())) {
//                e.destroy();
//            }

            for (Bullet b : playerObject.getBullets()) {
                if (Rect.intersects(b.getCollision(), b.getCollision())) {
                    //e.hit();
                    //b.destroy();
                }
            }
        //}


    }   //END OF UPDATE METHOD

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {

            //locking the canvas
            canvas = surfaceHolder.lockCanvas();

            //creating the black background
            canvas.drawColor(Color.BLACK);

            //setting the paint color to white to draw the stars
            paint.setColor(Color.WHITE);

            //drawing all stars
            for (GalaxyBackground s : galaxy) {
                paint.setStrokeWidth(s.findStarSize());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            //Drawing the playerObject
            canvas.drawBitmap(playerObject.getBitmap(), playerObject.getX(), playerObject.getY(), paint);

            //drawing the enemies
            for (int i=0; i < enemyCount; i++) {
                Log.d("Debugging", "INSIDE DRAW FOR LOOP") ;
                canvas.drawBitmap(aliens[i].getAlien1(), aliens[i].getX(), aliens[i].getY(), paint);
            }

            for (int i=0; i < enemyCount; i++) {
                canvas.drawBitmap(aliens[i].getAlien2(), aliens[i].getX(), aliens[i].getY(), paint);
            }

            for (int i=0; i < enemyCount; i++) {
                canvas.drawBitmap(aliens[i].getAlien3(), aliens[i].getX(), aliens[i].getY(), paint);
            }

            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(enemies[i].getAlien4(), enemies[i].getX(), enemies[i].getY(), paint);
            }

            //drawing boom image
            canvas.drawBitmap(explosion.getBitmap(), explosion.getX(), explosion.getY(), paint);


            //THERE IS A BUG IN THE DRAWING OR UPDATE METHOD BECAUSE BULLETS ARE BEING DRAWN TO SCREEN BUT NOT
            //BEING CLEARED UPON ACTION_UP AND ACTION_DOWN CALLS TO onCreate() OVERRIDE METHOD
            for (Bullet b : playerObject.getBullets()) {
                canvas.drawBitmap(b.getBitmap(), b.getX(), b.getY() + 100 , paint);

            }

            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }   //END OF DRAW METHOD




    public void steerLeft(float speed) {
        playerObject.steerLeft(speed);
    }

    public void steerRight(float speed) {
        playerObject.steerRight(speed);
    }

    public void stay() {
        playerObject.stay();
    }


    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //When the user presses on the screen we will do something here


                //stopping the boosting when screen is released
                playerObject.stopBoosting();
                break;

            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen do something here
                //boosting the space jet when screen is pressed
                playerObject.setBoosting();
                playerObject.fire();
                break;
        }
        return true;

    }

}