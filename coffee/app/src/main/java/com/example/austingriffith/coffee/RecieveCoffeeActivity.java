package com.example.austingriffith.coffee;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecieveCoffeeActivity extends Activity {

    private String coffeeShop ;
    private String coffeeShopURL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_coffee);

        Intent intent = getIntent() ;
        coffeeShop = intent.getStringExtra("coffeeShopName") ;
        coffeeShopURL = intent.getStringExtra("coffeeShopURL") ;

        Log.i("shop received", coffeeShop) ;
        Log.i("url received", coffeeShopURL) ;

        TextView messageView = findViewById(R.id.coffeeShopTextView);
        messageView.setText("You should check out " + coffeeShop);

        final ImageButton imageButton = findViewById(R.id.imageButton) ;
        View.OnClickListener imgbuttonclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebsite(v);
            }
        } ;

        imageButton.setOnClickListener(imgbuttonclick);
    }

    public void loadWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW) ;
        intent.setData(Uri.parse(coffeeShopURL));
        startActivity(intent);
    }

}
