package com.ecnv2021.observer_android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/nodes")
    Call<NodeVO> getNodeList();

    @GET("/nodes/{id}/statistics")
    Call<StatisticsVO> getStatisticsList(@Path("id") String id);

    @GET("/edges")
    Call<EdgeVO> getEdgeList();


    //테스트용
    // @GET( EndPoint-자원위치(URI) )
    @GET("posts/{post}")
    Call<PostResult> getPosts(@Path("post") String post);
}
