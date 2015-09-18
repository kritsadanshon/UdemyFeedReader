package com.sprint3r.ronin.udemyfeedreader;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class httpClientResponse {

    private httpClientConnect httpclient;
    private JSONObject jsonObject;

    public httpClientResponse() throws IOException {
        httpclient = new httpClientConnect("https://www.udemy.com/api-2.0/courses");
        jsonObject = httpclient.getResult();
    }

    public JSONArray getJSONArrayForCardView(){
        return jsonObject.getJSONArray("results");
    }

    public String getNextUrl(){
        return jsonObject.getString("next");
    }

}
