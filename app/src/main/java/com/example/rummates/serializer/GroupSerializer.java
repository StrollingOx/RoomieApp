package com.example.rummates.serializer;

import com.example.rummates.entities.groupEntity.GroupEntity;
import com.example.rummates.entities.groupEntity.ListOfGroups;
import com.google.gson.Gson;

public class GroupSerializer {

    public static GroupEntity groupEntityDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, GroupEntity.class);
    }

    public static String groupSerializer(GroupEntity groupEntity){
        Gson gson = new Gson();
        return gson.toJson(groupEntity);
    }

    public static ListOfGroups groupListDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ListOfGroups.class);
    }

}

