package com.example.austingriffith.galaxyinvadersandroid;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    //declaring gameview
    private GameView spaceGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing game view object
        //spaceGame = new GameView(this);

        //adding it to content view
        //setContentView(spaceGame);

        //Getting display object
        Display display = getWindowManager().getDefaultDisplay();

        //Getting the screen resolution into point object
        Point size = new Point();
        display.getSize(size);

        //Initializing game view object
        //this time we are also passing the screen size to the GameView constructor
        spaceGame = new GameView(this, size.x, size.y);

        //adding it to contentview
        setContentView(spaceGame);
    }

    //pausing the game when activity is in paused state
    //activity is no longer in foreground
    @Override
    protected void onPause() {
        super.onPause();
        spaceGame.pause();
    }

    //running the game when activity is in resumed state
    //activity is in foreground
    @Override
    protected void onResume() {
        super.onResume();
        spaceGame.resume();
    }
}