package com.example.rummates.entities.shoppinglistEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShoppingList {
    @SerializedName("list")
    @Expose
    private ArrayList<Item> products = null;

    @SerializedName("name")
    @Expose
    private String listName;

    public ArrayList<Item> getProducts() {
        return products;
    }

    public String getListName() {
        return listName;
    }
}
