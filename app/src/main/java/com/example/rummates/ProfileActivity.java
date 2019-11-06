package com.example.rummates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Button menu;
    private CircleImageView imageView;
    private TextView name, email, nick;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        imageView = findViewById(R.id.circle_profile);
        name = findViewById(R.id.nick);
        email = findViewById(R.id.email);
        nick = findViewById(R.id.age);


        mQueue = Volley.newRequestQueue(this);



        menu = (Button) findViewById(R.id.menu);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
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

    public void openProfileActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
