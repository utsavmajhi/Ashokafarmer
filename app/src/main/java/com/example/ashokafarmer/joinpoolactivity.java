package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ashokafarmer.joinpoolrecyclerdata.Getallpoolformat;
import com.example.ashokafarmer.joinpoolrecyclerdata.Pool;
import com.example.ashokafarmer.joinpoolrecyclerdata.pooljoinadapter;
import com.example.ashokafarmer.joinpoolrecyclerdata.pooljoinitems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class joinpoolactivity extends AppCompatActivity implements pooljoinadapter.onitemclicklistener{
    private RecyclerView mRecyclerView;
    private pooljoinadapter mpoolAdapter;
    private ArrayList<pooljoinitems> mpoollist;

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinpoolactivity);
        mRecyclerView=findViewById(R.id.joinrecyclerview);
        mRecyclerView.setHasFixedSize(true);
         toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();


        parseJSON();
    }

    private void parseJSON() {


        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        /*
        // JSON URL (NOW ITS A DUMMY)
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //getting values from fake json file
                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String poolname = hit.getString("user");
                                String mImageurl= hit.getString("tags");
                                String area = hit.getString("imageHeight");
                                String location = hit.getString("views");
                                String Report=hit.getString("userImageURL");

                                //remember maintain the same order as in poolitemslist.java
                                mpoollist.add(new pooljoinitems(mImageurl,poolname,area,location));
                            }
                            mpoolAdapter = new pooljoinadapter(joinpoolactivity.this, mpoollist);
                            mRecyclerView.setAdapter(mpoolAdapter);
                            mpoolAdapter.setOnItemClickListener(joinpoolactivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

        */

        //backend retrofit
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getallpoolformat> call=apiInterface.getallpools(currenttoken);
        call.enqueue(new Callback<Getallpoolformat>() {
            @Override
            public void onResponse(Call<Getallpoolformat> call, Response<Getallpoolformat> response) {
                if(response.isSuccessful())
                {
                    List<Pool> allpoollist=response.body().getPools();
                    for(int i=0;i<allpoollist.size();i++)
                    {
                        String pid=allpoollist.get(i).getId();
                        String ploc=allpoollist.get(i).getLocation();
                        String pname=allpoollist.get(i).getName();
                        String pdesc=allpoollist.get(i).getDescription();
                        String ptotinvest= String.valueOf(allpoollist.get(i).getTotalInvestment());
                        String pprofits= String.valueOf(allpoollist.get(i).getPrevProfits());
                        mpoollist.add(new pooljoinitems(pid,pname,"",ploc,pdesc,ptotinvest,pprofits));

                    }
                    mpoolAdapter = new pooljoinadapter(joinpoolactivity.this, mpoollist);
                    mRecyclerView.setAdapter(mpoolAdapter);
                    mpoolAdapter.setOnItemClickListener(joinpoolactivity.this);
                }
                else
                {
                    Toast.makeText(joinpoolactivity.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getallpoolformat> call, Throwable t) {
                Toast.makeText(joinpoolactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemClick(int position) {

        Intent intent=getIntent();
        String selectedlandid=intent.getStringExtra("ID_EXTRA1");


        Intent detailIntent=new Intent(joinpoolactivity.this,detailactivity.class);
        pooljoinitems clickedItem=mpoollist.get(position);
        detailIntent.putExtra("ID_EXTRA", new String[] {clickedItem.get_id(),selectedlandid});//poolid and land id send to nexr intent
        startActivity(detailIntent);


    }
}
