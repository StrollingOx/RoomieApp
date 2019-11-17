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
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;

import java.util.Objects;

public class AddProductDialog extends DialogFragment {
    private EditText etProduct;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_shoppinglist_addproductdialog,null);
        etProduct = view.findViewById(R.id.sl_add_product);

        builder.setView(view)
                .setTitle("Add Product")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}})
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = etProduct.getText().toString();
                        if(!input.equals("")){
                            ShoppingListEntity shoppingListEntity = EndpointController.getInstance().getShoppingListsForGroup();
                            shoppingListEntity.getLists().get(0).getProducts().add(new Item(input, false));

                            EndpointController.getInstance().getShoppingListEndpoint().updateDatabase(shoppingListEntity);
                        }
                    }});

        return builder.create();
    }

}
