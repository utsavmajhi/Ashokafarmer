package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.loginmodels.Logingetdet;
import com.example.ashokafarmer.loginmodels.Loginsendformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        SharedPreferences sharedPreferences2=getSharedPreferences("Secrets",MODE_PRIVATE);
        String tk=sharedPreferences2.getString("token","");
        if(tk!="")
        {
            startActivity(new Intent(this,homepage.class));
            finish();
        }
        //linking ui ends


    }


    //login clicking activity
    public void lgonclick(View view) {
        String lemail=loginem.getText().toString().trim();
        String lpass=loginpass.getText().toString().trim();

        //checklogincredentials backend
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/users/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Loginsendformat lgdet=new Loginsendformat(lemail,lpass);

        Call<Logingetdet> call=apiInterface.sendlogincredentials(lgdet);
        call.enqueue(new Callback<Logingetdet>() {
            @Override
            public void onResponse(Call<Logingetdet> call, Response<Logingetdet> response) {
                if(response.isSuccessful())
                {
                    String token=response.body().getToken();
                    String username=response.body().getUser().getName();
                    String aadhar=response.body().getUser().getAadhaar();
                    String email=response.body().getUser().getEmail();
                    String phone=response.body().getUser().getPhone();
                    String id=response.body().getUser().getId();


                    SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString("token",token);
                    editor.putString("username",username);
                    editor.putString("aadhar",aadhar);
                    editor.putString("email",email);
                    editor.putString("phone",phone);
                    editor.putString("id",id);

                    editor.apply();

                    //if login successful
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,homepage.class));


                }
                else
                {
                    Toast.makeText(MainActivity.this,"Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Logingetdet> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        //checklogincredentials backend ends





    }
    //login activity ends


    //register clicking activity
    public void lgregisclick(View view) {

        startActivity(new Intent(this,registrationactivity.class));

    }

    //register clicking activity ends




}
