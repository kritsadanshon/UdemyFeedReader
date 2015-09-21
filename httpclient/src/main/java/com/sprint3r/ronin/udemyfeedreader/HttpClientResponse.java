package com.sprint3r.ronin.udemyfeedreader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;

public class HttpClientResponse {

    private HttpClientConnect httpclient;
    private JsonObject jsonObject;

    public HttpClientResponse() throws IOException {
        httpclient = new HttpClientConnect("https://www.udemy.com/api-2.0/courses");
        jsonObject = httpclient.getResult();
    }

    public JsonArray getJSONArrayForCardView(){
        return jsonObject.getAsJsonArray("results");
    }

    public String getNextUrl(){
        return jsonObject.get("next").getAsString();
    }

}
