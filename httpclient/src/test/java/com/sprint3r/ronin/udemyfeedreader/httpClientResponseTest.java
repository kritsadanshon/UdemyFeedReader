package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;

import org.json.JSONObject;
import java.io.IOException;
import org.json.JSONArray;
import static org.junit.Assert.*;

public class httpClientResponseTest {

    httpClientResponse httpClientResponse;

    public httpClientResponseTest() throws IOException {
        httpClientResponse = new httpClientResponse();
    }

    @Test
    public void checkObjectJsonArray(){
        assertEquals(JSONArray.class, httpClientResponse.getJSONArrayForCardView().getClass());
    }

    @Test
    public void getTitleNameZeroIndexFromJsonArrayForCardView(){
        JSONObject jsonObjectResults = httpClientResponse.getJSONArrayForCardView().getJSONObject(0);
        assertEquals("Learn and Understand AngularJS", jsonObjectResults.get("title"));
    }

    @Test
    public void getNextUrlTest(){
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", httpClientResponse.getNextUrl());
    }

}
