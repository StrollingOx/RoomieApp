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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

//TODO: Merge with PostsEndpoint/NotesEndpoint (we don't need two almost identical classes)
public class ShoppingListEndpoint {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_URL = "https://rumies.herokuapp.com";
    private static final String SERVER_SHOPPING = "https://rumies.herokuapp.com/groups/shopping";
    private final String TAG = "ShoppingListEndpoint";

    public String getAllShoppingLists(){
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

    public void updateDatabase(ShoppingListEntity shoppingListEntity){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.execute(shoppingListEntity);

    }

//    public String getFirstShoppingList(){
//        NetworkConnector networkConnector = new NetworkConnector();
//        String response;
//        try {
//            response = networkConnector.execute(1).get();
//        } catch (ExecutionException e) {
//            response = "EXECUTION_EXCEPTION";
//        } catch (InterruptedException e) {
//            response = "INTERRUPTED_EXCEPTION";
//        }
//        return response;
//    }


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
    private class DatabaseConnector extends AsyncTask<ShoppingListEntity, Void, ShoppingListEntity>{

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
        protected ShoppingListEntity doInBackground(ShoppingListEntity... shoppingListEntities) {
            StringBuilder response = new StringBuilder();


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
            return shoppingListEntities[0];
        }

        @Override
        protected void onPostExecute(ShoppingListEntity shoppingListEntity) {
            if(shoppingListEntity!=null) {
                Bson updatedDocument = Document.parse(ShoppingListSerializer.shoppingListEntitySerializer(shoppingListEntity));
                Bson updateOperation = new Document("$set", updatedDocument);
                collection.updateOne(found, updateOperation);
                Log.d(TAG, "Database successfully updated");
            }else{
                Log.d(TAG, "Failed to update document on database");
            }
        }
    }

//    public static void updateDatabase(ShoppingListEntity shoppingListEntity) {
//        String uri = "mongodb://edmin:karolkrawczyk@rumies-shard-00-00-df76j.azure.mongodb.net:27017,rumies-shard-00-01-df76j.azure.mongodb.net:27017,rumies-shard-00-02-df76j.azure.mongodb.net:27017/test?ssl=true&replicaSet=Rumies-shard-0&authSource=admin&retryWrites=true&w=majority";
//        //String uri = "mongodb+srv://edmin:karolkrawczyk@rumies-df76j.azure.mongodb.net/test?retryWrites=true&w=majority";
//        String databaseName = "test";
//        String collectionName = "groups";
//        Document search = new Document("group_name", "Roomies Dev");
//
//        MongoClientURI clientURI = new MongoClientURI(uri);
//        MongoClient mongoClient = new MongoClient(clientURI);
//
//        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
//        MongoCollection collection = mongoDatabase.getCollection(collectionName);
//
//        //Log.d(TAG, "Connected to Database");
//
//        Document found = (Document) collection.find(search).first();
//        if(found != null){
//            Bson updatedDocument = Document.parse(ShoppingListSerializer.shoppingListEntitySerializer(shoppingListEntity));
//            Bson updateOperation = new Document("$set", updatedDocument);
//            collection.updateOne(found, updateOperation);
//            //Log.d(TAG, "Document updated");
//        }
//
//
//    }

    private String selector(Integer id)
    {
        switch(id){
            case 0: return SERVER_SHOPPING + "/5dc6ba9c2585a92b30b3fb81";
            default:
                return SERVER_URL;
        }
    }
}

/*

        String uri = "mongodb://edmin:karolkrawczyk@rumies-shard-00-00-df76j.azure.mongodb.net:27017,rumies-shard-00-01-df76j.azure.mongodb.net:27017,rumies-shard-00-02-df76j.azure.mongodb.net:27017/test?ssl=true&replicaSet=Rumies-shard-0&authSource=admin&retryWrites=true&w=majority";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection collection = mongoDatabase.getCollection("groups");

        System.out.println("Database Connected");

        Document search = (Document) collection.find(new Document("group_name", "Roomies Dev")).first();
        System.out.println(search);

        //from Document to json string
        String json = search.toJson();
        System.out.println(json);

        //from json string to Document
        Document doc = Document.parse(json);
        System.out.println(doc);

        //equals?
        System.out.println(search.toString().equals(doc.toString()));

 */
