package com.example.covid_19eg.Network;

import com.example.covid_19eg.model.properties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class propertiesClient {
    private static final String BASE_URL ="https://corona.lmao.ninja/";
    private ApiInterface apiInterface;
    private static propertiesClient INSTANCE;
    public propertiesClient(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface =retrofit.create(ApiInterface.class);
    }

    public static propertiesClient getINSTANCE(){
        if (INSTANCE==null){
            return INSTANCE=new propertiesClient();
        }
        return INSTANCE;
    }

    public Call<properties> getproperties(){
        return apiInterface.getproperties();
    }


}
