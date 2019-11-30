package com.example.rummates.entities.notesEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    //GET
    public Note(String content, String author, String date) {
        this.date = date;
        this.content = content;
        this.author = author;
    }

    //PATCH
    //{ "notes": {"author": "Adam", "content": "test test teset"} }
    public Note(String content, String author) {
        this.content = content;
        this.author = author;
    }

    //DELETE
    //{ "content": "test test teset"}
    public Note(String content) {
        this.content = content;
    }


    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }


    public String getContent(){ return content;}


}
