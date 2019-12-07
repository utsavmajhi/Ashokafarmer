package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class digitiselandactivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private TextView location,lname,lownername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitiselandactivity);
        location=findViewById(R.id.currentlocgps);
        lname=findViewById(R.id.digilname);
        lownername=findViewById(R.id.digiownname);
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

        String loc= (String) location.getText();
        String landname=lname.getText().toString();
        String ownername=lownername.getText().toString();

        Toast.makeText(digitiselandactivity.this, "Done Uploading data for Verification", Toast.LENGTH_SHORT).show();

        //server backend work
//hi


        //server backend work ends
    }

    //get location activity ends
}
