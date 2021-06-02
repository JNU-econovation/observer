package com.ecnv2021.observer_android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/nodes")
    Call<NodeVO> getNodeList();

    @GET("/nodes/{serviceName}/statistics")
    Call<StatisticsVO> getStatisticsList(@Path("serviceName") String id);

    @GET("/edges")
    Call<EdgeVO> getEdgeList();

}
