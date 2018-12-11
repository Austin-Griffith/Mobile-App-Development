package com.example.austingriffith.galaxyinvadersandroid;

import java.util.Random;

public class GalaxyBackground {

    private int xVaule;
    private int yValue;
    private int acceleration;
    private int maxSizeX;
    private int maxSizeY;
    private int minX;
    private int minY;



    public GalaxyBackground(int screenX, int screenY) {
        maxSizeX = screenX;
        maxSizeY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        acceleration = generator.nextInt(10);

        //generating a random coordinate
        //but keeping the coordinate inside the screen size
        xVaule = generator.nextInt(maxSizeX);
        yValue = generator.nextInt(maxSizeY);
    }

    public float findStarSize() {
        //giving random size to stars in galaxy background
        float minX = 1.0f;
        float maxSizeX = 6.0f;
        Random randomStars = new Random();
        float finalX = randomStars.nextFloat() * (maxSizeX - minX) + minX;
        return finalX;
    }
    

    public void scrolling(int objectSpeed) {
        //creating scrolling galaxy animation 
        //animation scrolling starting from top of screen
        //yValue -= objectSpeed;
        yValue -= acceleration;
        
        //if the star reached the bottom edge of the screen
        if (yValue < 0) {
            //starting the stars from bottom edge and giving the infinite scrolling background effect
            yValue = maxSizeY;
            Random generator = new Random();
            xVaule = generator.nextInt(maxSizeX);
            acceleration = generator.nextInt(10);
        }
    }
    

    //getter and setter methods for GalaxyBackground class
    public int getX() {
        return xVaule;
    }

    public int getY() {
        return yValue;
    }
}
