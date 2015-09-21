package com.sprint3r.ronin.udemyfeedreader;

import retrofit.Call;
import retrofit.http.GET;

public interface IUdemyCourses {
    @GET("/api-2.0/courses")
    Call<CoursesDetail> getFeed();
}
