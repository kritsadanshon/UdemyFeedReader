package com.sprint3r.ronin.udemyfeedreader;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RetrofitApi {

    final private String API_URL = "https://www.udemy.com";
    private Response<UdemyCourses> response;

    public RetrofitApi() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceFeedUdemy service = retrofit.create(InterfaceFeedUdemy.class);
        response = service.getFeed().execute();
    }

    public UdemyCourses getData(){
        return response.body();
    }

}
