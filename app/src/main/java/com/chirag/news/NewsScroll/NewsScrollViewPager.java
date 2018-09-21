package com.chirag.news.NewsScroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chirag.news.R;


public class NewsScrollViewPager extends AppCompatActivity {


    ViewPager mViewPager;
    TextView title;
    NewsScrollAdapter newsScrollAdapter;
    String position, news_id;
    public int currentposition;
    public static int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_scroll_view_pager);



        Intent mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();
        if (bundle != null) {
            position = bundle.getString("position");
            news_id = bundle.getString("id");
            System.out.println("PPPPPPPP" + news_id);

        }

        currentposition = Integer.valueOf(position);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        newsScrollAdapter = new NewsScrollAdapter(getSupportFragmentManager());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pos = position;
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        mViewPager.setAdapter(newsScrollAdapter);
        mViewPager.setCurrentItem(currentposition,true);
    }



}
