package com.chirag.news.SavedPager;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chirag.news.R;

public class SavedPager extends AppCompatActivity {

    ViewPager mViewPager;
    SavedAdapter savedAdapter;
    String position;
    public int currentposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_pager);

        Intent mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();
        if (bundle != null) {
            position = bundle.getString("position");
        }

        currentposition = Integer.valueOf(position);

        mViewPager = (ViewPager) findViewById(R.id.view_pager_saved);
        savedAdapter = new SavedAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(savedAdapter);
        mViewPager.setCurrentItem(currentposition, true);
    }
}
