package com.sprint3r.ronin.udemyfeedreader;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class retrofitApi {

    final private String API_URL = "https://www.udemy.com";
    private Response<udemyCourses> response;

    public retrofitApi() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceFeedUdemy service = retrofit.create(InterfaceFeedUdemy.class);
        response = service.getFeed().execute();
    }

    public udemyCourses getData(){
        return response.body();
    }

}
