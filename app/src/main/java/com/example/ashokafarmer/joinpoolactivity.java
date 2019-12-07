package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashokafarmer.joinpoolrecyclerdata.pooljoinadapter;
import com.example.ashokafarmer.joinpoolrecyclerdata.pooljoinitems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class joinpoolactivity extends AppCompatActivity implements pooljoinadapter.onitemclicklistener{
    private RecyclerView mRecyclerView;
    private pooljoinadapter mpoolAdapter;
    private ArrayList<pooljoinitems> mpoollist;
    private RequestQueue mRequestQueue;

    public static final String EXTRA_URL="imageurl";
    public static final String EXTRA_NAME="user";
    public static final String EXTRA_LOCATION="views";
    //added extra datas for manipulating later(report is not initialised currently)
    public static final String EXTRA_REPORT="imageurl";
    public  static final String EXTRA_AREA="imageHeight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinpoolactivity);
        mRecyclerView=findViewById(R.id.joinrecyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {

        //JSON URL (NOW ITS A DUMMY)
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

    }

    @Override
    public void onItemClick(int position) {

        Intent detailIntent=new Intent(joinpoolactivity.this,detailactivity.class);
        pooljoinitems clickedItem=mpoollist.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageurl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getPoolname());
        detailIntent.putExtra(EXTRA_AREA,clickedItem.getArea());
        detailIntent.putExtra(EXTRA_LOCATION,clickedItem.getLocation());

        startActivity(detailIntent);


    }
}
