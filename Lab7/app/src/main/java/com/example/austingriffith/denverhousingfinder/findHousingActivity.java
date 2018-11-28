package com.example.austingriffith.denverhousingfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class findHousingActivity extends Activity {

    private DenverHousing myDenverHousing = new DenverHousing() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_housing);

        final Button button = findViewById(R.id.button) ;
        View.OnClickListener buttonClick = new View.OnClickListener() {
            public void onClick (View view) {
                findHousing(view);
            }
        } ;
        button.setOnClickListener(buttonClick);
    }

    public void findHousing(View view) {

        //get info from spinner drop down
        Spinner interestSpinner = findViewById(R.id.spinner) ;

        //get the spinners string array position
        Integer interest = interestSpinner.getSelectedItemPosition() ;

        //set the housing area value
        myDenverHousing.setHousing(interest) ;

        //get the suggested housing area string
        String possibleHousing = myDenverHousing.getHousing() ;

        //get the suggested housing area URL string
        String suggestedURL = myDenverHousing.getHousingURL() ;

        //for error checking log the names of the housing area and URL to the console
//        Log.i("housing", possibleHousing) ;
//        Log.i("url", suggestedURL) ;

        Intent intent = new Intent(this, getHousingActivity.class) ;
        intent.putExtra("housingArea", possibleHousing) ;
        intent.putExtra("housingAreaURL", suggestedURL) ;
        startActivity(intent);

    }



}  //end of class
