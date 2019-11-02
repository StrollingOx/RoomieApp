package com.example.rummates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // TextView textView;
    Button mButton, cancelButton;
    Context context;
    static String email, password, nick, firstName, lastName, password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        mButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);


        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                EditText firstNameField = (EditText) findViewById(R.id.fName);
                EditText lastNameField = (EditText) findViewById(R.id.lastName);
                EditText nickField = (EditText) findViewById(R.id.nick);
                EditText emailField = (EditText) findViewById(R.id.email);
                EditText passwordField = (EditText) findViewById(R.id.pass);
                EditText password2Field = (EditText) findViewById(R.id.pass2);

                firstName = firstNameField.getText().toString();
                lastName = lastNameField.getText().toString();
                nick = nickField.getText().toString();
                email = emailField.getText().toString();
                password = passwordField.getText().toString();
                password2 = password2Field.getText().toString();

                //TODO add logic here, to execute post method with parameters above
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}