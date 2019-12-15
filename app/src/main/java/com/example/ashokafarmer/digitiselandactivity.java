package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.digitiselandmodels.Getnewlandformat;
import com.example.ashokafarmer.digitiselandmodels.Sendnewlandformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        //shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        //shared preferences ends



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
            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);
            Sendnewlandformat senddet=new Sendnewlandformat(landarea,landlocation,loclat,loclong);
            Call<Getnewlandformat> call=apiInterface.getnewland(currenttoken,senddet);
            call.enqueue(new Callback<Getnewlandformat>() {
                @Override
                public void onResponse(Call<Getnewlandformat> call, Response<Getnewlandformat> response) {
                    if(response.isSuccessful())
                    {
                        String message=response.body().getMessage();
                        String loc=response.body().getLand().getLat();
                        Toast.makeText(digitiselandactivity.this, "Done Uploading data for Verification", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(digitiselandactivity.this,homepage.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(digitiselandactivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Getnewlandformat> call, Throwable t) {
                    Toast.makeText(digitiselandactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        //server backend work ends
    }


}
