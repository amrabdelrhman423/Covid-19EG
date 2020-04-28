package com.example.covid_19eg.Network;

import com.example.covid_19eg.models.properties;


import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("countries/egypt")
    public Call<properties> getproperties(

    );

}
