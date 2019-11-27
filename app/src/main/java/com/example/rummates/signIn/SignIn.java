package com.example.rummates.signIn;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SignIn {


    private static String nick, password;
    private static final String BASE_URL = "https://rumies.herokuapp.com";
    private static final String LOGIN = "/users/login";


    public SignIn(String nick, String password){
        this.nick = nick;
        this.password = password;
    }

    public String handleSignIn(String nickName, String passw){

        SignInConnector networkConnector = new SignInConnector();

        String[] params = { nickName, passw};
        String response;
        try {
            response = networkConnector.execute(params).get();
        } catch (ExecutionException e) {
            response = "EXECUTION_EXCEPTION";
        } catch (InterruptedException e) {
            response = "INTERRUPTED_EXCEPTION";
        }
        Log.d("RESPONSE CODE", "LOGIN RESPONSE CODE "+response);
        return response;
    }


    private class SignInConnector extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... objects) {

            String nick = objects[0];
            String password = objects[1];

            StringBuilder res = new StringBuilder();

            HttpURLConnection con = null;
            int responseCode = 0;
            String response="0";
            try {
                URL url = new URL(BASE_URL + LOGIN);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nick", nick);
                jsonParam.put("password", password);


                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Log.i("ENDPOINT", BASE_URL + LOGIN);
                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG", conn.getResponseMessage());

                response = String.valueOf(conn.getResponseCode());
                BufferedReader in = null;
                try {
                    in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        res.append(inputLine);
                    }
                    in.close();
                } catch (Exception e) {
                    return "memory-exception";
                }
                Log.i("BODY", res.toString());
                conn.disconnect();
            } catch (Exception e) {
                Log.d("PIZDAAAAAAAA", "CHUJ JEBLO COS " + e);
                e.printStackTrace();
            }

            if (responseCode == 404) {
                return "404";
            }
            BufferedReader in = null;

            return res.toString();
        }
    }

    //Maybe it should be Async
//    public void sendPost(final String firstName, final String lastName, final String nick, final String email, final String password, final String password2, final Context ctx) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(BASE_URL + REGISTER);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                    conn.setRequestProperty("Accept", "application/json");
//                    conn.setDoOutput(true);
//                    conn.setDoInput(true);
//
//                    JSONObject jsonParam = new JSONObject();
//                    jsonParam.put("first_name", firstName);
//                    jsonParam.put("last_name", lastName);
//                    jsonParam.put("nick", nick);
//                    jsonParam.put("email", email);
//                    jsonParam.put("password", password);
//                    jsonParam.put("phone_number", "90011");
//
//                    Log.i("JSON", jsonParam.toString());
//                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
//                    os.writeBytes(jsonParam.toString());
//
//                    os.flush();
//                    os.close();
//
//                    Log.i("ENDPOINT", BASE_URL + REGISTER);
//                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//                    Log.i("MSG", conn.getResponseMessage());
//                    valid = true;
//
//
//                    conn.disconnect();
//                } catch (Exception e) {
//                    Log.d("PIZDAAAAAAAA", "CHUJ JEBLO COS " + e);
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
//
//    }

}