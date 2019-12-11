package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class profileactivity extends AppCompatActivity {


    private TextView pname,pemail,pphone,paadhar,pnoofpool,pallestiprofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        pname=findViewById(R.id.profileusername);
        pemail=findViewById(R.id.proemail);
        pphone=findViewById(R.id.prophn);
        paadhar=findViewById(R.id.proaadhar);
        pnoofpool=findViewById(R.id.pronumpools);
        pallestiprofit=findViewById(R.id.procurrbal);


        //shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        //shared preferences ends

        pname.setText(currentusername);
        pemail.setText(currentemail);
        paadhar.setText(currentaadhar);
        pphone.setText(currentph);




    }
}
