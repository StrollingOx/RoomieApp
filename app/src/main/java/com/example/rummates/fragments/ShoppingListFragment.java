package com.example.rummates.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.MainActivity;
import com.example.rummates.R;
import com.example.rummates.adapters.ShoppingListAdapter;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.dialogs.AddProductDialog;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private final String TAG = "ShoppingListFragment";

    private ArrayList<Item> shoppingList;
    private RecyclerView shoppingListRV;
    private RecyclerView.LayoutManager layoutManager;

    private ShoppingListAdapter shoppingListAdapter;
    private ShoppingListEntity shoppingListEntity;

    private Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppinglist, container, false);

        updateShoppingList();
        initRecyclerView(view);
        initTestButton(view);

        return view;
    }

    private void initTestButton(View view) {
        addButton = (Button) view.findViewById(R.id.button_add_record);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AddProductDialog addProductDialog = new AddProductDialog();
                addProductDialog.show((getActivity()).getSupportFragmentManager(), "dialog");
            }
        });

        Log.d(TAG, "TestButton initiated");
    }

    //TODO: Refresh adapter periodically

    private void updateShoppingList() {
        shoppingListEntity = EndpointController.getInstance(getContext()).getShoppingListsForGroup();
        if(shoppingList != shoppingListEntity.getLists().get(0).getProducts())
            shoppingList = shoppingListEntity.getLists().get(0).getProducts();

        Log.d(TAG, "ShoppingList Updated");
    }

    private void initRecyclerView(View view){
        shoppingListRV = view.findViewById(R.id.sl_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        shoppingListAdapter = new ShoppingListAdapter(shoppingList, getContext());
        shoppingListRV.setLayoutManager(layoutManager);
        shoppingListRV.setAdapter(shoppingListAdapter);

        Log.d(TAG, "RecyclerView initiated");
    }
}

//on item click implementation
/*
 *shoppingListAdapter.setOnItemClickListener(new ShoppingListAdapter.OnItemClickListener() {
 *    @Override
 *    public void onItemClick(int position) {
 *
 *    }
 *});
 */
