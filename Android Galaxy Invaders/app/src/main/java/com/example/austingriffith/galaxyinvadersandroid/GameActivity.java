package com.example.austingriffith.galaxyinvadersandroid;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    //declaring gameview
    private GameView spaceGame;

    private float mXTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


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


//        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
//        manager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_GAME);

    }


    //activity is no longer in foreground
    @Override
    protected void onPause() {
        super.onPause();
        spaceGame.pause();
    }


    //activity is in foreground
    @Override
    protected void onResume() {
        super.onResume();
        spaceGame.resume();
    }



    //  TRYING TO IMPLEMENT THE USE OF ACCELEROMETER IN PHONE TO CONTROL STEERING OF PLAYER OBJECT
    //WAS NOT ABLE TO GET THIS METHOD WORKING WITHOUT CRASHING THE ACTIVITY

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        mXTemp = event.values[0];
//
//        if (event.values[0] > 1){
//            spaceGame.steerLeft(event.values[0]);
//        }
//        else if (event.values[0] < -1){
//            spaceGame.steerRight(event.values[0]);
//        }else{
//            spaceGame.stay();
//        }
//    }
//
//    //@Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }




}