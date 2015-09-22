package com.sprint3r.ronin.udemyfeedreader;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitApi {

    final private String API_URL = "https://www.udemy.com";

    public Call<CoursesDetail> getData(String page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUdemyCourses service = retrofit.create(IUdemyCourses.class);

        final Call<CoursesDetail> call = service.getFeed(page);
        return call;
    }
}

