package com.example.a172110.test;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpRequest extends AsyncTask<String, Void, String>{
    private Activity mActivity;

    public AsyncHttpRequest(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return sb.toString();
    }

    public void onPostExecute(String string) {
        ((TextView)mActivity.findViewById(R.id.textview)).setText(string);
    }
}
