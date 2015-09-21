package com.sprint3r.ronin.udemyfeedreader;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.JsonArray;

import java.io.IOException;


public class UdemyFeedReaderActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            prepareItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareItems() throws IOException{
        HttpClientResponse httpClientResponse = new HttpClientResponse();
        JsonArray data = httpClientResponse.getJSONArrayForCardView();

            for (Integer item = 0; item < data.size(); item++) {
                String courseTitle = "title";
                String courseUrl = "url";
                recyclerViewAdapter.add(
                        recyclerViewAdapter.getItemCount(),
                        data.get(item).getAsJsonObject().get(courseTitle).getAsString(),
                        data.get(item).getAsJsonObject().get(courseUrl).getAsString()
                );
            }


    }

}