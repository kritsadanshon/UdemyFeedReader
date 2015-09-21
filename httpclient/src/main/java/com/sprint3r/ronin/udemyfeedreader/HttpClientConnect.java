package com.sprint3r.ronin.udemyfeedreader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientConnect {

    private HttpURLConnection urlConnection;
    private JsonObject jsonObj;

    public HttpClientConnect(String url) throws IOException{
        urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.setConnectTimeout(3000);
    }

    public JsonObject getResult() throws IOException {
        InputStreamReader inputReader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader bufferReader = new BufferedReader(inputReader);
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            total.append(line);
        }
        JsonParser parser = new JsonParser();
        jsonObj = parser.parse(total.toString()).getAsJsonObject();
        return jsonObj;
    }

}
