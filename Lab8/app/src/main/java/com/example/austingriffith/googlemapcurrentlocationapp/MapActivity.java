package com.example.austingriffith.googlemapcurrentlocationapp;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onMapReady: Map has been loaded");
        mMap = googleMap;

        getDeviceLocation();

        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        }

    }   //END OF onMapReady()

    private static final String TAG = "MapActivity" ;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION ;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234 ;
    private static final float DEFAULT_ZOOM = 15f ;

    //variable for project
    private Boolean mLocationPermissionGranted = false ;
    private GoogleMap mMap ;
    private FusedLocationProviderClient mFusedLocationProviderClient ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getLocationPermission();
    }



    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        Log.d(TAG, "getDeviceLocation: REACHED INSIDE getDeviceLocation METHOD");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if(task.isSuccessful()){

                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            Log.d(TAG, "getDeviceLocation: WE ARE INSIDE METHOD");
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
                            Log.d(TAG, "onComplete: WE ARE INSIDE METHOD --> MOVE CAMERA JUST EXECUTED!");

                        }
                            else {
                                Log.d(TAG, "onComplete: current location is null");
                                Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }



    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: INSIDE THE MOVE CAMERA METHOD!");
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }


    private void initMap() {
        Log.d(TAG, "Initializing Map") ;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map) ;
        mapFragment.getMapAsync(MapActivity.this);
    }

    private void getLocationPermission() {
        Log.d(TAG, "Getting needed location permissions") ;
        String [] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION } ;

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true ;
                initMap();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG, "onRequestPermissionsResult: has been called") ;
        mLocationPermissionGranted = false ;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {

                if (grantResults.length > 0 ) {

                    //need to loop through grantResults bc they might be multiple results
                    for (int i=0; i < grantResults.length; i++ ) {

                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {

                            mLocationPermissionGranted = false ;
                            Log.d(TAG, "onRequestPermissionsResult: permissions not granted ") ;
                            //break out here if permissions were not granted
                            return ;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permissions granted ") ;
                    mLocationPermissionGranted = true ;

                    //permission are granted so now can initialize map
                    initMap();
                }
            }
        }
    }



}   //END OF CLASS