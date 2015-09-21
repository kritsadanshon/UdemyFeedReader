package com.sprint3r.ronin.udemyfeedreader;

import retrofit.Call;
import retrofit.http.GET;

public interface InterfaceFeedUdemy {
    @GET("/api-2.0/courses")
    Call<UdemyCourses> getFeed();
}
