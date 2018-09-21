package com.chirag.news.SavedStand;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chirag.news.NewsScroll.NewsScrollViewPager;
import com.chirag.news.NewsStand.NewsStandAdapter;
import com.chirag.news.R;
import com.chirag.news.SavedPager.SavedPager;

import java.util.List;

public class SavedStandAdapter extends RecyclerView.Adapter<SavedStandAdapter.MyViewHolder> {
    private Context mContext;
    List<SavedStand> savedStandList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;
        public Button onClickListener;


        public MyViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.newsImage);
            onClickListener = (Button) view.findViewById(R.id.onClickListener);


        }
    }

    public SavedStandAdapter(Context mContext, List<SavedStand> savedStandList) {
        this.mContext = mContext;
        this.savedStandList = savedStandList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SavedStand savedStand = savedStandList.get(position);
        holder.titleTextView.setText(savedStand.getName());
        Glide.with(mContext).load(savedStand.getImage()).into(holder.imageView);
        holder.onClickListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Online or Offline, should move to respective activities
                if (savedStand.getState().equals("ONLINE")) {
                    Intent intent1 = new Intent(mContext, NewsScrollViewPager.class);
                    intent1.putExtra("position", String.valueOf(holder.getAdapterPosition()));
                    intent1.putExtra("id", String.valueOf(savedStand.get_id()));
                    mContext.startActivity(intent1);
                } else if (savedStand.getState().equals("OFFLINE")) {
                    Intent intent1 = new Intent(mContext, SavedPager.class);
                    intent1.putExtra("position", String.valueOf(holder.getAdapterPosition()));
                    mContext.startActivity(intent1);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return savedStandList.size();
    }

}

