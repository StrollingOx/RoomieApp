package com.example.rummates.serializer;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingList;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.google.gson.Gson;

public class ShoppingListSerializer {

    public static ShoppingListEntity shoppingListEntityDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ShoppingListEntity.class);
    }

    public static ShoppingList shoppingListDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ShoppingList.class);
    }

    public static Item itemDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Item.class);
    }

    public static Comment commentDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Comment.class);
    }

}