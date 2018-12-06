package com.example.austingriffith.galaxyinvadersandroid;

import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class PlayerActivity {

    //Bitmap to get character from image
    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;

    //motion speed of the character
    private int speed = 0;

    //constructor
    public PlayerActivity(Context context) {
        x = 500;
        y = 1500;
        speed = 1;

        //Getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);
    }

    //Method to update coordinate of character
    public void update(){
        //updating x coordinate
        x++;
    }

    /*
     * These are getters you can generate it automatically
     * right click on editor -> generate -> getters
     * */
    public Bitmap getBitmap() {
        return bitmap;
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
}