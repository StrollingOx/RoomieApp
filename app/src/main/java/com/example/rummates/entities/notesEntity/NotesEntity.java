package com.example.rummates.entities.notesEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotesEntity {

    @SerializedName("notes")
    @Expose
    private ArrayList<Note> notes = null;

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
