package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView loginem;
    private TextView loginpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking ui

        loginem=findViewById(R.id.lgemail);
        loginpass=findViewById(R.id.lgpass);

        //linking ui ends


    }



    //login clicking activity
    public void lgonclick(View view) {
        String lemail=loginem.getText().toString().trim();
        String lpass=loginpass.getText().toString().trim();

        //checklogincredentials backend



        //checklogincredentials backend ends



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
