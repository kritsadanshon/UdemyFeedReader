package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;

import java.io.IOException;
import org.json.JSONArray;
import static org.junit.Assert.*;

public class httpClientResponseTest {

    private httpClientResponse httpClientResponse;
    private JSONArray cardView;

    public httpClientResponseTest() throws IOException {
        httpClientResponse = new httpClientResponse();
        cardView = (JSONArray) httpClientResponse.getJSONArrayForCardView();
    }

    @Test
    public void checkObjectJsonArray(){
        assertEquals(JSONArray.class, httpClientResponse.getJSONArrayForCardView().getClass());
    }

    @Test
    public void lengthOfCardView(){
        assertEquals(12, cardView.length());
    }

    @Test
    public void checkTitleNameOfIndexOne(){
        String TitleName = cardView.getJSONObject(1).getString("title");
        assertEquals("Javascript: Understanding the Weird Parts", TitleName);
    }

    @Test
    public void checkUrlOfIndexOne(){
        String url = cardView.getJSONObject(1).getString("url");
        assertEquals("https://www.udemy.com/understand-javascript/", url);
    }

    @Test
    public void getNextUrlTest(){
        assertEquals("https://www.udemy.com/api-2.0/courses?page=2", httpClientResponse.getNextUrl());
    }

}
