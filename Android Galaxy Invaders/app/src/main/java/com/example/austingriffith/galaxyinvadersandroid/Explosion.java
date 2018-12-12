package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion {

    //collideExplosion object
    private Bitmap collideExplosion;

    //coordinate variables
    private int x;
    private int y;

    //constructor for class
    public Explosion(Context context) {
        //getting boom image from drawable resource
        collideExplosion = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.explosion);

        //setting the coordinate outside the screen after collision
        x = -150;
        y = -150;
    }

    //setters for x and y to make it visible at the place of collision of player and alien object
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //getter methods for coordinates and collision
    public Bitmap getBitmap() {
        return collideExplosion;
    }

    public void setBitmap(Bitmap collideExplosion) {
        this.collideExplosion = collideExplosion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
