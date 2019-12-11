package com.example.ashokafarmer;

import com.example.ashokafarmer.digitiselandmodels.Getnewlandformat;
import com.example.ashokafarmer.digitiselandmodels.Sendnewlandformat;
import com.example.ashokafarmer.loginmodels.Logingetdet;
import com.example.ashokafarmer.loginmodels.Loginsendformat;
import com.example.ashokafarmer.pendingrequestsmodels.Pendinggetformat;
import com.example.ashokafarmer.registrationmodels.Registgetformat;
import com.example.ashokafarmer.registrationmodels.Registsendformat;
import com.example.ashokafarmer.rejectedrequestsmodel.Rejectedgetformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    //signin or login route
    @POST("signin")
    Call<Logingetdet> sendlogincredentials(@Body Loginsendformat loginsendformat);
    //signin or login route ends

    //registration
    @POST("signup")
    Call<Registgetformat> sendregiscredentials(@Body Registsendformat registsendformat);


    @GET("farmers/landsadminpending")
    Call<Pendinggetformat> getpendingrequests(@Header("Authorization") String header);

    @GET("farmers/landsadminrejected")
    Call<Rejectedgetformat> getrejectedrequests(@Header("Authorization") String header);

    //all pools he joined till now
    @GET("farmers/pools")
    Call<Getalreadyjoinpoolformat> getalreadyjoinpool(@Header("Authorization") String header);

    @POST("farmers/lands")
    Call<Getnewlandformat> getnewland(@Header("Authorization") String header, @Body Sendnewlandformat sendnewlandformat);

}
