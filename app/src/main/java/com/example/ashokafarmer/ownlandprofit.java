package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ownlandprofit extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private mylandAdapter mlandAdapter;
    private ArrayList<mylanditem> mylandlist;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownlandprofit);
        toolbar=findViewById(R.id.ownlandtoolbar);
        mRecyclerView=findViewById(R.id.ownlandrecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mylandlist=new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showmylands();

    }

    private void showmylands() {


        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Mylandgetformat> call=apiInterface.getmylands(currenttoken);
        call.enqueue(new Callback<Mylandgetformat>() {
            @Override
            public void onResponse(Call<Mylandgetformat> call, Response<Mylandgetformat> response) {
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

                        mylandlist.add(new mylanditem(lid,"lat:"+llat+"\nlong:"+llong,larea,lloc,"",""));
                    }
                    mlandAdapter = new mylandAdapter(ownlandprofit.this, mylandlist);
                    mRecyclerView.setAdapter(mlandAdapter);

                }
                else
                {
                    Toast.makeText(ownlandprofit.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mylandgetformat> call, Throwable t) {
                Toast.makeText(ownlandprofit.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
