package com.example.austingriffith.lab_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayBoo(View view)
    {
        EditText name = findViewById(R.id.editText);
        String nameValue = name.getText().toString();
        TextView booText = findViewById(R.id.message);
        booText.setText("Your favorite team is " + nameValue + "!");
        ImageView mlb = findViewById(R.id.imageView);
        mlb.setImageResource(R.drawable.mlb);

    }
}
