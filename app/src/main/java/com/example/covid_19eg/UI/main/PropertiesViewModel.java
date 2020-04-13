package com.example.covid_19eg.UI.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19eg.model.properties;
import com.example.covid_19eg.Network.propertiesClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertiesViewModel extends ViewModel {

    public MutableLiveData<properties> propertiesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> properties = new MutableLiveData<>();


    public void getproperties() {
        propertiesClient.getINSTANCE().getproperties().enqueue(new Callback<properties>() {
            @Override
            public void onResponse(Call<properties> call, Response<properties> response) {
                propertiesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<properties> call, Throwable t) {
                properties.setValue("errr");
            }
        });
    }



}
