package com.example.rummates;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rummates.fragments.ShoppingListFragment;

public class TemporaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();

    }
}
