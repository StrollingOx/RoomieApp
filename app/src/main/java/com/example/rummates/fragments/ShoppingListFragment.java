package com.example.rummates.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.errors.ErrorTags;
import com.example.rummates.R;
import com.example.rummates.adapters.ShoppingListAdapter;
import com.example.rummates.controllers.EndpointController;
import com.example.rummates.dialogs.AddProductDialog;
import com.example.rummates.entities.UserEntity;
import com.example.rummates.entities.shoppinglistEntity.Item;
import com.example.rummates.entities.shoppinglistEntity.ShoppingListEntity;
import com.example.rummates.serializer.UserSerializer;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private final String TAG = "ShoppingListFragment";
    private String groupID = ErrorTags.ERROR_NO_GROUP_ID;
    private UserEntity user = null;

    private ArrayList<Item> shoppingList;
    private RecyclerView shoppingListRV;
    private RecyclerView.LayoutManager layoutManager;

    private ShoppingListAdapter shoppingListAdapter;
    private ShoppingListEntity shoppingListEntity;
    Thread tMyLoc;
    private Button addButton, refreshButton;
    private int index;

    View mainView;

    public ShoppingListFragment(int xd){
        index=xd;
    }
    public ShoppingListFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppinglist, container, false);

        getExtras();
        getFirstGroupId();
        updateShoppingList(groupID,index);
        initRecyclerView(view);
        initTestButton(view);
        initRefreshButton(view);
        mainView = view;
        return view;
    }

    private void getExtras() {
        Bundle b = getActivity().getIntent().getExtras();
        if(b != null){
            user = UserSerializer.userDeserializer(getActivity().getIntent().getExtras().getString("user"));
        }
    }

    private void getFirstGroupId() {
        if(user != null)
            if(user.getGroups().size() != 0)
                this.groupID = user.getGroups().get(0).getId();
        else
            groupID = "5dc6ba9c2585a92b30b3fb81";
        //TODO: if no groups -> go to CreateGroup
    }

    private void initTestButton(View view) {
        addButton = (Button) view.findViewById(R.id.button_add_record);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AddProductDialog addProductDialog = new AddProductDialog(groupID);
                addProductDialog.show((getActivity()).getSupportFragmentManager(), "dialog");
            }
        });

        Log.d(TAG, "TestButton initiated");
    }

    private void initRefreshButton(View view) {
        refreshButton = (Button) view.findViewById(R.id.button_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updateShoppingList(groupID,index);
                initRecyclerView(mainView);
            }
        });

        Log.d(TAG, "TestButton initiated");
    }

    //TODO: Refresh adapter periodically

    private void updateShoppingList(String groupID, int index) {
        shoppingListEntity = EndpointController.getInstance(getContext()).getShoppingListsForGroup(groupID);
        if(shoppingList != shoppingListEntity.getLists().get(index).getProducts())
            shoppingList = shoppingListEntity.getLists().get(index).getProducts();

        Log.d(TAG, "ShoppingList Updated");
    }

    private void initRecyclerView(View view){
        shoppingListRV = view.findViewById(R.id.sl_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        shoppingListAdapter = new ShoppingListAdapter(shoppingList, getContext(), groupID, user.getNick());
        shoppingListRV.setLayoutManager(layoutManager);
        shoppingListRV.setAdapter(shoppingListAdapter);
        mainView = view;

         shoppingListAdapter.setOnItemClickListener(new ShoppingListAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 Log.d("TUTUTUTUTUTU", "KUUUUUUUUUUUUUURWAAAAAAAAAAAAAAAAAAAA");
                 updateShoppingList(groupID,index);
                 initRecyclerView(mainView);
             }
         });


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
