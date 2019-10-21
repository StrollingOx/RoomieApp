package com.example.rummates.entities.testEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
//DO NOT USE!!!
//TODO:fix
public class Post {

    @SerializedName("")
    @Expose
    private java.util.List<PostEntity> list = null;

    //DELETE THIS
    public Post(List<PostEntity> list) {
        this.list = list;
    }


    public List<PostEntity> getList() {
        return list;
    }

    public void setList(List<PostEntity> list) {
        this.list = list;
    }
}
