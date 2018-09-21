package com.chirag.news.NewsStand;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chirag.news.Database.DatabaseHelper;
import com.chirag.news.R;
import com.chirag.news.SavedStand.SavedStandActivity;
import com.chirag.news.Splashscreen.SplashScreen;

import java.util.ArrayList;
import java.util.List;

public class NewsStandActivity extends AppCompatActivity {
    private List<NewsStand> newsStandList;
    private NewsStandAdapter newsStandAdapter;
    private RecyclerView recyclerView;
    DatabaseHelper newsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_stand);

        newsStandList = new ArrayList<>();

        newsDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.news_list_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        for (int j = 0; j < SplashScreen.newsList.size(); j++)
            newsStandList.add(new NewsStand(SplashScreen.newsList.get(j).get(0), SplashScreen.newsList.get(j).get(1), SplashScreen.newsList.get(j).get(2), "NULL", "ONLINE"));
        newsStandAdapter = new NewsStandAdapter(NewsStandActivity.this, newsStandList);
        recyclerView.setAdapter(newsStandAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saved_article) {
            Intent intent1 = new Intent(this, SavedStandActivity.class);
            startActivity(intent1);

        } else if(item.getItemId() == R.id.delete_article) {
            newsDb.deleteAllData();
        } else {
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
