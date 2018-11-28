package com.example.austingriffith.denverhousingfinder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class getHousingActivity extends Activity {

    private String housingArea;
    private String housingAreaURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_housing);

        Intent intent = getIntent();
        housingArea = intent.getStringExtra("housingArea");
        housingAreaURL = intent.getStringExtra("housingAreaURL");

//        Log.i("housing recieved", housingArea);
//        Log.i("url recieved", housingAreaURL);

        // textView3 is the label in the get_housing.xml file
        TextView messageView = findViewById(R.id.textView3);
        messageView.setText("You should checkout " + housingArea + ".");

        final ImageButton imageButton = findViewById(R.id.imageButton2);
        View.OnClickListener imgbuttonclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHousingURL(v);
            }
        };
        imageButton.setOnClickListener(imgbuttonclick);
    }


        public void loadHousingURL(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW) ;
            intent.setData(Uri.parse(housingAreaURL)) ;
            startActivity(intent);
        }



}
