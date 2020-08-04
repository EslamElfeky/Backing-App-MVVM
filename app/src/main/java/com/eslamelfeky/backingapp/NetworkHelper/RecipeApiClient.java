package com.eslamelfeky.backingapp.NetworkHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeApiClient {
  private static   Retrofit retrofit=null;


    public static Retrofit getClient() {

        if (retrofit == null) {
           return retrofit = retrofit=new Retrofit.Builder()
                    .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        else{ return retrofit; }
    }

    }

