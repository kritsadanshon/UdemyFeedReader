package com.sprint3r.ronin.udemyfeedreader;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.course_feed_recyclerview);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        prepareItems();
    }

    private void prepareItems() {
        retrofitApi retrofit;
        retrofit = new retrofitApi();
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

}