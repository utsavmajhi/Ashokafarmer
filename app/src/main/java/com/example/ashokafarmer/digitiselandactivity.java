package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class digitiselandactivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private TextView location,larea,llocation;
    private Toolbar digitoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitiselandactivity);
        location=findViewById(R.id.currentlocgps);
        larea=findViewById(R.id.digiarea);
        llocation=findViewById(R.id.digilocation);
        digitoolbar=findViewById(R.id.digitoolbar);


        setSupportActionBar(digitoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    //get location activity by clicking ...from gps instance
    public void getlocationclick(View view) {



        gpsTracker = new GpsTracker(digitiselandactivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            String m= String.valueOf("Latitude="+latitude+"\n"+"Longitude="+longitude);
            location.setText(m);

        }else{
            gpsTracker.showSettingsAlert();
        }

    }


    //submit button for digitalise verfication of land
    public void digionclick(View view) {

        String loclat="";
        String loclong="";
        String landarea=larea.getText().toString().trim();
        String landlocation=llocation.getText().toString().trim();


        //gps tracker starts
        gpsTracker = new GpsTracker(digitiselandactivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            loclat= String.valueOf(latitude);
            loclong= String.valueOf(longitude);

        }else{
            gpsTracker.showSettingsAlert();
        }


        //gps tracker ends
        if(loclat.isEmpty()||loclong.isEmpty()||landarea.isEmpty()||landlocation.isEmpty())
        {
            Toast.makeText(digitiselandactivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        }
        else
        {

            Toast.makeText(digitiselandactivity.this, "Done Uploading data for Verification"+"\n", Toast.LENGTH_SHORT).show();
        }


        //server backend work
//hi


        //server backend work ends
    }

    //get location activity ends
}
