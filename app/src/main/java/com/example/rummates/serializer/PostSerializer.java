package com.example.rummates.serializer;

import com.example.rummates.entities.testEntity.PostEntity;
import com.example.rummates.entities.testEntity.Post;
import com.google.gson.Gson;

public class PostSerializer {

    public static Post testEntityDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Post.class);
    }

    public static PostEntity postsListDrializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, PostEntity.class);
    }
}