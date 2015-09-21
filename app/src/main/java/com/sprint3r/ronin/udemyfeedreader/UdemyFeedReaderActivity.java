package com.sprint3r.ronin.udemyfeedreader;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.JsonArray;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class UdemyFeedReaderActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udemy_feed_reader);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.course_feed_recyclerview);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        prepareItemsHTTP();
        //prepareItemsRetrofit();
    }

    private void prepareItemsRetrofit() {
        RetrofitApi retrofit;
        retrofit = new RetrofitApi();
        Call<CoursesDetail> feed = retrofit.getData();

        feed.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {
                CoursesDetail feedUdemy = response.body();
                recyclerViewAdapter.add(feedUdemy.results);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void prepareItemsHTTP() {
        HttpClientResponse httpClientResponse = null;
        try {
            httpClientResponse = new HttpClientResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonArray data = httpClientResponse.getJSONArrayForCardView();
        recyclerViewAdapter.add(data);
    }

}