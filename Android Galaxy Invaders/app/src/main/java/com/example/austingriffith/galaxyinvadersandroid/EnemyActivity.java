package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class EnemyActivity {

    private int mX;
    private int mY;
    private Rect mCollision;
    private int mScreenSizeX;
    private int mScreenSizeY;
    private int mEnemies[];
    private int mMaxX;
    private int mMaxY;

    //alien4 for the enemy
    //we have already pasted the alien4 in the drawable folder
    private Bitmap alien4;
    private Bitmap alien1 ;
    private Bitmap alien2 ;
    private Bitmap alien3 ;

    //x and y coordinates
    private int x; private int y;   //alien 4

    private int x1; private int y1;  //alien1
    private int x2; private int y2;  //alien2
    private int x3; private int y3;  //alien3


    //enemy speed
    private int speed = 1;

    //min and max coordinates to keep the enemy inside the screen
    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    //creating a rect object
    private Rect detectCollision;


    public EnemyActivity(Context context, int screenX, int screenY) {

        mScreenSizeX = screenX;
        mScreenSizeY = screenY;

        //getting alien4 from drawable resource
        alien1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo1);
        alien2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo2);
        alien3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo3);
        alien4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo4);

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY) - alien4.getHeight();

        y1 = generator.nextInt(maxY) - alien1.getHeight() - 200;
        y2 = generator.nextInt(maxY) - alien2.getHeight();
        y3 = generator.nextInt(maxY) - alien3.getHeight();

        //initializing rect object
        detectCollision = new Rect(x, y, alien4.getWidth(), alien4.getHeight());

    }

    public void update(int playerSpeed) {
        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed;
        x -= speed;
        //if the enemy reaches the left edge
        if (x < minX - alien4.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - alien4.getHeight() + 25;
        }


        x1 -= playerSpeed;
        x1 -= speed;
        if (x1 < minX - alien1.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x1 = maxX;
            y1 = generator.nextInt(maxY) - alien1.getHeight() - 150;
            Log.d("EnemyActivity", "INSIDE UPDATE") ;
        }

        x2 -= playerSpeed;
        x2 -= speed;
        if (x2 < minX - alien2.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x2 = maxX;
            y2 = generator.nextInt(maxY) - alien2.getHeight() + 50;
        }

        x3 -= playerSpeed;
        x3 -= speed;
        if (x3 < minX - alien3.getWidth())
        {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x3 = maxX;
            y3 = generator.nextInt(maxY) - alien3.getHeight() - 200;
        }

        //Adding the top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + alien4.getWidth();
        detectCollision.bottom = y + alien4.getHeight();

//        for (EnemyActivity e : mEnemies) {
//            e.update();
//        }

    }   //END OF UPDATE METHOD


    //adding a setter to x coordinate so that we can change it after collision
    public void setX(int x){
        this.x = x;
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    //getters methods for coordinates and speed
    public Bitmap getAlien4() {
        return alien4;
    }

    public Bitmap getAlien1() {
        return alien1 ;
    }

    public Bitmap getAlien2() {
        return alien2 ;
    }

    public Bitmap getAlien3() {
        return alien3 ;
    }

    public int getX() {
        return x ;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }




    //public Rect getCollision() { return detectCollision; }
//
    public void hit(){ destroy(); }
//
    public void destroy(){ y = mScreenSizeY + 1; }



}
