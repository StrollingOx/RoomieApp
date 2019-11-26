package com.example.rummates.controllers;

import android.content.Context;
import android.util.Log;

import com.example.rummates.endpoints.NotesEndpoint;
import com.example.rummates.endpoints.ShoppingListEndpoint;
import com.example.rummates.entities.notesEntity.NotesEntity;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.entities.testEntity.PostEntity;
import com.example.rummates.entities.testEntity.Post;
import com.example.rummates.serializer.NotesSerializer;
import com.example.rummates.serializer.PostSerializer;
import com.example.rummates.serializer.ShoppingListSerializer;

import java.util.ArrayList;
import java.util.List;

public class EndpointController {

    private static EndpointController instance;
    private NotesEndpoint notesEndpoint;
    private ShoppingListEndpoint shoppingListEndpoint; //TODO: Might need to merge with Posts/NotesEndpoint
    private Context context;

    private EndpointController(Context context){
        this.context=context;
        this.notesEndpoint = new NotesEndpoint();
        this.shoppingListEndpoint = new ShoppingListEndpoint();
    }

    public static EndpointController getInstance(Context context) {
        if (instance == null)
            instance = new EndpointController(context);
        return instance;
    }

    public static EndpointController getInstance() {
        return instance;
    }

    public Post getAllPosts() {
        Post Post = null;

        try{
            Post = PostSerializer.allPostsDeserializer(notesEndpoint.getAllPosts());
            Log.d("Info", "Loading data from rumies.herokuapp.com/posts.");
            return Post;
        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/posts).");
            List<PostEntity> list = new ArrayList<>();
            list.add(new PostEntity("-1","-1","-1","-1", "-1"));
            return new Post(list);
        }

    }

    public PostEntity getFirstPost() {
        PostEntity postEntity = null;

        try{
            postEntity = PostSerializer.singlePostDeserializer(notesEndpoint.getFirstPost());
            Log.d("Info", "Loading data from rumies.herokuapp.com/posts/5da8e600b5d6e426d4d6ef0b.");
            return postEntity;
        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/posts/5da8e600b5d6e426d4d6ef0b).");
            return new PostEntity("-1","-1","-1","-1", "-1");
        }

    }

    //TODO: this method should get String variable of a group ID to identify which shopping lists to return
    public ShoppingListEntity getShoppingListsForGroup(/*String id */){
        ShoppingListEntity shoppingListEntity = null;

        try{
            shoppingListEntity = ShoppingListSerializer.shoppingListEntityDeserializer(shoppingListEndpoint.getShoppingListsFromGroup());
            Log.d("Info", "Loading data from rumies.herokuapp.com/groups/shopping/5dc6ba9c2585a92b30b3fb81.");
            return shoppingListEntity;

        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/groups/shopping/5dc6ba9c2585a92b30b3fb81).");
            return new ShoppingListEntity();
        }
    }

    public NotesEntity getNotesForGroup(/*String id */){
        NotesEntity notesEntity = null;

        try{
            notesEntity = NotesSerializer.notesEntityDeserializer(notesEndpoint.getAllPosts());
            Log.d("Info", "Loading data from rumies.herokuapp.com/groups/notes/5dc6ba9c2585a92b30b3fb81.");
            return notesEntity;

        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/groups/notes/5dc6ba9c2585a92b30b3fb81).");
            return new NotesEntity();
        }
    }

    public ShoppingListEndpoint getShoppingListEndpoint() {
        return shoppingListEndpoint;
    }

    public NotesEndpoint getNotesEndpoint() {
        return notesEndpoint;
    }
}
