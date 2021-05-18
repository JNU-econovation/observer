package com.ecnv2021.observer_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    private Call<String> mCallNodeList;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NodesActivity.class));
            }
        });

        //setRetrofitInit();
        //callNodeList();
    }

    private void setRetrofitInit() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl("<Base URL>")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }

    private void callNodeList() {

        mCallNodeList = mRetrofitAPI.getNodeList();
        mCallNodeList.enqueue(mRetrofitCallback);

    }

    private Callback<String> mRetrofitCallback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            String result = response.body();
            NodeVO mMovieListVO = (NodeVO) mGson.fromJson(result, NodeVO.class);
            Log.d("result", result);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            t.printStackTrace();
        }
    };
}