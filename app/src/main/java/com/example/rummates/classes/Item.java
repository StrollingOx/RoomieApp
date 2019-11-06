package com.example.rummates.classes;

import java.util.ArrayList;

public class Item {

    private String itemName;
    private boolean isChecked;
    private ArrayList<Comment> comments;

    public Item(String itemName, boolean isChecked) {
        this.itemName = itemName;
        this.isChecked = isChecked;
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

}
