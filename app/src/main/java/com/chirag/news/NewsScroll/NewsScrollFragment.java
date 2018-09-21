package com.chirag.news.NewsScroll;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chirag.news.Database.DatabaseHelper;
import com.chirag.news.Database.HttpHandler;
import com.chirag.news.R;
import com.chirag.news.SavedStand.SavedStandActivity;
import com.chirag.news.Splashscreen.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NewsScrollFragment extends Fragment {

    View view;
    DatabaseHelper newsDb;
    private String url = "https://infinite-refuge-38409.herokuapp.com/api/news/";
    TextView textView_title,textView_body;
    ImageView imageView;
    FloatingActionButton download;
    CoordinatorLayout coordinatorLayout;
    public static String news_id;
    public boolean connection = false;
    int position;
    private static String title, body;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scroll_fragment_layout, container, false);
        newsDb = new DatabaseHelper(getContext());
        imageView = (ImageView)view.findViewById(R.id.scroll_imageView);
        textView_title=(TextView)view.findViewById(R.id.textView_title);
        textView_body=(TextView)view.findViewById(R.id.textView_body);
        download = (FloatingActionButton) view.findViewById(R.id.Download);
        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator);


        setUpFragmentData();
        return view;
    }

    public void setUpFragmentData() {

        news_id = SplashScreen.newsList.get(position).get(0);
        Glide.with(getContext()).load(SplashScreen.newsList.get(position).get(2)).into(imageView);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
        if(connection)
            new GetDetails().execute();
        else {
            textView_title.setText(SplashScreen.newsList.get(position).get(1));
            textView_body.setText("No Internet Connection. Please switch on Internet or click here to goto Saved Articles");
            textView_body.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SavedStandActivity.class);
                    startActivity(intent);
                }
            });
        }


    }

    public Fragment setFragmentPosition(int position) {
        this.position = position;
        return this;
    }

    public class GetDetails extends AsyncTask<Void, Void, Void> {
        private static final String TAG = "NewsScrollViewPager";
        String content = "NULL";

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
            String jsonStr = sh.makeServiceCall(url+news_id );



            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray news = jsonObj.getJSONArray("news");

                        JSONObject cards = news.getJSONObject(0);
                        content = cards.getString("content");

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //On UI Thread, perform Front-End tasks
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView_title.setText(SplashScreen.newsList.get(position).get(1));
                    textView_body.setText(content);
                    download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            newsDb.insertData((SplashScreen.newsList.get(position).get(0)), SplashScreen.newsList.get(position).get(1),SplashScreen.newsList.get(position).get(2),content,SplashScreen.newsList.get(position).get(3));
                            Snackbar snackbar = Snackbar
                                    .make(coordinatorLayout, "Article saved for Offline Section", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    });


                }

            });
        }


    }

}