package com.example.rummates.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.rummates.R;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;

import java.util.ArrayList;
import java.util.Objects;

public class AddCommentDialog extends DialogFragment {
    private EditText etComment;
    private final int position;

    public AddCommentDialog(int position) {
        this.position = position;
    }
    //private AddCommentDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
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
                        String input = etComment.getText().toString();
                        if(!input.equals("")){
                            ShoppingListEntity shoppingListEntity = EndpointController.getInstance().getShoppingListsForGroup();
                            shoppingListEntity.getLists().get(0).getProducts().get(position).addComment(new Comment(input));
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            EndpointController.getInstance().getShoppingListEndpoint().updateDatabase(shoppingListEntity);
                            //TODO:Add comment
                            //TODO:Add username to comment
                        }
                        //Need endpoint to finish
                    }});

        return builder.create();
    }
//    public interface AddCommentDialogListener{
//        void applyComment(String description, int position);
//    }
}
