package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ashokafarmer.rejectedrequestsmodel.Land;
import com.example.ashokafarmer.rejectedrequestsmodel.Rejectedgetformat;
import com.example.ashokafarmer.rejectedrequestsmodel.rejectedrequestAdapter;
import com.example.ashokafarmer.rejectedrequestsmodel.rejectedrequestitem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class rejectedrequests extends AppCompatActivity {

    private Toolbar mtoolbar;

    String temp="";

    private RecyclerView mRecyclerView;
    private rejectedrequestAdapter mrejectAdapter;
    private ArrayList<rejectedrequestitem> mrejectedlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejectedrequests);
        mRecyclerView=findViewById(R.id.rejectedrecycler);
        mrejectedlist=new ArrayList<>();
        mtoolbar=findViewById(R.id.rejectedreqtoolbar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);



        //bcakend

        Call<Rejectedgetformat> call=apiInterface.getrejectedrequests(currenttoken);
        call.enqueue(new Callback<Rejectedgetformat>() {
            @Override
            public void onResponse(Call<Rejectedgetformat> call, Response<Rejectedgetformat> response) {
                if(response.isSuccessful())
                {
                    List<Land> rejland=response.body().getLands();


                    if(rejland.isEmpty())
                    {
                        Toast.makeText(rejectedrequests.this, "No rejected requests", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for(int i=0;i<rejland.size();i++)
                        {
                            String lname= String.valueOf((i+1));
                            String lloc=rejland.get(i).getLocation();
                            String larea=rejland.get(i).getArea();
                            String llat=rejland.get(i).getLat();
                            String llong=rejland.get(i).getLong();

                            mrejectedlist.add(new rejectedrequestitem("lat:"+llat+"\nlong:"+llong,larea,lloc,"",""));

                          /*  temp+="* Land "+(i+1)+"\n"+"  Location: "+ rejland.get(i).getLocation()+"\n"+"  Area: "+rejland.get(i).getArea()+"\n"+"  Latitude: " +rejland.get(i).getLat()+"\n"+"  Longitude: "+rejland.get(i).getLong()+"\n"+"\n";

                            rejectedreqtxt.append(temp);
                            temp="";

                           */

                        }
                        mrejectAdapter=new rejectedrequestAdapter(rejectedrequests.this,mrejectedlist);
                        mRecyclerView.setAdapter(mrejectAdapter);
                    }

                }
                else
                {
                    Toast.makeText(rejectedrequests.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Rejectedgetformat> call, Throwable t) {
                Toast.makeText(rejectedrequests.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }


}

