package com.example.rummates.endpoints;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.util.JSON;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GroupsEndpoint {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_URL = "https://rumies.herokuapp.com";
    private static final String SERVER_GROUP_GET = "https://rumies.herokuapp.com/groups";
    private static final String SERVER_GROUP_POST = "https://rumies.herokuapp.com/groups";
    private static final String SERVER_GROUPS_GET = "https://rumies.herokuapp.com/users/groups/nick/";

    public String getGroup(String groupID){
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(0, groupID).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        return response;
    }

    public String addGroup(String JSONnote){
        System.out.println(JSONnote);
        NetworkConnector networkConnector = new NetworkConnector();
        String response;

        try {
            response = networkConnector.execute(1, "", JSONnote).get();
            System.out.println("responseee "+response);
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
            System.out.println("responseee "+response);
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
            System.out.println("responseee "+response);
        }
        return response;
    }

    public String getGroupsForUser(String userNick){
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        Log.d("Info", "Sending request to network connector: "+SERVER_GROUPS_GET+userNick);
        try {
            response = networkConnector.execute(2, userNick).get();
            System.out.println("responseee "+response);
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
            System.out.println("responseee "+response);
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
            System.out.println("responseee "+response);
        }
        return response;
    }

    @SuppressLint("StaticFieldLeak")
    private class NetworkConnector extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... objects) {
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = null;
            DataOutputStream os = null;
            int responseCode = 0;
            try {
                URL obj = new URL(selector((Integer)objects[0]) + objects[1]);
                con = (HttpURLConnection) obj.openConnection();
                switch((Integer)objects[0]){
                    case 1:
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        os = new DataOutputStream(con.getOutputStream());
                        os.writeBytes((String)objects[2]);

                        os.flush();
                        os.close();
                        break;
                    default: //case 0: //case 2:
                        //ERR
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", USER_AGENT);
                }
                responseCode = con.getResponseCode();
                System.out.println("responseee CODE "+responseCode);
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
            case 0: return SERVER_GROUP_GET;
            case 1: return SERVER_GROUP_POST;
            case 2: return SERVER_GROUPS_GET;
            default:
                return SERVER_URL;
        }
    }
}
