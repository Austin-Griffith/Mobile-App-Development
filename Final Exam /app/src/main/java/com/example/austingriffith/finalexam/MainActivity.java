package com.example.austingriffith.finalexam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private PizzaActivity buildPizza = new PizzaActivity() ;
    private TextView pizzaResults ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //string for made up sentence
        pizzaResults = findViewById(R.id.textView3);

        //for the order pizza button
        final Button button = findViewById(R.id.button_order_pizza) ;
        View.OnClickListener buttonClick = new View.OnClickListener() {
            public void onClick (View view) {
                makePizza(view);
            }
        } ;
        button.setOnClickListener(buttonClick);

    }


    private void makePizza(View view) {

        //get pizza sauce from spinner
        Spinner pizzaSizeSpinner = findViewById(R.id.spinner_pizza);
        //get sauce spinner item array position
        Integer size = pizzaSizeSpinner.getSelectedItemPosition();

        //get pizza size from spinner
        Spinner pizzaSauceSpinner = findViewById(R.id.spinner_pizza);
        // get size spinner item array position
        Integer sauce = pizzaSauceSpinner.getSelectedItemPosition() ;

        //set the size and sauce of pizza
        buildPizza.setSizeInfo(size); ;
        buildPizza.setSauceInfo(sauce); ;

        //get pizza info
        String sizeOfPizza = buildPizza.getPizzaSize();
        String sauceOnPizza = buildPizza.getPizzaSauce() ;

        //text view
        pizzaResults = findViewById(R.id.textView3) ;

        //radio buttons
        RadioGroup choice = findViewById(R.id.radioGroup2);
        int choice_id = choice.getCheckedRadioButtonId();

        String crust_choice = "";

        if (choice_id == -1) {

            //adding error message with toast
            Context context = getApplicationContext();
            CharSequence text = "Please make a selection" ;
            int duration = Toast.LENGTH_LONG ;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            if (choice_id == R.id.radioButton_thick) {

                crust_choice = "thick crust ";

                //setting final text and change image with button click
                pizzaResults.setText(crust_choice);

            } else {
                if (choice_id == R.id.radioButton2_thin) {
                    crust_choice = "thin_crust" ;
                    pizzaResults.setText(crust_choice);
                }

            } if (choice_id == R.id.radioButton3) {
                crust_choice = "deep dish" ;
                pizzaResults.setText(crust_choice);
            }

        }

        CheckBox hamCheckBox = findViewById(R.id.checkBox_ham);
        Boolean ham = hamCheckBox.isChecked();

        CheckBox baconCheckBox = findViewById(R.id.checkBox_bacon);
        Boolean bacon = baconCheckBox.isChecked();

        CheckBox onionsCheckBox = findViewById(R.id.checkBox_onions);
        Boolean onions = onionsCheckBox.isChecked();

        CheckBox peppersCheckBox = findViewById(R.id.checkBox_peppers);
        Boolean peppers = peppersCheckBox.isChecked();

        CheckBox pepperoniCheckBox = findViewById(R.id.checkBox_pepperoni);
        Boolean pepperoni = pepperoniCheckBox.isChecked();

        CheckBox mushroomsCheckBox = findViewById(R.id.checkBox_mushrooms);
        Boolean mushrooms = mushroomsCheckBox.isChecked();

        CheckBox sauageCheckBox = findViewById(R.id.checkBox_sausage);
        Boolean sausage = sauageCheckBox.isChecked();

        CheckBox cheeseCheckBox = findViewById(R.id.checkBox_cheese);
        Boolean extraCheese = cheeseCheckBox.isChecked();






        //create an Intent
        Intent intent = new Intent(this, RecievedPizzaActivity.class);

        //pass data
        intent.putExtra("sizeOfPizza", sizeOfPizza);
        intent.putExtra("sauceOnPizza", sauceOnPizza);

        //start intent
        startActivity(intent);

    }


}
