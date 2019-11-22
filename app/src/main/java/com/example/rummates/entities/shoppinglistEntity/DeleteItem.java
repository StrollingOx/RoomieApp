package com.example.rummates.entities.shoppinglistEntity;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeleteItem {


    @SerializedName("name")
    @Expose
    private String listName;
    @SerializedName("item")
    @Expose
    private String itemName;

    public DeleteItem(String itemName) {
        this.itemName = itemName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
