package com.example.ashokafarmer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createpoolactivity extends AppCompatActivity implements notpooledlandAdapter.onitemclicklistener{

    private Toolbar crtoolbar;

    private RecyclerView mRecyclerView;
    private notpooledlandAdapter mnotpoollandAdapter;
    private ArrayList<notpooleditem> mnotpoollandlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpoolactivity);
        mRecyclerView=findViewById(R.id.yourlandrecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mnotpoollandlist=new ArrayList<>();
        crtoolbar=findViewById(R.id.crpooltoolbar);
        setSupportActionBar(crtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showhisnotpooledlands();

    }

    private void showhisnotpooledlands() {
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Notpoollandgetformat> call=apiInterface.getmynotpooled(currenttoken);
        call.enqueue(new Callback<Notpoollandgetformat>() {
            @Override
            public void onResponse(Call<Notpoollandgetformat> call, Response<Notpoollandgetformat> response) {
                if(response.isSuccessful())
                {
                    List<Land> la=response.body().getLands();
                    for(int i=0;i<la.size();i++)
                    {
                        String lid=la.get(i).getId();
                        String llat=la.get(i).getLat();
                        String llong=la.get(i).getLong();
                        String lloc=la.get(i).getLocation();
                        String larea=la.get(i).getArea();

                       mnotpoollandlist.add(new notpooleditem(lid,"lat:"+llat+"\nlong:"+llong,larea,lloc,"",""));

                    }
                    mnotpoollandAdapter = new notpooledlandAdapter(createpoolactivity.this, mnotpoollandlist);
                    mRecyclerView.setAdapter(mnotpoollandAdapter);
                    mnotpoollandAdapter.setOnItemClickListener(createpoolactivity.this);

                }
                else
                {
                    Toast.makeText(createpoolactivity.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Notpoollandgetformat> call, Throwable t) {
                Toast.makeText(createpoolactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onItemClick(int position) {
        notpooleditem clickedItem=mnotpoollandlist.get(position);
        String pooliddselected=clickedItem.get_id();
        Intent i=new Intent(createpoolactivity.this,choicejoincreate.class);
        i.putExtra("ID_EXTRA",pooliddselected);
        startActivity(i);
       // Toast.makeText(this, pooliddselected, Toast.LENGTH_SHORT).show();

    }
}
