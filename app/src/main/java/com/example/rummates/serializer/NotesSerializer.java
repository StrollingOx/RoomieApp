package com.example.rummates.serializer;

import com.example.rummates.entities.notesEntity.NotesEntity;
import com.google.gson.Gson;

public class NotesSerializer {

    public static NotesEntity notesEntityDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, NotesEntity.class);
    }

    public static String notesEntitySerializer(NotesEntity notesEntity){
        Gson gson = new Gson();
        return gson.toJson(notesEntity);
    }
}
