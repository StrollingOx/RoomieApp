package com.example.rummates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    Button mButton, cancelButton;
    Context context;
    public static String email, password, nick, firstName, lastName, password2;
    private static final String BASE_URL = "https://rumies.herokuapp.com";
    private static final String REGISTER = "/users/register";

    private final int MIN_PASSWORD = 8;
    private final int MIN_NICK = 6;

    boolean valid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        mButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);
        valid = false;

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

                sendPost(firstName, lastName, nick, email, password, password2,getBaseContext());
                if(valid){
                    Toast.makeText(getBaseContext(), "Wszystko walidne", Toast.LENGTH_LONG).show();
                    //TODO redirect to Michal's user page
                    //startActivity(new Intent(getApplicationContext(), <MICHAL'S PAGE>));
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public boolean stringContainsNumber(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    public boolean validUserInput(final String firstName, final String lastName, final String nick, final String email, final String password, final String password2, final Context ctx) {
        boolean valid = true;
        if (!password2.equals(password)) {
            Log.d("PASSWORD_ERROR", "NIE TAKIE SAME HASLO");
            Toast.makeText(ctx, "NIE TAKIE SAME HASLO", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (password.length() < MIN_PASSWORD) {
            Log.d("PASSWORD_ERROR", "password too short");
            Toast.makeText(ctx, "password too short", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if ( nick.length() < MIN_NICK ) {
            Log.d("PASSWORD_ERROR", "nick too short");
            Toast.makeText(ctx, "nick too short", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if(stringContainsNumber(firstName) || stringContainsNumber(lastName)){
            Log.d("PASSWORD_ERROR", "NAME CAN NOT CONTAIN NUMBERS");
            Toast.makeText(ctx, "NAME CAN NOT CONTAIN NUMBERS", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if(!email.contains("@")){
            Log.d("PASSWORD_ERROR", "email has to have @ sign");
            Toast.makeText(ctx, "email has to have @ sign", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;

}

    //Maybe it should be Async
    public void sendPost(final String firstName, final String lastName, final String nick, final String email, final String password, final String password2, final Context ctx) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL + REGISTER);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    if (validUserInput(firstName, lastName, nick, email, password, password2,ctx)) {
                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("first_name", firstName);
                        jsonParam.put("last_name", lastName);
                        jsonParam.put("nick", nick);
                        jsonParam.put("email", email);
                        jsonParam.put("password", password);
                        jsonParam.put("phone_number", "90011");

                        Log.i("JSON", jsonParam.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                        os.writeBytes(jsonParam.toString());

                        os.flush();
                        os.close();

                        Log.i("ENDPOINT", BASE_URL + REGISTER);
                        Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                        Log.i("MSG", conn.getResponseMessage());
                        valid = true;
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    Log.d("PIZDAAAAAAAA", "CHUJ JEBLO COS " + e);
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

//
//    private class NetworkConnector extends AsyncTask<Object, Void, String> {
//
//        @Override
//        protected String doInBackground(Object... objects) {
//            StringBuilder response = new StringBuilder();
//            HttpURLConnection con = null;
//            int responseCode = 0;
//            try {
//                URL obj = new URL(selector((Integer)objects[0]));
//                con = (HttpURLConnection) obj.openConnection();
//                con.setRequestMethod("GET");
//                con.setRequestProperty("User-Agent", USER_AGENT);
//                responseCode = con.getResponseCode();
//            } catch (Exception e) {
//                return "connection-exception";
//            }
//
//            if (responseCode == 404) {
//                return "not-found-exception";
//            }
//
//            Log.d("Internet", "Sending request to URL : " + con.getURL().toString());
//            Log.d("Internet", "Response Code: " + responseCode);
//            BufferedReader in = null;
//            try {
//                in = new BufferedReader(
//                        new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//            } catch (Exception e) {
//                return "memory-exception";
//            }
//            return response.toString();
//        }
//    }

}