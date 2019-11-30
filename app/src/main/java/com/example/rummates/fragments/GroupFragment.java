package com.example.rummates.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rummates.CreateGroupActivity;
import com.example.rummates.LoginActivity;
import com.example.rummates.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.facebook.FacebookSdk.getApplicationContext;

public class GroupFragment extends Fragment {
    private final String TAG = "GroupFragment";
    SearchView searchView;
    FloatingActionButton addGroup;

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

        return view;
    }

}
