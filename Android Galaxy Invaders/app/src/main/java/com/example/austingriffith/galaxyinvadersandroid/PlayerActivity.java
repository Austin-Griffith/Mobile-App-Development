package com.example.austingriffith.galaxyinvadersandroid;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;

public class PlayerActivity {

    //Bitmap to get character from image
    private Bitmap player;

    //coordinates
    private int x;
    private int y;

    //motion speed of the character
    private int speed = 0;


    //boolean variable to track the ship is boosting or not
    private boolean boosting;

    private Rect detectCollision;

    //Gravity Value to add gravity effect on the ship
    private final int GRAVITY = -10;

    //Controlling X & Y coordinate so that ship won't go outside the screen
    private int maxY; private int minY;
    private int minX ; private int maxX ;

    //Limit the bounds of the ship's speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;


    private boolean mIsSteerLeft, mIsSteerRight;
    private float mSteerSpeed;
    private ArrayList<Bullet> mBullets;
    private Context mContext;
    private int mScreenSizeX, mScreenSizeY;




    //player class constructor
    public PlayerActivity(Context context, int screenX, int screenY) {
        x = 500;
        y = 1500;
        speed = 1;

        //Getting player from drawable resource
        player = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);

        //calculating maxY
        maxY = screenY - player.getHeight();
        maxX = screenX - player.getWidth() ;

        //top edge's y point is 0 so min y will always be zero
        minY = 0;
        minX = 0;

        //setting the boosting value to false initially
        boosting = false;

        //initializing rect object
        detectCollision =  new Rect(x, y, player.getWidth(), player.getHeight());



        mScreenSizeX = screenX;
        mScreenSizeY = screenY;
        mContext = context;

        mBullets = new ArrayList<>();



    }   //END OF PLAYERACTIVITY


    //setting boosting true
    public void setBoosting() {
        boosting = true;
    }

    //setting boosting false
    public void stopBoosting() {
        boosting = false;
    }


    public void update() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            speed += 2;
        } else {
            //slowing down if not boosting
            speed -= 5;
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //moving the ship down
        y -= speed + GRAVITY;

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + player.getWidth();
        detectCollision.bottom = y + player.getHeight();


        if (mIsSteerLeft){
            x -= 10 * mSteerSpeed;
            if (x<minX){
                x = minX;
            }
        }else if (mIsSteerRight){
            x += 10 * mSteerSpeed;
            if (x>maxX){
                x = maxX;
            }
        }

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + player.getWidth();
        detectCollision.bottom = y + player.getHeight();





//        for (Bullet l : mBullets) {
//            l.update();
//        }
//
//        boolean deleting = true;
//        while (deleting) {
//            if (mBullets.size() != 0) {
//                if (mBullets.get(0).getY() < 0) {
//                    mBullets.remove(0);
//                }
//            }
//
//            if (mBullets.size() == 0 || mBullets.get(0).getY() >= 0) {
//                deleting = false;
//            }
//        }


    }   //END OF UPDATE METHOD



    public ArrayList<Bullet> getBullets() { return mBullets; }

    public void fire(){ mBullets.add(new Bullet(mContext, mScreenSizeX, mScreenSizeY, x, y, player, false)); }


    public Rect getDetectCollision() { return detectCollision; }

    public Bitmap getBitmap() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }



    public void steerRight(float speed)
    {
        mIsSteerLeft = false;
        mIsSteerRight = true;
        mSteerSpeed = Math.abs(speed);
    }

    public void steerLeft(float speed)
    {
        mIsSteerRight = false;
        mIsSteerLeft = true;
        mSteerSpeed = Math.abs(speed);
    }

    public void stay()
    {
        mIsSteerLeft = false;
        mIsSteerRight = false;
        mSteerSpeed = 0;
    }


}