package com.example.rummates.adapters.expandableadapter;

import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CommentGroupModel extends ExpandableGroup<Comment> {
    public CommentGroupModel(String title, List<Comment> items) {
        super(title, items);
    }
}
