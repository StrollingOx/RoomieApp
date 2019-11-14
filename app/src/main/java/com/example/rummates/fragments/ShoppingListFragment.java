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
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.entities.shoppinglistEntity.Comment;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private ArrayList<Item> shoppingList;
    private RecyclerView shoppingListRV;
    private RecyclerView.LayoutManager layoutManager;
    private ShoppingListAdapter shoppingListAdapter;
    private ShoppingListEntity shoppingListEntity;

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
        shoppingListEntity = EndpointController.getInstance(getContext()).getShoppingListsForGroup();

        shoppingList = new ArrayList<Item>(shoppingListEntity.getLists().get(0).getProducts());
        /*
        shoppingList = new ArrayList<>();
        shoppingList.add(new Item("KREWETKI", true));
        shoppingList.add(new Item("MARCHEWKI", true));
        ArrayList<Comment> aList = new ArrayList<>();
        aList.add(new Comment("Kolega z drużyny niebieskich", "Kiedyś miałem taką sytuacje, że pies nasrał mi pod płotem. (...) ze dupe złoi. O. To jest plan. "));
        aList.add(new Comment("Człowiek(?)", "Jestem człowiekiem."));
        shoppingList.add(new Item("Kość", false, new ArrayList<Comment>(aList)));
        shoppingList.add(new Item("Szynka", false));
        shoppingList.add(new Item("Karkówka", false));
        shoppingList.add(new Item("Parówka", false));

        aList.clear();
        aList.add(new Comment("StrollingOx", "Plz dużo"));
        aList.add(new Comment("StrollingOx", "Plz solone"));
        aList.add(new Comment("StrollingOx", "Plz szybko kurde NO KURDE SZYBKO"));
        shoppingList.add(new Item("Cziperki", true, aList));
        */

    }

    private void initRecyclerView(View view){
        shoppingListRV = view.findViewById(R.id.sl_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        shoppingListAdapter = new ShoppingListAdapter(shoppingList, getContext());
        shoppingListRV.setLayoutManager(layoutManager);
        shoppingListRV.setAdapter(shoppingListAdapter);
    }
}
