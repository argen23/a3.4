package com.example.a34.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static MockerApi api;

    public static MockerApi getApi(){
        if (api == null){
            api  = provideRetrofit();
        }
        return api;
    }

    private static MockerApi provideRetrofit (){
        return new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MockerApi.class);

    }

}
