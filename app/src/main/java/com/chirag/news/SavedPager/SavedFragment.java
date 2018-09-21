package com.chirag.news.SavedPager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chirag.news.NewsStand.NewsStandActivity;
import com.chirag.news.R;
import com.chirag.news.SavedStand.SavedStandActivity;


public class SavedFragment extends Fragment {

    View view;
    TextView textView_title, textView_body;
    ImageView imageView;
    int position;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.saved_fragment_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.scroll_imageView);
        textView_title = (TextView) view.findViewById(R.id.textView_title);
        textView_body = (TextView) view.findViewById(R.id.textView_body);


        setUpFragmentData();
        return view;
    }

    public void setUpFragmentData() {

        Glide.with(getContext()).load(SavedStandActivity.savedNewsList.get(position).get(2)).into(imageView);
        textView_title.setText(SavedStandActivity.savedNewsList.get(position).get(1));
        textView_body.setText(SavedStandActivity.savedNewsList.get(position).get(3));

    }

    public android.support.v4.app.Fragment setFragmentPosition(int position) {
        this.position = position;
        return this;
    }
}
