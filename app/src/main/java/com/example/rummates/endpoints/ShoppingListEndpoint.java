package com.example.rummates.endpoints;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.serializer.ShoppingListSerializer;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ShoppingListEndpoint {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_URL = "https://rumies.herokuapp.com";
    private static final String SERVER_SHOPPING_GET = "https://rumies.herokuapp.com/groups/shopping/";
    private static final String SERVER_SHOPPING_PATCH_ITEM = "http://rumies.herokuapp.com/groups/shopping/item/";
    private static final String SERVER_SHOPPING_PATCH_COMMENT = "http://rumies.herokuapp.com/groups/shopping/com/";
    private static final String SERVER_SHOPPING_DELETE_ITEM = "http://rumies.herokuapp.com/groups/shopping/item/";
    private final String TAG = "ShoppingListEndpoint";

    public String getShoppingLists(String groupID){
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

    public String patchShoppingListItem(String groupId, String JSONitem){
        System.out.println(JSONitem);
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(1, groupId, JSONitem).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        return response;
    }

    public String patchShoppingListComment(String groupId, String JSONitem){
        System.out.println(JSONitem);
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(2, groupId, JSONitem).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        return response;
    }

    public String deleteShoppingListItem(String groupId, String JSONitem){
        System.out.println(JSONitem);
        NetworkConnector networkConnector = new NetworkConnector();
        String response;
        try {
            response = networkConnector.execute(3, groupId, JSONitem).get();
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
            DataOutputStream os = null;
            int responseCode = 0;
            try {
                URL obj = new URL(selector((Integer)objects[0]) + objects[1]);
                con = (HttpURLConnection) obj.openConnection();
                switch((Integer)objects[0]){
                    case 1:
                    case 2:
                        con.setRequestMethod("PATCH");
                        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        os = new DataOutputStream(con.getOutputStream());
                        os.writeBytes((String)objects[2]);

                        os.flush();
                        os.close();
                        break;
                    case 3:
                        con.setRequestMethod("DELETE");
                        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        os = new DataOutputStream(con.getOutputStream());
                        os.writeBytes((String)objects[2]);

                        os.flush();
                        os.close();
                        break;
                    default:
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", USER_AGENT);

                }
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
            case 0: return SERVER_SHOPPING_GET;
            case 1: return SERVER_SHOPPING_PATCH_ITEM;
            case 2: return SERVER_SHOPPING_PATCH_COMMENT;
            case 3: return SERVER_SHOPPING_DELETE_ITEM;
            default:
                return SERVER_URL;
        }
    }
}
