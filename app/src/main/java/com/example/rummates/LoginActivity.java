package com.example.rummates;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.RequestOptions;
import com.example.rummates.signIn.SignIn;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private Button registerButton, signInButton;
    String nick,password;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);

        registerButton = findViewById(R.id.register_button);
        signInButton = findViewById(R.id.sign_in_button);



        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email","public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            EditText nickField = (EditText) findViewById(R.id.sign_in_username);
            EditText passwordField = (EditText) findViewById(R.id.sign_in_password);

            nick = nickField.getText().toString();
            password = passwordField.getText().toString();

            SignIn signIn = new SignIn(nick, password);
            String status = signIn.handleSignIn(nick,password);
            Log.d("LoginActivity", status);
            Toast.makeText(getBaseContext(), "Logged in succesfully", Toast.LENGTH_LONG).show();
            if(status.contains("_id")){
                Intent transition = new Intent(LoginActivity.this, MainActivity.class);
                transition.putExtra("user", String.valueOf(status)); //DO NOT FUCKING DELETE IT
                startActivity(transition);
            }
        }
    });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if(currentAccessToken==null) {
                //magicButton.setEnabled(false);
                Toast.makeText(LoginActivity.this,"User Logged out",Toast.LENGTH_LONG).show();
            }
            else {
                loadUserProfile(currentAccessToken);
               //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    Log.d("fbfbfb","here are some info: "+first_name);
                    if (id.length() > 0) {
                        Log.d("FBFBFBFBFBF", "UDALO SIE PANIE " + first_name);

                        String fbNick = first_name + "." + last_name;
                        SignIn signIn = new SignIn(fbNick, fbNick);
                        String status = signIn.handleSignIn(fbNick, fbNick);
                        Log.d("LoginActivity", status);
                        Toast.makeText(getBaseContext(), "Logged in via facebook", Toast.LENGTH_LONG).show();
                        if (status.contains("_id")) {
                            System.out.println("TUTAJ (loadUserProfile) !!!! " +status);
                            Intent transition = new Intent(LoginActivity.this, MainActivity.class);
                            transition.putExtra("user", String.valueOf(status)); //DO NOT FUCKING DELETE IT
                            startActivity(transition);
                        } else {
                            RegisterActivity rg = new RegisterActivity();
                            rg.sendPost(first_name, last_name, fbNick, email, fbNick, fbNick, getBaseContext());
                            Log.d("rejestracja kurła", "UDALO SIE PANIE " + first_name);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        }
                    }
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    //Glide.with(LoginActivity.this).load(image_url).into(circleImageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus()
    {
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }
}
