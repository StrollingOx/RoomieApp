package com.example.rummates.adapters.expandableadapter;

import android.view.View;
import android.widget.TextView;

import com.example.rummates.R;
import com.example.rummates.classes.Comment;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CommentViewHolder extends ChildViewHolder {
    private TextView tvUserName, tvDescription;
    public CommentViewHolder(View itemView) {
        super(itemView);
        tvUserName = itemView.findViewById(R.id.comment_username);
        tvDescription = itemView.findViewById(R.id.comment_description);
    }

    public void bind(Comment comment){
        tvUserName.setText(comment.getUsername());
        tvDescription.setText(comment.getDescription());
    }
}
