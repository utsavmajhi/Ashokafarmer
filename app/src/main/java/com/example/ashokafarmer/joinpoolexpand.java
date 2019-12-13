package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ashokafarmer.homepage.SELECTEDPOOLID;


public class joinpoolexpand extends AppCompatActivity {

    private Toolbar toolbar;
    String timedate="";
    String temp="";
    private TextView poolname,poolinvest,poollocation,poolengineer,poolreport,plprofits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinpoolexpand);

        toolbar=findViewById(R.id.jpexptoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poolname=findViewById(R.id.plname);
        plprofits=findViewById(R.id.plestprofit);
        poollocation=findViewById(R.id.pllocation);
        poolinvest=findViewById(R.id.poolfundsrece);
        poolreport=findViewById(R.id.jpreporttxt);
        poolengineer=findViewById(R.id.jpdesp);
        poolreport.setMovementMethod(new ScrollingMovementMethod());

        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
        String selectedpoolname=intent.getStringArrayExtra("ID_EXTRA")[1];
        String selectedpoollocation=intent.getStringArrayExtra("ID_EXTRA")[2];
        String selectedpoolinvestments=intent.getStringArrayExtra("ID_EXTRA")[3];
        String selectedpoolengineerid=intent.getStringArrayExtra("ID_EXTRA")[4];

        poolname.setText(selectedpoolname);
        poollocation.setText(selectedpoollocation);
        poolinvest.setText(selectedpoolinvestments);

        //for reports and previous profits
        infodet();

    }

    private void infodet() {

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        Intent intent=getIntent();
        String selectedpoolid=intent.getStringArrayExtra("ID_EXTRA")[0];
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


                    //estimated profits
                    plprofits.setText(ppreprofit);


                    //for reports
                    List<Report> allrepo=response.body().getReports();
                    for(int i=(allrepo.size()-1);i>=0;i--)
                    {
                        double time=allrepo.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);
                        temp+=timedate+"\nTitle:"+allrepo.get(i).getTitle()+"\n"+"Description:"+allrepo.get(i).getDescription()+"\n"+"\n"+"\n";
                       poolreport.append(temp);
                        timedate="";
                        temp="";

                    }

                }
                else
                {
                    Toast.makeText(joinpoolexpand.this, "Error:"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Poolinfogetformat> call, Throwable t) {
                Toast.makeText(joinpoolexpand.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
