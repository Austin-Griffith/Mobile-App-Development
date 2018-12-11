package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.Random;

public class EnemyActivity {

    //bitmap for the enemy
    //we have already pasted the bitmap in the drawable folder
    private Bitmap bitmap;
    //change bitmap to alien4 ^^^
    private Bitmap alien1 ;
    private Bitmap alien2 ;
    private Bitmap alien3 ;

    //x and y coordinates
    private int x;
    private int y;

    private int x1;
    private int y1;

    //enemy speed
    private int speed = 1;

    //min and max coordinates to keep the enemy inside the screen
    private int maxX;
    private int minX;

    private int maxY;
    private int minY;


    public EnemyActivity(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo1);
        alien1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo2);

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();

        y1 = generator.nextInt(maxY) - alien1.getHeight();

    }

    public void update(int playerSpeed) {
        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed;
        x -= speed;
        //if the enemy reaches the left edge
        if (x < minX - bitmap.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }



        x1 -= playerSpeed;
        x1 -= speed;
        //if the enemy reaches the left edge
        if (x1 < minX - alien1.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x1 = maxX;
            y1 = generator.nextInt(maxY) - alien1.getHeight();
            Log.d("EnemyActivity", "INSIDE UPDATE") ;
        }

    }



    //getters methods for coordinates and speed
    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getAlien1() {
        return alien1 ;
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
}
