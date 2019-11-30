package com.example.rummates.entities.groupEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupEntity {

    @SerializedName("group_name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public List<String> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<String> user_ids) {
        this.user_ids = user_ids;
    }
}
