package com.example.rummates.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rummates.R;

public class AddCommentDialog extends AppCompatDialogFragment {
    private EditText etComment;
    private AddCommentDialogListener listener;
    private final int position;

    public AddCommentDialog(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_shoppinglist_addcomentdialog,null);
        etComment = view.findViewById(R.id.sli_add_comment);

        builder.setView(view)
                .setTitle("Add Comment")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}})
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Need endpoint to finish
                        //TODO:Add comment
                        //TODO:Add username to comment
                    }});

        return builder.create();
    }
    public interface AddCommentDialogListener{
        void applyComment(String description, int position);
    }
}
