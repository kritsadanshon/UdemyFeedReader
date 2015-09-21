package com.sprint3r.ronin.udemyfeedreader;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class UdemyFeedReaderActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    retrofitApi retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udemy_feed_reader);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        try {
            prepareItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareItems() throws IOException {
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
                    myRecyclerViewAdapter.add(
                            myRecyclerViewAdapter.getItemCount(),
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