package com.chirag.news.SavedStand;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chirag.news.Database.DatabaseHelper;
import com.chirag.news.R;

import java.util.ArrayList;
import java.util.List;

public class SavedStandActivity extends AppCompatActivity {

    private List<SavedStand> savedStandList;
    private SavedStandAdapter savedStandAdapter;
    private RecyclerView recyclerView;
    public static ArrayList<ArrayList<String>> savedNewsList;
    DatabaseHelper newsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_stand);

        newsDb = new DatabaseHelper(this);

        savedStandList = new ArrayList<>();
        savedNewsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.news_list_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        viewAll();

    }

    public void viewAll() {
        Cursor res = newsDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "No Saved Articles Found");
            return;
        }

//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()) {
//            buffer.append("Id :" + res.getString(0) + "\n");
//            buffer.append("TITLE :" + res.getString(1) + "\n");
//            buffer.append("IMAGE :" + res.getString(2) + "\n");
//            buffer.append("CONTENT :" + res.getString(3) + "\n\n");

        if (!savedStandList.isEmpty())
            savedStandList.clear();

        while (res.moveToNext()){
            ArrayList<String> temp = new ArrayList<>();
            savedStandList.add(new SavedStand(res.getString(0), res.getString(1), res.getString(2), res.getString(3), "OFFLINE"));
            temp.add(res.getString(0));
            temp.add(res.getString(1));
            temp.add( res.getString(2));
            temp.add(res.getString(3));
            savedNewsList.add(temp);
        }

        System.out.println("SSSSSSSS" + savedNewsList);

        savedStandAdapter = new SavedStandAdapter(SavedStandActivity.this, savedStandList);
        recyclerView.setAdapter(savedStandAdapter);




        // Show all data
//        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
