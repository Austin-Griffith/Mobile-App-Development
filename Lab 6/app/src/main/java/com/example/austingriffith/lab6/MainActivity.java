package com.example.austingriffith.lab6;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    private String studentMessage;
    private ImageView skobuff;
    private TextView results;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //save current state
        outState.putString("msg", studentMessage);
        outState.putInt("imageid", R.drawable.skobuff);

        //always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //string for made up sentence
        results = findViewById(R.id.message);

        //setting image by id
        skobuff = findViewById(R.id.imageView2);

        //check for recovering the instance state
        if (savedInstanceState !=null){
            studentMessage = savedInstanceState.getString("msg");
            int image_id = savedInstanceState.getInt("imageid");

            results.setText(studentMessage);
            skobuff.setImageResource(image_id);
        }
    }


    public void enterButton(View view) {
        //find values
        results = findViewById(R.id.message) ;
        EditText name1 = findViewById(R.id.editText4);      //name1  == gpa
        EditText name2 = findViewById(R.id.editText3) ;     //name2 == major

        //set values
        String gpaValue = name1.getText().toString();
        String majorValue = name2.getText().toString();

        //spinner selection
        Spinner gpaType = findViewById(R.id.spinner);
        String gpaChoice = String.valueOf(gpaType.getSelectedItem());

        //text view
        results = findViewById(R.id.message) ;
        //image view
        skobuff = findViewById(R.id.imageView2) ;


        //radio buttons
        RadioGroup choice = findViewById(R.id.radioGroup);
        int choice_id = choice.getCheckedRadioButtonId();

        String yes_no_choice = "";

        if (choice_id == -1) {

            //adding error message with toast
            Context context = getApplicationContext();
            CharSequence text = "Please select yes or no to graduating radioButtons" ;
            int duration = Toast.LENGTH_LONG ;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            if (choice_id == R.id.radioButton1) {
                yes_no_choice = "You will be graduating on time" ;

                studentMessage = "You are majoring in " + majorValue + " and your " + gpaChoice + " gpa is " + gpaValue + "! " + yes_no_choice + "." ;

                //setting final text and change image with button click
                results.setText(studentMessage);
                skobuff.setImageResource(R.drawable.skobuff);

            } else {
                yes_no_choice = "You will not not graduating on time " ;
                studentMessage = "You are majoring in " + majorValue + " and your " + gpaChoice + " gpa is " + gpaValue + "! " + yes_no_choice + "." ;

                //setting final text and change image with button click
                results.setText(studentMessage);
                skobuff.setImageResource(R.drawable.skobuff);
            }
        }


    }

}
