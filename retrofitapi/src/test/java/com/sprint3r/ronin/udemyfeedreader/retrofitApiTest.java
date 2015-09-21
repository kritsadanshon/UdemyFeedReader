package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;
import com.google.gson.JsonElement;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RetrofitApiTest {

    private RetrofitApi RetrofitApi;
    private UdemyCourses feedUdemy;

    public RetrofitApiTest() throws IOException {
        RetrofitApi = new RetrofitApi();
        feedUdemy = RetrofitApi.getData();
    }

    @Test
    public void testCallNextPageUrl(){
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", feedUdemy.next);
    }

    @Test
    public void testCallResults(){
        JsonElement actual = feedUdemy.results.get(0).getAsJsonObject().get("title");
        assertEquals("Learn and Understand AngularJS", actual.getAsString());
    }
}
