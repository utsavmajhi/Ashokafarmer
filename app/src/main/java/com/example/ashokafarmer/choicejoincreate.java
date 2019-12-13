package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class choicejoincreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicejoincreate);
    }



    //create a pool by farmer with his land
    public void createpoolclick(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        Intent intent=getIntent();
        String lidselect=intent.getStringExtra("ID_EXTRA");
        //Toast.makeText(this, pidselect, Toast.LENGTH_SHORT).show();
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Createreqsentformat credp=new Createreqsentformat(lidselect);
        Call<Createreqgetformat> call=apiInterface.createnewpool(currenttoken,credp);
        call.enqueue(new Callback<Createreqgetformat>() {
            @Override
            public void onResponse(Call<Createreqgetformat> call, Response<Createreqgetformat> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(choicejoincreate.this, response.message()+"! Successfully Initiated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(choicejoincreate.this,homepage.class));
                    finish();
                }
                else
                {
                    Toast.makeText(choicejoincreate.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Createreqgetformat> call, Throwable t) {
                Toast.makeText(choicejoincreate.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void joinexistingpoolclick(View view) {
        Intent i=new Intent(choicejoincreate.this,joinpoolactivity.class);
        Intent intent=getIntent();
        String lidselect=intent.getStringExtra("ID_EXTRA");
        i.putExtra("ID_EXTRA1",lidselect);
        startActivity(i);



    }
}
