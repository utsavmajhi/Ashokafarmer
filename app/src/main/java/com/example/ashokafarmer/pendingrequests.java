package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.pendingrequestsmodels.Land;
import com.example.ashokafarmer.pendingrequestsmodels.Pendinggetformat;
import com.example.ashokafarmer.pendingrequestsmodels.pendingrequestAdapter;
import com.example.ashokafarmer.pendingrequestsmodels.pendingrequestitem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pendingrequests extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private pendingrequestAdapter mtranAdapter;
    private ArrayList<pendingrequestitem> mpendinglist;
    private TextView pendingreq;
    String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingrequests);
        mRecyclerView=findViewById(R.id.pendingrecycler);
        mpendinglist=new ArrayList<>();
        toolbar=findViewById(R.id.pendingtoolbar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        //shared preferences ends
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Pendinggetformat> call=apiInterface.getpendingrequests(currenttoken);
        call.enqueue(new Callback<Pendinggetformat>() {
            @Override
            public void onResponse(Call<Pendinggetformat> call, Response<Pendinggetformat> response) {
                if(response.isSuccessful())
                {

                    List<Land> la=response.body().getLands();
                    if(la.isEmpty())
                    {

                    }
                    else {
                        for (int i = 0; i < la.size(); i++) {

                                String lname= String.valueOf((i+1));
                                String lloc=la.get(i).getLocation();
                                String larea=la.get(i).getArea();
                                String llat=la.get(i).getLat();
                                String llong=la.get(i).getLong();
                                mpendinglist.add(new pendingrequestitem("lat:"+llat+"\nlong:"+llong,larea,lloc,"",""));//set lat and long lateron

                            /*temp += "* Land " + (i + 1) + "\n" + "  Location: " + la.get(i).getLocation() + "\n" + "  Area: " + la.get(i).getArea() + "\n" + "  Latitude: " + la.get(i).getLat() + "\n" + "  Longitude: " + la.get(i).getLong() + "\n" + "\n";
                            pendingreq.append(temp);
                            temp = "";

                             */
                        }
                        mtranAdapter=new pendingrequestAdapter(pendingrequests.this,mpendinglist);
                        mRecyclerView.setAdapter(mtranAdapter);
                    }
                }
                else
                {
                    Toast.makeText(pendingrequests.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Pendinggetformat> call, Throwable t) {
                Toast.makeText(pendingrequests.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
