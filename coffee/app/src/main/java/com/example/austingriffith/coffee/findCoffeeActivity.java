package com.example.austingriffith.coffee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class findCoffeeActivity extends Activity {

    private CoffeeShop myCoffeeShop = new CoffeeShop() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_coffee);

        final Button button = findViewById(R.id.button) ;
        View.OnClickListener buttonClick = new View.OnClickListener() {
            public void onClick (View view) {
                findCofffee(view);
            }
        } ;
        button.setOnClickListener(buttonClick);
    }

    public void findCofffee(View view) {
        //get the spinner
        Spinner crowdSpinner = findViewById(R.id.spinner) ;

        //get spinner item array position
        Integer crowd = crowdSpinner.getSelectedItemPosition();

        //set the coffees shop
        myCoffeeShop.setCoffeeShop(crowd) ;

        //get suggested coffee shop
        String suggestedCoffeeShop = myCoffeeShop.getCoffeeShop();

        //get URL of suggested coffee shop
        String suggestedCoffeeShopURL = myCoffeeShop.getCoffeeShopURL();

        //log the name of the coffee shop and its url to the console
        Log.i("shop", suggestedCoffeeShop) ;
        Log.i("url", suggestedCoffeeShopURL) ;

        Intent intent = new Intent(this , RecieveCoffeeActivity.class) ;
        intent.putExtra("coffeeShopName", suggestedCoffeeShop) ;
        intent.putExtra("coffeeShopURL", suggestedCoffeeShopURL) ;
        startActivity(intent);

    }

}
