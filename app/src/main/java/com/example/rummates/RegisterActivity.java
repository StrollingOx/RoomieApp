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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    Button mButton, cancelButton;
    Context context;
    static String email, password, nick, firstName, lastName, password2;
    private static final String BASE_URL = "https://rumies.herokuapp.com";
    private static final String REGISTER = "/users/register";


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

                sendPost();
               // postNewUser(BASE_URL + REGISTER);
//                try {
//                    postNewUser(BASE_URL+REGISTER);
//                    Toast.makeText(getBaseContext(), "nooo jest git ", Toast.LENGTH_LONG).show();
//                }
//                catch (Exception e){
//                    Toast.makeText(getBaseContext(), "chuj i pizda "+e, Toast.LENGTH_LONG).show();
//                }

                //TODO add logic here, to execute post method with parameters above
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public void sendPost() {
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

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("first_name", "android");
                    jsonParam.put("last_name", "test");
                    jsonParam.put("nick", "androidtest");
                    jsonParam.put("email", "test@test.pl");
                    jsonParam.put("password", "test");
                    jsonParam.put("phone_number", "112233");

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("ENDPOINT", BASE_URL + REGISTER);
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    Log.d("PIZDAAAAAAAA", "CHUJ JEBLO COS " + e);
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


//    protected String postData(String url) {
//        StringBuilder response = new StringBuilder();
//        HttpURLConnection con = null;
//        int responseCode = 0;
//        try {
//            URL obj = new URL(url);
//            con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("first_name", "android");
//            con.setRequestProperty("last_name", "test");
//            con.setRequestProperty("nick", "androidtest");
//            con.setRequestProperty("email", "test@test.pl");
//            con.setRequestProperty("password", "test");
//            con.setRequestProperty("phone_number", "112233");
//            con.setDoOutput(true);
//
//            OutputStream outputPost = new BufferedOutputStream(con.getOutputStream());
//
//            try (Writer w = new OutputStreamWriter(outputPost, "UTF-8")) {
//                w.write("Hello, World!");
//            }
//            outputPost.flush();
//            outputPost.close();
//
//            con.setChunkedStreamingMode(0);
//        } catch (Exception e) {
//            Log.d("ERRORRR", "exception wyjebalo" + e);
//            return "connection-exception" + e;
//        } finally {
//            if (con != null) // Make sure the connection is not null.
//                con.disconnect();
//        }
//
//        if (responseCode == 404) {
//            Log.d("ERRORRR", "ERROOOR 404");
//            return "not-found-exception";
//        }
//
//        Log.d("Internet", "Sending request to URL : " + con.getURL().toString());
//        Log.d("Internet", "Response Code: " + responseCode);
//
//        return response.toString();
//    }


//    public class CallAPI extends AsyncTask<String, String, String> {
//
//        String emailString;
//        String commentString;
//
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            OkHttpClient client = new OkHttpClient();
//            RequestBody formBody = new FormBody.Builder()
//                    .add("first_name", "android")
//                    .add("last_name", "test")
//                    .add("nick", "androidtest")
//                    .add("email", "test@test.pl")
//                    .add("password", "test")
//                    .add("phone_number", "112233")
//                    .build();
//            Request request = new Request.Builder()
//                    .url(BASE_URL+REGISTER) // The URL to send the data to
//                    .post(formBody)
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                System.out.println(response.body().toString());
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }

    private final OkHttpClient client = new OkHttpClient();

    public void postNewUser(String url) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("first_name", "android")
                .add("last_name", "test2")
                .add("nick", "android")
                .add("email", "test2@test2.pl")
                .add("password", "test2")
                .add("phone_number", "1122332")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.d("PIZDAAAAAAAA", "CHUJ JEBLO COS " + response);
                throw new IOException("Unexpected code " + response);
            }

            System.out.println(response.body().string());
        }
    }

}