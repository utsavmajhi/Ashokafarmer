package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.registrationmodels.Registgetformat;
import com.example.ashokafarmer.registrationmodels.Registsendformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registrationactivity extends AppCompatActivity {

    private TextView nm,adhr,phn,emai,pass;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationactivity);
        nm=findViewById(R.id.regisname);
        adhr=findViewById(R.id.regisaadhar);
        phn=findViewById(R.id.regisphn);
        emai=findViewById(R.id.regisem);
        pass=findViewById(R.id.regispass);
        mtoolbar=findViewById(R.id.registoolbar);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void regisubclick(View view) {

        //backend starts

        String name=nm.getText().toString();
        String password=pass.getText().toString();
        String aadhar=adhr.getText().toString();
        String phone=phn.getText().toString();
        String email=emai.getText().toString();
        String usertype="1";
        if(name.isEmpty()||password.isEmpty()||aadhar.isEmpty()||phone.isEmpty()||email.isEmpty())
        {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //backend starts

            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/users/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);

            Registsendformat regis=new Registsendformat(email,password,name,aadhar,1,phone);
            Call<Registgetformat> call=apiInterface.sendregiscredentials(regis);
            call.enqueue(new Callback<Registgetformat>() {
                @Override
                public void onResponse(Call<Registgetformat> call, Response<Registgetformat> response) {
                    if(response.isSuccessful())
                    {
                        assert response.body() != null;
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
                        Toast.makeText(registrationactivity.this, "Successfully created your account", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(registrationactivity.this,homepage.class));
                    }
                    else
                    {
                        Toast.makeText(registrationactivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Registgetformat> call, Throwable t) {
                    Toast.makeText(registrationactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            //backends ends

        }






    }
}
