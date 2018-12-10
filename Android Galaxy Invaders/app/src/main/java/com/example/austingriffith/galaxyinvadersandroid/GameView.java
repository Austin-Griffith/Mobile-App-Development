package com.example.austingriffith.galaxyinvadersandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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

    volatile boolean playing;
    private Thread gameThread = null;

    //adding the player to this class
    private PlayerActivity player;

    //objects used for drawing to screen
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context, int screenX, int screenY)
    {
        super(context);

        //initializing player object
        //passing screen size to player constructor
        player = new PlayerActivity(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    private void update() {
        //updating player position within while game loop
        player.update();
    }

    private void draw() {
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();

            //creating the black background
            canvas.drawColor(Color.BLACK);

            //Drawing the player
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);

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
                player.stopBoosting();
                break;

            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen do something here
                //boosting the space jet when screen is pressed
                player.setBoosting();
                break;
        }
        return true;
    }

}