package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ashokafarmer.allradyjoinedpools.Getalreadyjoinpoolformat;
import com.example.ashokafarmer.unknownclasses.Pool;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homepage extends AppCompatActivity implements pooladapter.onitemclicklistener{
    private Toolbar mtoolbar;

    public static String URL ;
    public static String NAME;
    public static String LOCATION ;
    public static String SELECTEDPOOLID;
    public static  String nofjoinedpools;
    //added extra datas for manipulating later(report is not initialised currently)
    public static final String EXTRA_REPORT = null;
    public  static String INVESTMENTS;

    private RecyclerView mRecyclerView;
    private pooladapter mpoolAdapter;
    private ArrayList<poolitems> mpoollist;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mtoolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        mRecyclerView=findViewById(R.id.joinownpoolsrecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(this);
        //get shared prefrences parameters

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        //



        //gps permission setup
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        //gps permission setup ends

        parseJSON();


        //NAVIGATION BAR DIRECTLY IMPORTED FROM MIKEPENZ
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.headernav)
                .addProfiles(
                        new ProfileDrawerItem().withName(currentusername).withEmail(currentemail).withIcon(getResources().getDrawable(R.drawable.profile_photo))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();



        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Profile");
        PrimaryDrawerItem item2=new PrimaryDrawerItem().withIdentifier(2).withName("My Lands");
        PrimaryDrawerItem item3=new PrimaryDrawerItem().withIdentifier(3).withName("Join/Init");
        PrimaryDrawerItem item4=new PrimaryDrawerItem().withIdentifier(4).withName("Digitize Land");
        PrimaryDrawerItem item5=new PrimaryDrawerItem().withIdentifier(5).withName("Pending Requests");
        PrimaryDrawerItem item6=new PrimaryDrawerItem().withIdentifier(6).withName("Rejected Requests");
        PrimaryDrawerItem item7=new PrimaryDrawerItem().withIdentifier(7).withName("Logout");




        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1, item2,item3 , item4, item5,item6,item7

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (position)
                        {
                            case 1:
                                //pass token for getting user details
                                Intent p1=new Intent(homepage.this,profileactivity.class);
                                p1.putExtra("nofpoolsjoined",nofjoinedpools);
                                //pass the token or required details
                                startActivity(p1);
                                break;
                            case 2:
                                startActivity(new Intent(homepage.this,ownlandprofit.class));
                                break;

                            case 3:
                                startActivity(new Intent(homepage.this,createpoolactivity.class));
                                break;

                            case 4:
                                startActivity(new Intent(homepage.this,digitiselandactivity.class));
                                break;

                            case 5:
                                startActivity(new Intent(homepage.this,pendingrequests.class));
                                break;
                            case 6:
                                Toast.makeText(homepage.this, "Rejected Requests", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(homepage.this,rejectedrequests.class));
                                break;
                            case 7:
                                Toast.makeText(homepage.this, "Logout", Toast.LENGTH_SHORT).show();
                                //during logout

                                //during logout activity ends
                                //clear shared preferences
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("Secrets", MODE_PRIVATE);
                                SharedPreferences.Editor editor=pref.edit();
                                editor.clear();
                                editor.apply();
                                //
                                startActivity(new Intent(homepage.this,MainActivity.class));
                                finish();
                                break;









                        }
                        return true;
                    }
                })
                .build();
        //NAVIGATION DRAWER ENDS







    }

    private void parseJSON() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        /*
        //JSON URL (NOW ITS A DUMMY)
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";;
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
                                mpoollist.add(new poolitems(mImageurl,poolname,area,location));
                            }
                            mpoolAdapter = new pooladapter(homepage.this, mpoollist);
                            mRecyclerView.setAdapter(mpoolAdapter);
                            mpoolAdapter.setOnItemClickListener(homepage.this);

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


        //retrofit json
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getalreadyjoinpoolformat> call=apiInterface.getalreadyjoinpool(currenttoken);
        call.enqueue(new Callback<Getalreadyjoinpoolformat>() {
            @Override
            public void onResponse(Call<Getalreadyjoinpoolformat> call, Response<Getalreadyjoinpoolformat> response) {
                if(response.isSuccessful())
                {
                    List<Pool> listpool=response.body().getPools();
                    nofjoinedpools= String.valueOf(listpool.size());//for passing it to profile intent for no of joined pools
                    for(int i=0;i<listpool.size();i++)
                    {
                        String id=listpool.get(i).getId();

                        String poolname = listpool.get(i).getName();
                        String mImageurl= listpool.get(i).getEngineerId();
                        String area = "Rs "+String.valueOf(listpool.get(i).getTotalInvestment());
                        String location = listpool.get(i).getLocation();
                        String Report=null;
                        mpoollist.add(new poolitems(id,mImageurl,poolname,area,location,Report));


                    }
                    mpoolAdapter = new pooladapter(homepage.this, mpoollist);
                    mRecyclerView.setAdapter(mpoolAdapter);
                    mpoolAdapter.setOnItemClickListener(homepage.this);
                }
            }

            @Override
            public void onFailure(Call<Getalreadyjoinpoolformat> call, Throwable t) {

            }
        });


    }


    @Override
    public void onItemClick(int position) {
        poolitems clickedItem=mpoollist.get(position);
        Intent detailIntent=new Intent(homepage.this,joinpoolexpand.class);

        /*Bundle extras = new Bundle();
        extras.putString(SELECTEDPOOLID,clickedItem.get_id());
        extras.putString(NAME,clickedItem.getPoolname());
        extras.putString(LOCATION,clickedItem.getLocation());
        extras.putString(INVESTMENTS,clickedItem.getArea());*/

        //imageurl==engineersid
        //Toast.makeText(this, clickedItem.getmImageurl(), Toast.LENGTH_SHORT).show();
        detailIntent.putExtra("ID_EXTRA", new String[] { clickedItem.get_id(),clickedItem.getPoolname(),clickedItem.getLocation(),clickedItem.getArea(),clickedItem.getmImageurl()});
        startActivity(detailIntent);

    }
}
