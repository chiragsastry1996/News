package com.chirag.news.SavedPager;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chirag.news.NewsStand.NewsStandActivity;
import com.chirag.news.SavedStand.SavedStandActivity;
import com.chirag.news.Splashscreen.SplashScreen;

public class SavedAdapter extends FragmentStatePagerAdapter {

    public static int PAGE_COUNT;

    public SavedAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SavedFragment().setFragmentPosition(position);
    }

    @Override
    public int getCount() {
        PAGE_COUNT = SavedStandActivity.savedNewsList.size();
        return PAGE_COUNT;
    }

}
