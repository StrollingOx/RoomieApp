package com.example.rummates.entities.shoppinglistEntity;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentForItem {


    @SerializedName("name")
    @Expose
    private String listName;
    @SerializedName("item")
    @Expose
    private String itemName;
    @SerializedName("nick")
    @Expose
    private String username;
    @SerializedName("content")
    @Expose
    private String description;

    public CommentForItem(String itemName) {
        this.itemName = itemName;
    }

    public String getListName() {
        return listName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
