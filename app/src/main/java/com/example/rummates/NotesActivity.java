package com.example.rummates;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rummates.controllers.TestController;
import com.example.rummates.entities.testEntity.PostEntity;
import com.example.rummates.entities.testEntity.Post;

public class NotesActivity extends AppCompatActivity {

    private TextView id, title, desc, date, __v;
    private Post Post;
    private PostEntity firstPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_layout);

        id = findViewById(R.id.id_value);
        title = findViewById(R.id.title_value);
        desc = findViewById(R.id.description_value);
        date = findViewById(R.id.date_value);
        __v = findViewById(R.id.__v_value);

        firstPost = TestController.getInstance(getBaseContext()).getFirstPost();

        id.setText(firstPost.get_id());
        title.setText(firstPost.getTitle());
        desc.setText(firstPost.getDescription());
        date.setText(firstPost.getDate());
        __v.setText(firstPost.get__v());



    }
}
