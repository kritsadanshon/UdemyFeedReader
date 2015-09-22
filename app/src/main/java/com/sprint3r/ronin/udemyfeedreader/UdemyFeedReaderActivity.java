package com.sprint3r.ronin.udemyfeedreader;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.concurrent.ExecutionException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class UdemyFeedReaderActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private LoadImageFromURL loadImageFromURL;

    private int current_page = 1;
    private String courseTitle = "title";
    private String courseImage = "image_480x270";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udemy_feed_reader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.course_feed_recyclerview);

        loadData(current_page);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData(current_page);
            }
        });
    }

    private void loadData(int current_page) {
        loadItemToRecyclerViewAdapter(current_page);
    }

    private void loadMoreData(int current_page) {
        loadItemToRecyclerViewAdapter(current_page);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void loadItemToRecyclerViewAdapter(int current_page) {
        RetrofitApi retrofit;
        retrofit = new RetrofitApi();
        Call<CoursesDetail> feed = retrofit.getData(Integer.toString(current_page));

        feed.enqueue(new Callback<CoursesDetail>() {
            @Override
            public void onResponse(Response<CoursesDetail> response) {
                CoursesDetail feedUdemy = response.body();

                for (int index = 0; index < feedUdemy.results.size(); index++) {
                    String urlImage = feedUdemy.results.get(index).getAsJsonObject()
                            .get(courseImage).getAsString();
                    loadImageFromURL = new LoadImageFromURL(urlImage);
                    AsyncTask<String, Void, Bitmap> execute = loadImageFromURL.execute();

                    try {
                        recyclerViewAdapter.add(recyclerViewAdapter.getItemCount(),
                                feedUdemy.results.get(index).getAsJsonObject()
                                        .get(courseTitle).getAsString(),
                                execute.get()
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}


//Toast.makeText(this, "Go to page : " + Integer.toString(current_page), Toast.LENGTH_SHORT).show();
