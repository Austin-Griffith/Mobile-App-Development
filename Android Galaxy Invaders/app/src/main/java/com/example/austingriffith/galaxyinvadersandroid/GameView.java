package com.example.austingriffith.galaxyinvadersandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private int enemyCount = 1;


    //Adding an stars list
    private ArrayList<GalaxyBackground> galaxy = new ArrayList<GalaxyBackground>();

    public GameView(Context context, int screenX, int screenY)
    {
        super(context);

        //initializing playerObject object
        //passing screen size to playerObject constructor
        playerObject = new PlayerActivity(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();


        //adding 100 stars you may increase the number
        int starsInGalaxy = 400;
        for (int i = 0; i < starsInGalaxy; i++) {
            GalaxyBackground s  = new GalaxyBackground(screenX, screenY);
            galaxy.add(s);
        }

        //initializing enemy object array with objects
        enemies = new EnemyActivity[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new EnemyActivity(context, screenX, screenY);
        }

        aliens = new EnemyActivity[enemyCount];
        for (int i=0; i < enemyCount; i++) {
            Log.d("Debugging", "INSIDE FOR LOOP") ;
            aliens[i] = new EnemyActivity(context, screenX, screenY) ;
        }

    }

    private void update() {
        //updating playerObject position within while game loop
        playerObject.update();

        //Updating the stars with playerObject speed
        for (GalaxyBackground s : galaxy) {
//            s.scrolling(playerObject.getSpeed());
            s.scrolling(0);
        }

        //updating the enemy coordinate with respect to player speed
        for(int i=0; i < enemyCount; i++){
            enemies[i].update(playerObject.getSpeed());
        }

        for (int i=0 ; i < enemyCount; i++) {
            aliens[i].update(playerObject.getSpeed());
        }


    }

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
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(enemies[i].getBitmap(), enemies[i].getX(), enemies[i].getY(), paint);
            }

            for (int i=0; i < enemyCount; i++) {
                Log.d("Debugging", "INSIDE DRAW FOR LOOP") ;
                canvas.drawBitmap(aliens[i].getAlien1(), aliens[i].getX(), aliens[i].getY(), paint);
            }
            Log.d("Debugging", "OUTSIDE DRAW FOR LOOP") ;

            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
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
                break;
        }
        return true;
    }

}