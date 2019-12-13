package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.joinpoolrequestbyfarmermodel.Getformatjoinpool;
import com.example.ashokafarmer.joinpoolrequestbyfarmermodel.Sendformatjoinpool;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailactivity extends AppCompatActivity {

    private Toolbar toolbar;
    String timedate="";
    String temp="";
    private TextView plname,plloc,pltotinvests,pldescription,plreport,plprofit,plarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        plname=findViewById(R.id.jpnametxt);
        plloc=findViewById(R.id.jpoollocatxt);
        pltotinvests=findViewById(R.id.jptotinvest);
        pldescription=findViewById(R.id.jpdesp);
        plreport=findViewById(R.id.jpreporttxt);
        plprofit=findViewById(R.id.jpprofit);
        plarea=findViewById(R.id.jpareatxt);
        plreport.setMovementMethod(new ScrollingMovementMethod());
        toolbar=findViewById(R.id.detailtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infopool();


    }


    //for all information of the pool
    private void infopool() {
        Intent intent=getIntent();
        String selectedlandid=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");


        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Poolinfogetformat> call=apiInterface.getdetailsofpool(currenttoken,selectedpoolid);
        call.enqueue(new Callback<Poolinfogetformat>() {
            @Override
            public void onResponse(Call<Poolinfogetformat> call, Response<Poolinfogetformat> response) {
                if(response.isSuccessful())
                {
                    String pname=response.body().getPool().getName();
                    String ptotinvest= String.valueOf(response.body().getPool().getTotalInvestment());
                    String ppreprofit= String.valueOf(response.body().getPool().getPrevProfits());
                    String pdesc=response.body().getPool().getDescription();
                    String plocation=response.body().getPool().getLocation();
                    //set values to ui

                    plname.setText(pname);
                    pltotinvests.setText(ptotinvest);
                    plprofit.setText(ppreprofit);
                    pldescription.setText(pdesc);
                    plloc.setText(plocation);

                    //for reports
                    List<Report> allrepo=response.body().getReports();
                    for(int i=(allrepo.size()-1);i>=0;i--)
                    {
                        double time=allrepo.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);
                        temp+=timedate+"\nTitle:"+allrepo.get(i).getTitle()+"\n"+"Description:"+allrepo.get(i).getDescription()+"\n"+"\n"+"\n";
                        plreport.append(temp);
                        timedate="";
                        temp="";

                    }

                }
                else
                {
                    Toast.makeText(detailactivity.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Poolinfogetformat> call, Throwable t) {
                Toast.makeText(detailactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //join pool clicking activity
    public void joinclick(View view) {
        //backend
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");
        Intent intent=getIntent();
        String selectedlandid=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];

        //Toast.makeText(this, selectedlandid, Toast.LENGTH_SHORT).show();
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Sendformatjoinpool dettosend=new Sendformatjoinpool(selectedlandid,selectedpoolid);
        Call<Getformatjoinpool> call=apiInterface.joinpool(currenttoken,dettosend);
        call.enqueue(new Callback<Getformatjoinpool>() {
            @Override
            public void onResponse(Call<Getformatjoinpool> call, Response<Getformatjoinpool> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(detailactivity.this, response.body().getMessage()+"Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(detailactivity.this,homepage.class));
                    finish();
                }
                else
                {
                    Toast.makeText(detailactivity.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getformatjoinpool> call, Throwable t) {
                Toast.makeText(detailactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    //join pool activity ends
}
