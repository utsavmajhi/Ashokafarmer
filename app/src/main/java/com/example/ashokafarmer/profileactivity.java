package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profileactivity extends AppCompatActivity {


    private TextView pname,pemail,pphone,paadhar,pnoofpool,pallestiprofit,totarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        Intent intent=getIntent();
        String nofjpools=intent.getStringExtra("nofpoolsjoined");


        pname=findViewById(R.id.profileusername);
        pemail=findViewById(R.id.proemail);
        pphone=findViewById(R.id.prophn);
        paadhar=findViewById(R.id.proaadhar);
        pnoofpool=findViewById(R.id.pronumpools);
        pallestiprofit=findViewById(R.id.procurrbal);
        totarea=findViewById(R.id.totalareaofland);


        //shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        //shared preferences ends

        pname.setText(currentusername);
        pemail.setText(currentemail);
        paadhar.setText(currentaadhar);
        pphone.setText(currentph);
        if(!(nofjpools ==null))
        {
           pnoofpool.setText(nofjpools);
        }



    }
}
