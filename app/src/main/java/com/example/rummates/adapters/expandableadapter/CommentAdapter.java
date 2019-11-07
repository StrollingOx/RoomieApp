package com.example.rummates.adapters.expandableadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rummates.R;
import com.example.rummates.classes.Comment;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CommentAdapter extends ExpandableRecyclerViewAdapter<CommentGroupViewHolder, CommentViewHolder> {
    public CommentAdapter(List<? extends ExpandableGroup> groups){
        super(groups);
    }

    @Override
    public CommentGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shoppinglist_commentviewholder, parent, false);
        return new CommentGroupViewHolder(v);
    }

    @Override
    public CommentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shoppinglist_comment,parent,false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(CommentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Comment comment = (Comment) group.getItems().get(childIndex);
        holder.bind(comment);
    }

    @Override
    public void onBindGroupViewHolder(CommentGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        final CommentGroupModel cgm = (CommentGroupModel) group;
        holder.bind(cgm);

    }
}
