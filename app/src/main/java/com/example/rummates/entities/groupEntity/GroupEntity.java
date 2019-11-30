package com.example.rummates.entities.groupEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupEntity {

    @SerializedName("group_name")
    @Expose
    private String name;

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("user_ids")
    @Expose
    private List<String> user_ids = null;

    public GroupEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<String> user_ids) {
        this.user_ids = user_ids;
    }
}
