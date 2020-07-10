package com.example.counter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView nDisplay;
    Button restart;
    public int sum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         startButton = findViewById(R.id.startB);
         nDisplay = findViewById(R.id.textView2);
         restart = findViewById(R.id.restart);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum = 0;
                nDisplay.setText((String.valueOf(sum)));
            }
        });

    }

    protected void  record(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, spanish);
        startActivityForResult(intent, 10);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            int res = getNumberFromResult(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) );


            if ( res == 1 ) {
                //FIRST_NUMBER = intFound;
                sum += 10;
                nDisplay.setText(String.valueOf(sum));
                record();
            }else if (res == 0){
                record();
            }
            else if(res == -1){
            }

        } else {
            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
        }
    }

    // method to loop through results trying to find a number
    private int getNumberFromResult(ArrayList<String> results) {

        for (String str : results) {

            if (str.equals("10")) {
                return 1;
            }else if(str.equals("para") || str.equals("stop")){
                return -1;
            }

        }

        return 0;
    }
    /*
    // method to convert string number to integer
    private boolean getIntNumberFromText(String strNum) {


        if( strNum.equals("ten") || strNum.equals("10")){
            return true;
        }
        return false;
    }
    */


}

