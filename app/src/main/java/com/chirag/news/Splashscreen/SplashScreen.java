package com.chirag.news.Splashscreen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chirag.news.Database.DatabaseHelper;
import com.chirag.news.Database.HttpHandler;
import com.chirag.news.NewsStand.NewsStandActivity;
import com.chirag.news.R;
import com.chirag.news.SavedStand.SavedStandActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SplashScreen extends AppCompatActivity {
    private String url = "https://infinite-refuge-38409.herokuapp.com/api/all-news";
    public boolean connection = false;

    public static ArrayList<ArrayList<String>> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //checking connection
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());

        newsList = new ArrayList<>();
        new GetTitles().execute();

    }

    //API Request
    public class GetTitles extends AsyncTask<Void, Void, Void> {
        private static final String TAG = "SplashScreen";

        @Override
        public void onPreExecute() {
            super.onPreExecute();
        }

        //GET request done in background
        @Override
        public Void doInBackground(Void... arg0) {
            //Calling the HTTPHandler
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);


            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray news = jsonObj.getJSONArray("news");
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject cards = news.getJSONObject(i);
                        String id = cards.getString("_id");
                        String image = cards.getString("image");
                        String title = cards.getString("title");
                        String uid = cards.getString("uid");

                        ArrayList<String> news_details = new ArrayList<>();
                        news_details.add(id);
                        news_details.add(title);
                        news_details.add(image);
                        news_details.add(uid);

                        newsList.add(news_details);
                    }


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //On UI Thread, perform Front-End tasks
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (connection) {
                        Intent intent = new Intent(SplashScreen.this, NewsStandActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashScreen.this, SavedStandActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }

            });
        }


    }
}