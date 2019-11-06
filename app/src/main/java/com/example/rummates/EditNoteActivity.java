package com.example.rummates;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rummates.fragments.NotesFragment;
import com.example.rummates.fragments.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class EditNoteActivity extends AppCompatActivity {

    int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit note");
        final EditText editText = (EditText) findViewById(R.id.editText);

        Intent i = getIntent();
        postId = i.getIntExtra("postId",  -1);

        if(postId != -1){

            String fillerText = NotesViewModel.posts.get(postId);
            editText.setText(fillerText);

        }

//        editText.addTextChangedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Note updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                NotesViewModel.posts.set(postId, String.valueOf(editText.getText()));
                NotesFragment.arrayAdapter.notifyDataSetChanged();
                if (getSupportActionBar() != null){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
            }
        });
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//
//    }
}
