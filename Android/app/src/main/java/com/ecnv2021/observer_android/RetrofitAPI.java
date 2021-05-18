package com.ecnv2021.observer_android;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("/nodes/{id}")
    Call<String> getNodeList();
}
