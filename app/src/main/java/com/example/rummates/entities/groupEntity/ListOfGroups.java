package com.example.rummates.entities.groupEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfGroups {
    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("groups")
    @Expose
    List<GroupGET> list = null;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<GroupGET> getList() {
        return list;
    }

    public void setList(List<GroupGET> list) {
        this.list = list;
    }
}
