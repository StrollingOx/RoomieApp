package com.example.rummates.entities.shoppinglistEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckedForItem {


    @SerializedName("name")
    @Expose
    private String listName;
    @SerializedName("item")
    @Expose
    private String itemName;
    @SerializedName("checked")
    @Expose
    private boolean checked;


    public CheckedForItem(String itemName) {
        this.itemName = itemName;
    }

    public String getListName() {
        return listName;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getChecked() {
        return checked;
    }


    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
