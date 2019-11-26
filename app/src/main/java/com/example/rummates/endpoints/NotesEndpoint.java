package com.example.rummates.endpoints;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.serializer.NotesSerializer;
import com.example.rummates.serializer.ShoppingListSerializer;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NotesEndpoint {
    //TODO:RENAME!!! (this is notes endpoint now)
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_URL = "https://rumies.herokuapp.com";
    private static final String SERVER_POSTS = "https://rumies.herokuapp.com/groups/notes";

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

    public String updateDatabase(NotesEntity notesEntity){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.execute(notesEntity);
        return "done";
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

    @SuppressLint("StaticFieldLeak")
    private class DatabaseConnector extends AsyncTask<NotesEntity, Void, NotesEntity>{

        @SuppressLint("AuthLeak") String uri = "mongodb://edmin:karolkrawczyk@rumies-shard-00-00-df76j.azure.mongodb.net:27017,rumies-shard-00-01-df76j.azure.mongodb.net:27017,rumies-shard-00-02-df76j.azure.mongodb.net:27017/test?ssl=true&replicaSet=Rumies-shard-0&authSource=admin&retryWrites=true&w=majority";
        String databaseName = "test";
        String collectionName = "groups";

        MongoClientURI clientURI;
        MongoClient mongoClient;
        MongoDatabase mongoDatabase;
        MongoCollection collection;

        Document search = new Document("group_name", "Roomies Dev");
        Document found;

        @Override
        protected NotesEntity doInBackground(NotesEntity... notesEntities) {

            try {
                clientURI = new MongoClientURI(uri);
                mongoClient = new MongoClient(clientURI);
                mongoDatabase = mongoClient.getDatabase(databaseName);
                collection = mongoDatabase.getCollection(collectionName);
                found = (Document) collection.find(search).first();
            }catch(Exception e)
            {
                System.out.println("connection-exception");
                return null;
            }
            return notesEntities[0];
        }

        @Override
        protected void onPostExecute(NotesEntity notesEntity) {
            if(notesEntity!=null) {
                Bson updatedDocument = Document.parse(NotesSerializer.notesEntitySerializer(notesEntity));
                Bson updateOperation = new Document("$set", updatedDocument);
                collection.updateOne(found, updateOperation);
            }else{
            }
        }

    }

    private String selector(Integer id)
    {
        switch(id){
            case 0: return SERVER_POSTS + "/5dc6ba9c2585a92b30b3fb81";
            default:
                return SERVER_URL;
        }
    }
}
