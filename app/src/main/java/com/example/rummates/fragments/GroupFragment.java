package com.example.rummates.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rummates.CreateGroupActivity;
import com.example.rummates.LoginActivity;
import com.example.rummates.R;
import com.example.rummates.adapters.GroupsAdapter;
import com.example.rummates.entities.UserEntity;
import com.example.rummates.entities.groupEntity.GroupEntity;
import com.example.rummates.serializer.UserSerializer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class GroupFragment extends Fragment {
    private final String TAG = "GroupFragment";
    private SearchView searchView;
    private FloatingActionButton addGroup;
    private String groupID;
    private UserEntity user;


    private ArrayList<GroupEntity> listOfGroups;
    private RecyclerView groupsRV;
    private RecyclerView.LayoutManager layoutManager;

    private GroupsAdapter groupsAdapter;
    private GroupEntity groupEntity;


    private Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_manager, container, false);

        searchView = (SearchView)view.findViewById(R.id.searcher);
        addGroup = (FloatingActionButton)view.findViewById((R.id.add_group_button));
        addGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateGroupActivity.class));
            }
        });


        getExtras();
        getFirstGroupId();
        getGroupsForCurrentUser();
        initRecyclerView(view);




        return view;
    }

    private void getGroupsForCurrentUser() {
        //listOfGroups = TODO: get list from endpoint
        listOfGroups = new ArrayList<>();
        listOfGroups.add(new GroupEntity("local_group_0"));
        listOfGroups.add(new GroupEntity("local_group_1"));
        listOfGroups.add(new GroupEntity("local_group_2"));
        listOfGroups.add(new GroupEntity("local_group_3"));
        listOfGroups.add(new GroupEntity("local_group_4"));
        listOfGroups.add(new GroupEntity("local_group_5"));
        listOfGroups.add(new GroupEntity("local_group_6"));
        listOfGroups.add(new GroupEntity("local_group_7"));
        listOfGroups.add(new GroupEntity("local_group_8"));
        listOfGroups.add(new GroupEntity("local_group_9"));
    }

    private void initRecyclerView(View view) {
        groupsRV = view.findViewById(R.id.groups_rv);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        groupsAdapter = new GroupsAdapter(listOfGroups, getContext());
        groupsRV.setLayoutManager(layoutManager);
        groupsRV.setAdapter(groupsAdapter);

        Log.d(TAG, "RecyclerView initiated");
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

}
