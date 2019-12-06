package com.example.rummates.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.example.rummates.errors.ErrorTags;
import com.example.rummates.R;
import com.example.rummates.entities.UserEntity;
import com.example.rummates.serializer.UserSerializer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView imageView;
    private TextView name, email, nick;
    private RequestQueue mQueue;

    private String groupID = ErrorTags.ERROR_NO_GROUP_ID;
    private UserEntity user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        getExtras();
        getFirstGroupId();
        imageView = view.findViewById(R.id.circle_profile); //TODO: IMAGE CHANGE
        name = view.findViewById(R.id.nick);
        email = view.findViewById(R.id.email);
        nick = view.findViewById(R.id.age);
        //mQueue = Volley.newRequestQueue(getActivity());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });


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


    private void jsonParse() {
        name.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        email.setText("Email: " + user.getEmail());
        nick.setText("Nicka: " + user.getNick());
    }
}
