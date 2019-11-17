package com.example.rummates.entities.shoppinglistEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShoppingListEntity {

//    @SerializedName("_id")
//    @Expose
//    private String id;

    @SerializedName("shopping_lists")
    @Expose
    private ArrayList<ShoppingList> lists = null;

//    public String getId() {
//        return id;
//    }

    public ArrayList<ShoppingList> getLists() {
        return lists;
    }
}
