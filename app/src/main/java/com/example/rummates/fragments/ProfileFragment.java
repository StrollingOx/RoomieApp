package com.example.rummates.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rummates.MainActivity;
import com.example.rummates.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView imageView;
    private TextView name, email, nick;
    private RequestQueue mQueue;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = view.findViewById(R.id.circle_profile);
        name = view.findViewById(R.id.nick);
        email = view.findViewById(R.id.email);
        nick = view.findViewById(R.id.age);
        mQueue = Volley.newRequestQueue(getActivity());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });


        return view;
    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/16tn3c";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");

                    JSONObject user = jsonArray.getJSONObject(0);

                    String userName = user.getString("first_name");
                    String lastname = user.getString("last_name");
                    String nickk = user.getString("nick");
                    String emaill = user.getString("email");

                    name.setText("Name: " + userName + " " + lastname);
                    email.setText("Email: " + emaill);
                    nick.setText("Nicka: " + nickk);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


}
