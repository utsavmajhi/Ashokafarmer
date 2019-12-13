package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ashokafarmer.homepage.SELECTEDPOOLID;


public class joinpoolexpand extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView poolname,poolinvest,poollocation,poolengineer,poolreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinpoolexpand);

        toolbar=findViewById(R.id.jpexptoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poolname=findViewById(R.id.plname);
        poollocation=findViewById(R.id.pllocation);
        poolinvest=findViewById(R.id.poolfundsrece);
        poolreport=findViewById(R.id.jpreporttxt);
        poolengineer=findViewById(R.id.jpdesp);


        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        String selectedpoolname=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoollocation=intent.getStringArrayExtra("ID_EXTRA")[2];
        String selectedpoolinvestments=intent.getStringArrayExtra("ID_EXTRA")[3];
        String selectedpoolengineerid=intent.getStringArrayExtra("ID_EXTRA")[4];

        poolname.setText(selectedpoolname);
        poollocation.setText(selectedpoollocation);
        poolinvest.setText(selectedpoolinvestments);


    }
}
