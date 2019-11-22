package com.example.rummates.serializer;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.example.rummates.entities.shoppinglistEntity.CommentForItem;
import com.example.rummates.entities.shoppinglistEntity.DeleteItem;
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

    public static String itemSerialiser(Item item){
        Gson gson = new Gson();
        return gson.toJson(item);
    }

    public static Comment commentDeserializer(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Comment.class);
    }

    public static String shoppingListEntitySerializer(ShoppingListEntity shoppingListEntity){
        Gson gson = new Gson();
        return gson.toJson(shoppingListEntity);
    }

    public static String commentForItemSerializer(CommentForItem item) {
        Gson gson = new Gson();
        return gson.toJson(item);
    }

    public static String deleteItemSerializer(DeleteItem item){
        Gson gson = new Gson();
        return gson.toJson(item);
    }
}
