package com.example.rummates.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.rummates.R;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.example.rummates.entities.shoppinglistEntity.CommentForItem;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.serializer.ShoppingListSerializer;

import java.util.Objects;

public class AddCommentDialog extends DialogFragment {
    private EditText etComment;
    private String groupID;
    private final int position;

    public AddCommentDialog(int position, String groupID) {
        this.position = position;
        this.groupID = groupID;
    }

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

                            //Get ShoppingList from endpoint
                            ShoppingListEntity shoppingListEntity = EndpointController.getInstance().getShoppingListsForGroup(groupID);
                            //Convert item from Item.class to CommentForItem.class
                            CommentForItem item = new CommentForItem(shoppingListEntity.getLists().get(0).getProducts().get(position).getItemName());
                            item.setListName(shoppingListEntity.getLists().get(0).getListName());
                            item.setDescription(input);
                            item.setUsername("unknown"); //TODO: ADD USERNAME!
                            //Patch to server
                            EndpointController.getInstance().patchShoppingListItemComments(groupID, item);

                            //TODO:Add username to comment
                        }
                    }});

        return builder.create();
    }
}
