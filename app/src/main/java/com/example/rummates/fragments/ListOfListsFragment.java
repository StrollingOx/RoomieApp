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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.adapters.ListOfListsAdapter;
import com.example.rummates.entities.shoppinglistEntity.ShoppingList;
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

public class ListOfListsFragment extends Fragment {

    private final String TAG = "ShoppingListFragment";
    private String groupID = ErrorTags.ERROR_NO_GROUP_ID;
    private UserEntity user = null;

    private ArrayList<ShoppingList> shoppingList;
    private RecyclerView shoppingListRV;
    private RecyclerView.LayoutManager layoutManager;

    private ListOfListsAdapter shoppingListAdapter;
    private ShoppingListEntity shoppingListEntity;

    private Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_lists, container, false);

        getExtras();
        getFirstGroupId();
        updateShoppingList(groupID);
        initRecyclerView(view);

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


    //TODO: Refresh adapter periodically

    private void updateShoppingList(String groupID) {
        shoppingListEntity = EndpointController.getInstance(getContext()).getShoppingListsForGroup(groupID);
        if(shoppingList != shoppingListEntity.getLists())
            shoppingList = shoppingListEntity.getLists();

        Log.d(TAG, "ShoppingList Updated");
    }

    private void initRecyclerView(View view){
        shoppingListRV = view.findViewById(R.id.lists_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        shoppingListAdapter = new ListOfListsAdapter(shoppingList, getContext(), groupID);
        shoppingListRV.setLayoutManager(layoutManager);
        shoppingListRV.setAdapter(shoppingListAdapter);

        Log.d(TAG, "RecyclerView initiated");

        shoppingListAdapter.setOnItemClickListener(new ListOfListsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int a) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();
                Log.d("TUTUTUTUTUTU", "KLIKNIENTO LKINIETNO");
                Fragment fragment = new ShoppingListFragment(a);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
    }


}
