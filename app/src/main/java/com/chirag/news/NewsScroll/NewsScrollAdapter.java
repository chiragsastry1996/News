package com.chirag.news.NewsScroll;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chirag.news.Splashscreen.SplashScreen;

public class NewsScrollAdapter extends FragmentStatePagerAdapter {

    public static int PAGE_COUNT;

    public NewsScrollAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new NewsScrollFragment().setFragmentPosition(position);
    }

    @Override
    public int getCount() {
        PAGE_COUNT = SplashScreen.newsList.size();
        return PAGE_COUNT;
    }

}
