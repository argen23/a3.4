package com.example.a34.network;

import com.example.a34.data.MockerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockerApi {


    @GET("posts")
    Call<List<MockerModel>> getPosts();

    @POST("posts")
    Call<MockerModel> createMocker(@Body MockerModel model);

    @PUT("posts/{id}")
    Call<MockerModel> update(@Path("id") String id, @Body MockerModel model);

    @DELETE("posts/{id}")
    Call<MockerModel> deleteMockerModel(
        @Path("id") Integer id
    );
    
}
