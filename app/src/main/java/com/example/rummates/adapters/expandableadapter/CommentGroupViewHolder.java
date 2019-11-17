package com.example.rummates.adapters.expandableadapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.example.rummates.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CommentGroupViewHolder extends GroupViewHolder {
    private TextView tvComments;

    public CommentGroupViewHolder(View itemView) {
        super(itemView);
        tvComments = itemView.findViewById(R.id.sli_comments);
    }

    @SuppressLint("SetTextI18n")
    public void bind(CommentGroupModel cgm){
        if(cgm.getItemCount() == 0)
            tvComments.setText("");
        else
            tvComments.setText("Comments("+cgm.getItemCount()+")");
    }
}
