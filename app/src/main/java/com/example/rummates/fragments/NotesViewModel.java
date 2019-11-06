package com.example.rummates.fragments;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class NotesViewModel extends ViewModel {

    public static ArrayList<String> posts;

    public NotesViewModel() {
        posts = new ArrayList<>();
        posts.add("First Post");
        posts.add("Second Post");
    }


    public ArrayList<String> getNotes() {
        return posts;
    }



}