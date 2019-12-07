package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    //login clicking activity
    public void lgonclick(View view) {

        //if login successful
        startActivity(new Intent(this,homepage.class));


    }
    //login activity ends


    //register clicking activity
    public void lgregisclick(View view) {

        startActivity(new Intent(this,registrationactivity.class));


    }

    //register clicking activity ends

}
