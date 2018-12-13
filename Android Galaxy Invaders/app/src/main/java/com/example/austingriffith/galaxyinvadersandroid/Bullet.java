package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Bullet {
    
    private Bitmap bulletBitMap ;
    private int x;
    private int y;
    private  Rect bulletCollision ;
    private int screenSizeX;
    private int screenSizeY;
    private boolean mIsEnemy;
    private int bulletSpeed = 5;


public Bullet(Context context, int screenX, int screenY, int spaceShipX, int spaceShipY, Bitmap player, boolean isEnemy){

        screenSizeX = screenX ;
        screenSizeY = screenY ;
        mIsEnemy = isEnemy;

        bulletBitMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bulletBitMap = Bitmap.createScaledBitmap(bulletBitMap, bulletBitMap.getWidth() * 3/5, bulletBitMap.getHeight() * 3/5, false);


        x = spaceShipX + player.getWidth()/2 - bulletBitMap.getWidth()/2;
        if (mIsEnemy){
            y = spaceShipY + bulletBitMap.getHeight() + 10;
        }else{
            y = spaceShipY - bulletBitMap.getHeight() - 10;
        }

        bulletCollision = new Rect(x, y, x + bulletBitMap.getWidth(), y + bulletBitMap.getHeight());
    }

    public void update(int speed){
        if (mIsEnemy){
            y += bulletBitMap.getHeight() + 10;
            bulletCollision.left = x;
            bulletCollision.top = y;
            bulletCollision.right = x + bulletBitMap.getWidth();
            bulletCollision.bottom = y + bulletBitMap.getHeight();
        }else{
            y -= bulletBitMap.getHeight() - 10;
            bulletCollision.left = x;
            bulletCollision.top = y;
            bulletCollision.right = x + bulletBitMap.getWidth();
            bulletCollision.bottom = y + bulletBitMap.getHeight();
        }


        //decreasing x coordinate so that enemy will move right to left
//        x -= playerSpeed;
//        x -= speed;
//        //if the enemy reaches the left edge
//        if (x < minX - alien4.getWidth()) {
//            //adding the enemy again to the right edge
//            Random generator = new Random();
//            speed = generator.nextInt(10) + 10;
//            x = maxX;
//            y = generator.nextInt(maxY) - alien4.getHeight() + 25;
//        }

//        y -= speed ;
//        y -= bulletSpeed;
//        if (y < screenSizeY) {
//            Random generator = new Random();
//            bulletSpeed = generator.nextInt(10) + 1 ;
//            y = screenSizeY;
//        }


    }

    public boolean isEnemy() {
        return mIsEnemy;
    }

    public Rect getCollision() {
        return bulletCollision;
    }

    public void destroy(){
        if (mIsEnemy){
            y = screenSizeY + 500;
        }else{
            y = 500 - bulletBitMap.getHeight();
        }

    }


    public Bitmap getBitmap() {
        return bulletBitMap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
