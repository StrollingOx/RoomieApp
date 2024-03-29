package com.example.rummates.entities.shoppinglistEntity;

import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {


    @SerializedName("name")
    @Expose
    private String listName;
    @SerializedName("comments")
    @Expose
    private ArrayList<Comment> comments;
    @SerializedName("item")
    @Expose
    private String itemName;
    @SerializedName("checked")
    @Expose
    private boolean isChecked;

    public Item(String itemName) {
        this.itemName = itemName;
    }

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
        String groupID = "5dc6ba9c2585a92b30b3fb81";

        isChecked = !isChecked;
        ShoppingListEntity shoppingListEntity = EndpointController.getInstance().getShoppingListsForGroup(groupID);
        CheckedForItem item = new CheckedForItem(this.getItemName());
        item.setListName(shoppingListEntity.getLists().get(0).getListName());
        item.setChecked(isChecked);
        //Patch to server
        EndpointController.getInstance().patchShoppingListItemChecked(groupID, item);
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
