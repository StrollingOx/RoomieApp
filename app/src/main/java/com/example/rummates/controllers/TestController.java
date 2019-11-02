package com.example.rummates.controllers;

import android.content.Context;
import android.util.Log;

import com.example.rummates.endpoints.PostsEndpoint;
import com.example.rummates.entities.testEntity.PostEntity;
import com.example.rummates.entities.testEntity.Post;
import com.example.rummates.serializer.PostSerializer;

import java.util.ArrayList;
import java.util.List;

public class TestController {

    private static TestController instance;
    private PostsEndpoint postsEndpoint;
    private Context context;

    private TestController(Context context){
        this.context=context;
        this.postsEndpoint = new PostsEndpoint();
    }

    public static TestController getInstance(Context context) {
        if (instance == null)
            instance = new TestController(context);
        return instance;
    }

    public static TestController getInstance() {
        return instance;
    }

    public Post getAllPosts() {
        Post Post = null;

        try{
            Post = PostSerializer.allPostsDeserializer(postsEndpoint.getAllPosts());
            Log.d("Info", "Loading data from rumies.herokuapp.com/posts.");
            return Post;
        }catch(Exception e){
            Log.d("Info", "Failed to download data.");
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
            Log.d("Info", "Failed to download data.");
            return new PostEntity("-1","-1","-1","-1", "-1");
        }

    }

}
