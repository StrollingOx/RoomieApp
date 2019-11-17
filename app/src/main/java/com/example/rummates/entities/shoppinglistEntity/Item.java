package com.example.rummates.entities.shoppinglistEntity;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {

    @SerializedName("comments")
    @Expose
    private ArrayList<Comment> comments;
    @SerializedName("item")
    @Expose
    private String itemName;
    @SerializedName("checked")
    @Expose
    private boolean isChecked;


    public Item(String itemName, boolean isChecked) {
        this.itemName = itemName;
        this.isChecked = isChecked;
    }

    public Item(String itemName, boolean isChecked, ArrayList<Comment> comments) {
        this.itemName = itemName;
        this.isChecked = isChecked;
        this.comments = comments;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void toggle(){
        isChecked = !isChecked;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void deleteComment(int position){
        comments.remove(position);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
