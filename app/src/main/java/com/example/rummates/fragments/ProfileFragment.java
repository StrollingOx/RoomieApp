package com.example.rummates.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rummates.LoginActivity;
import com.example.rummates.MainActivity;
import com.example.rummates.NotesActivity;
import com.example.rummates.ProfileActivity;
import com.example.rummates.R;

public class ProfileFragment extends Fragment {

    Button leButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_temp_profile, container, false);

        leButton = view.findViewById(R.id.le_button);

        leButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        return view;
    }
}
