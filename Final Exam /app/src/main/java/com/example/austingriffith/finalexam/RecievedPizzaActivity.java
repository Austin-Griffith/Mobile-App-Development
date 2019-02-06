package com.example.austingriffith.finalexam;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecievedPizzaActivity extends Activity {

    private String pizzaSize1;
    private String pizzaSauce1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_activity_main);

        //get intent
        Intent intent = getIntent();
        pizzaSize1 = intent.getStringExtra("sizeOfPizza");
        pizzaSauce1 = intent.getStringExtra("sauceOnPizza");


        //update text view
        TextView messageView = findViewById(R.id.textView3);
        messageView.setText("You have chosen a " + pizzaSize1 + " with " + pizzaSauce1 + " sauce. Your pizza has the following toppings " +
                "");

        //get image button
        ImageButton imageButton = findViewById(R.id.imageButton);
        //create listener
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                //loadWebSite(view);
            }
        };

        //add listener to the button
        imageButton.setOnClickListener(onclick);
    }

    private void loadWebSite(View view){
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse( ));
        //startActivity(intent);
    }

}
