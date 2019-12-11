package com.example.ashokafarmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokafarmer.rejectedrequestsmodel.Land;
import com.example.ashokafarmer.rejectedrequestsmodel.Rejectedgetformat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class rejectedrequests extends AppCompatActivity {

    private Toolbar mtoolbar;
    private TextView rejectedreqtxt;
    String temp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejectedrequests);
        mtoolbar=findViewById(R.id.rejectedreqtoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rejectedreqtxt=findViewById(R.id.reqrejecttxt);
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
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
                        rejectedreqtxt.setText("No Rejected Requests");
                    }
                    else {
                        for(int i=0;i<rejland.size();i++)
                        {
                            temp+="* Land "+(i+1)+"\n"+"  Location: "+ rejland.get(i).getLocation()+"\n"+"  Area: "+rejland.get(i).getArea()+"\n"+"  Latitude: " +rejland.get(i).getLat()+"\n"+"  Longitude: "+rejland.get(i).getLong()+"\n"+"\n";

                            rejectedreqtxt.append(temp);
                            temp="";

                        }
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

