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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udemy_feed_reader);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);

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

                for (Integer item = 0; item < feedUdemy.results.size(); item++) {
                    String courseTitle = "title";
                    String courseUrl = "url";
                    recyclerViewAdapter.add(
                            recyclerViewAdapter.getItemCount(),
                            feedUdemy.results.get(item).getAsJsonObject().get(courseTitle).getAsString(),
                            feedUdemy.results.get(item).getAsJsonObject().get(courseUrl).getAsString()
                    );
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }




}