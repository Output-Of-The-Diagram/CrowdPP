package com.ootd.crowdpp.Retrofits;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface RetrofitInterface {

    @GET("/")
    Call<DataModel> test_my_server();

    @FormUrlEncoded
    @POST("/login/")
    Call<Result> login(
            @Field("id") String id,
            @Field("pw") String pw
    );

    @FormUrlEncoded
    @POST("/signup/")
    Call<Result> signup(
            @Field("id") String id,
            @Field("pw") String pw,
            @Field("name") String name,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("/kickmember/")
    Call<Result> kickmember(
            @Field("userId") String userId,
            @Field("crowdId") int crowdId
    );

    @FormUrlEncoded
    @POST("/deletecrowd/")
    Call<Result> deletecrowd(
            @Field("crowdId") int crowdId
    );

    @FormUrlEncoded
    @POST("/applycrowd/")
    Call<Result> applycrowd(
            @Field("userId") String userId,
            @Field("crowdId") int crowdId
    );

    @FormUrlEncoded
    @POST("/checkId/")
    Call<Result> checkId(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("/makecrowd/")
    Call<Result> makecrowd(
        @Field("crowdName") String crowdName,
        @Field("crowdExplain") String crowdExplain,
        @Field("crowdImage") String crowdImage,
        @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("/isleader/")
    Call<Result> makecrowd(
        @Field("userId") String userId,
        @Field("crowdId") int crowdId
    );

    @GET("/allcrowd/")
    Call<ArrayList<CrowdModel>> getAllCrowd();

    @GET("/allmember/{crowdId}")
    Call<ArrayList<UserModel>> getAllMember(
        @Path("crowdId") int crowdId
    );
}