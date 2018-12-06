package com.example.austingriffith.googlemapcurrentlocationapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity" ;
    private static final int ERROR_DIALOG_REQUEST = 9001 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServicesOkay()) {
            init() ;
        }
    }

    private void init() {
        Button buttonMap = (Button) findViewById(R.id.buttonMap) ;
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class) ;
                startActivity(intent);
            }
        });
    }


    public boolean isServicesOkay() {
        Log.d(TAG, "isServicesOK: checking google play services version") ;

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this) ;
        if (available == ConnectionResult.SUCCESS) {
            //everything is okay and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play services is working fine");
            return true ;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
                //an error ocurred but can be resolved
            Log.d(TAG, "isServicesOK: an error has occured but we can fix this error");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST) ;
            dialog.show();
        }
        else {
            Toast.makeText(this, "You cant make map requests", Toast.LENGTH_SHORT).show();
        }
        return false ;
    }

}   //end of class
