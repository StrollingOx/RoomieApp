package com.example.rummates.entities;

import com.example.rummates.entities.groupEntity.GroupEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserEntity {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("nick")
    @Expose
    private String nick;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("groups")
    @Expose
    private List<GroupEntity> groups = null;

    public String get_id() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }
}
