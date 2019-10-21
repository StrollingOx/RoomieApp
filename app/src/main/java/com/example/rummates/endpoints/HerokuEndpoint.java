package com.example.rummates.endpoints;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class HerokuEndpoint {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_URL = "https://rumies.herokuapp.com";
    private static final String SERVER_POSTS = "https://rumies.herokuapp.com/posts";

    public String getAllPosts(){
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(0).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        return response;
    }

    public String getFirstPost(){
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(1).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        return response;
    }



    @SuppressLint("StaticFieldLeak")
    private class NetworkConnector extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... objects) {
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = null;
            int responseCode = 0;
            try {
                URL obj = new URL(selector((Integer)objects[0]));
                con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                responseCode = con.getResponseCode();
            } catch (Exception e) {
                return "connection-exception";
            }

            if (responseCode == 404) {
                return "not-found-exception";
            }

            Log.d("Internet", "Sending request to URL : " + con.getURL().toString());
            Log.d("Internet", "Response Code: " + responseCode);
            BufferedReader in = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } catch (Exception e) {
                return "memory-exception";
            }
            return response.toString();
        }
    }

    private String selector(Integer id)
    {
        switch(id){
            case 0: return SERVER_POSTS;
            case 1: return SERVER_POSTS + "/5da8e600b5d6e426d4d6ef0b";
            default:
                return SERVER_URL;
        }
    }
}
