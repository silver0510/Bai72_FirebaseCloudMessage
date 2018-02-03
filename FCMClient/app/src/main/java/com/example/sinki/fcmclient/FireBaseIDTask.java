package com.example.sinki.fcmclient;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sinki on 9/14/2017.
 */

public class FireBaseIDTask extends AsyncTask<String,Void,Boolean> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try
        {
            URL url = new URL("http://www.sinkiapp.somee.com/api/fcm/?token="+params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/xml;charset=UTF-8");
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line!=null)
            {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            boolean kt = builder.toString().contains("true");
        }
        catch (Exception ex)
        {
            Log.e("LOI",ex.toString());
        }
        return null;
    }
}
