package com.example.rummates.controllers;

import android.content.Context;
import android.util.Log;

import com.example.rummates.endpoints.PostsEndpoint;
import com.example.rummates.endpoints.ShoppingListEndpoint;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.entities.testEntity.PostEntity;
import com.example.rummates.entities.testEntity.Post;
import com.example.rummates.serializer.PostSerializer;
import com.example.rummates.serializer.ShoppingListSerializer;

import java.util.ArrayList;
import java.util.List;

public class EndpointController {

    private static EndpointController instance;
    private PostsEndpoint postsEndpoint;
    private ShoppingListEndpoint shoppingListEndpoint; //TODO: Might need to merge with Posts/NotesEndpoint
    private Context context;

    private EndpointController(Context context){
        this.context=context;
        this.postsEndpoint = new PostsEndpoint();
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
            Post = PostSerializer.allPostsDeserializer(postsEndpoint.getAllPosts());
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
            postEntity = PostSerializer.singlePostDeserializer(postsEndpoint.getFirstPost());
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
            shoppingListEntity = ShoppingListSerializer.shoppingListEntityDeserializer(shoppingListEndpoint.getAllShoppingLists());
            Log.d("Info", "Loading data from rumies.herokuapp.com/groups/shopping/5dc6ba9c2585a92b30b3fb81.");
            return shoppingListEntity;

        }catch(Exception e){
            Log.d("Info", "Failed to download data(rumies.herokuapp.com/groups/shopping/5dc6ba9c2585a92b30b3fb81).");
            return new ShoppingListEntity();
        }
    }

    public ShoppingListEndpoint getShoppingListEndpoint() {
        return shoppingListEndpoint;
    }
}
