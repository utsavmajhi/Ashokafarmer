package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class digitiselandactivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private TextView location,laddress,lownername;
    private Toolbar digitoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitiselandactivity);
        location=findViewById(R.id.currentlocgps);
        laddress=findViewById(R.id.digilandaddress);
        lownername=findViewById(R.id.digiownname);
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

        String loc="";
        String landadd=laddress.getText().toString();
        String ownername=lownername.getText().toString();


        //gps tracker starts
        gpsTracker = new GpsTracker(digitiselandactivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            loc= String.valueOf("Latitude="+latitude+"\n"+"Longitude="+longitude);

        }else{
            gpsTracker.showSettingsAlert();
        }


        //gps tracker ends
        if(loc.isEmpty()||landadd.isEmpty()||ownername.isEmpty())
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
