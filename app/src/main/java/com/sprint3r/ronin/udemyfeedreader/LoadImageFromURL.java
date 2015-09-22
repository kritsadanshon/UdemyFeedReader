package com.sprint3r.ronin.udemyfeedreader;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageFromURL extends AsyncTask<String, Void, Bitmap> {

    private String imageCourse;

    public LoadImageFromURL(String imageCourse) {

        this.imageCourse = imageCourse;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            URL url = new URL(imageCourse);
            InputStream is = url.openConnection().getInputStream();
            Bitmap bitMap = BitmapFactory.decodeStream(is);
            return bitMap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
