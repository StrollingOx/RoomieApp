package com.example.rummates.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.R;
import com.example.rummates.adapters.ShoppingListAdapter;
import com.example.rummates.classes.Item;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private ArrayList<Item> shoppingList = new ArrayList<>();
    private RecyclerView shoppingListRV;
    private RecyclerView.LayoutManager layoutManager;
    private ShoppingListAdapter shoppingListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppinglist, container, false);

        initShoppingList();
        initRecyclerView(view);

        //TODO:On item click implementation
        /*
        *shoppingListAdapter.setOnItemClickListener(new ShoppingListAdapter.OnItemClickListener() {
        *    @Override
        *    public void onItemClick(int position) {
        *
        *    }
        *});
        */
        return view;
    }

    private void initShoppingList() {
        shoppingList.add(new Item("KREWETKI", true));
        shoppingList.add(new Item("MARCHEWKI", true));
        shoppingList.add(new Item("Kość", false));
        shoppingList.add(new Item("Szynka", false));
        shoppingList.add(new Item("Karkówka", false));
        shoppingList.add(new Item("Parówka", false));
    }

    private void initRecyclerView(View view){
        shoppingListRV = view.findViewById(R.id.sl_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        shoppingListAdapter = new ShoppingListAdapter(shoppingList);
        shoppingListRV.setLayoutManager(layoutManager);
        shoppingListRV.setAdapter(shoppingListAdapter);
    }
}
