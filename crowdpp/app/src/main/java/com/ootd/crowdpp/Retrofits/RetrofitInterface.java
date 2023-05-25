package com.ootd.crowdpp.Retrofits;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RetrofitInterface {

    @GET("/")
    Call<DataModel> test_my_server();
}