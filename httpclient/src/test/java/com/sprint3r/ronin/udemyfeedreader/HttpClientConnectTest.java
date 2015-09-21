package com.sprint3r.ronin.udemyfeedreader;

import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class HttpClientConnectTest {

    private HttpClientConnect HttpClientConnect;

    public HttpClientConnectTest() throws Exception {
        HttpClientConnect = new HttpClientConnect("https://www.udemy.com/api-2.0/courses");
    }

    @Test
    public void requestDataTestShouldReturnJsonData() throws IOException {
        String expected = "https://www.udemy.com/api-2.0/courses?page=2";
        assertEquals(expected, HttpClientConnect.getResult().get("next").getAsString());
    }

}
