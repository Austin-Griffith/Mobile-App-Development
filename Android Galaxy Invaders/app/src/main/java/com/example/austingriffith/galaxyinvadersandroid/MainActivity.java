package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //image button
    private ImageButton buttonPlay;
    private ImageButton buttonExit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //getting the start button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        //adding a listener for user input
        buttonPlay.setOnClickListener(this);

        //getting the exit button
        buttonExit = (ImageButton) findViewById(R.id.buttonExit) ;

        //adding a listener for user input
        //using override to call finish() method to quit app
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


    }

    @Override
    public void onClick(View v) {

        //starting game activity
        startActivity(new Intent(this, GameActivity.class));

    }
}
