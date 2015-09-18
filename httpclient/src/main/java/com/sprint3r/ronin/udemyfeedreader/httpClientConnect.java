package com.sprint3r.ronin.udemyfeedreader;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpClientConnect {

    private HttpURLConnection urlConnection;
    private JSONObject jsonObj;

    public httpClientConnect(String url) throws IOException{
        urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.setConnectTimeout(3000);
    }

    public JSONObject getResult() throws IOException {
        InputStreamReader inputReader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader bufferReader = new BufferedReader(inputReader);
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            total.append(line);
        }
        jsonObj = new JSONObject(total.toString());
        return jsonObj;
    }

}
