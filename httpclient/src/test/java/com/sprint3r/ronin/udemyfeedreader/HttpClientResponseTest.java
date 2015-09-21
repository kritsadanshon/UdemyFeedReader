package com.sprint3r.ronin.udemyfeedreader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class HttpClientResponseTest {

    HttpClientResponse HttpClientResponse;

    public HttpClientResponseTest() throws IOException {
        HttpClientResponse = new HttpClientResponse();
    }

    @Test
    public void checkObjectJsonArray(){
        assertEquals(JsonArray.class, HttpClientResponse.getJSONArrayForCardView().getClass());
    }

    @Test
    public void getTitleNameZeroIndexFromJsonArrayForCardView(){
        JsonObject jsonObjectResults = (JsonObject) HttpClientResponse.getJSONArrayForCardView().get(0).getAsJsonObject();
        assertEquals("Learn and Understand AngularJS", jsonObjectResults.get("title").getAsString());
    }

    @Test
    public void getNextUrlTest(){
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", HttpClientResponse.getNextUrl());
    }

}
