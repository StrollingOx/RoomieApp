package com.example.rummates.entities.notesEntity;

import com.example.rummates.entities.shoppinglistEntity.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Note {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;


    public Note(String content, String author, String date) {
        this.date = date;
        this.content = content;
        this.author = author;
    }

    public String getNoteContent(){ return content;}


}
