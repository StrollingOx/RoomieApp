package com.example.rummates.serializer;

import com.example.rummates.entities.UserEntity;
import com.google.gson.Gson;

public class UserSerializer {

    public static UserEntity userDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, UserEntity.class);
    }

    public static String userSerializer(UserEntity user){
        Gson gson = new Gson();
        return gson.toJson(user);
    }


}

