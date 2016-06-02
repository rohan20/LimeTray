package com.example.rohan.limetray;

import com.example.rohan.limetray.TransactionAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rohan on 02-Jun-16.
 */
public class RESTAdapter {

    Retrofit retrofit;
    TransactionAPI api;

    public RESTAdapter(String base_url){

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(TransactionAPI.class);

    }

    public TransactionAPI getApi(){
        return api;
    }

}
