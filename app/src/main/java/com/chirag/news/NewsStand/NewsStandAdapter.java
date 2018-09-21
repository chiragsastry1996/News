package com.chirag.news.NewsStand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chirag.news.NewsScroll.NewsScrollViewPager;
import com.chirag.news.R;
import com.chirag.news.SavedPager.SavedPager;


import java.util.List;


public class NewsStandAdapter extends RecyclerView.Adapter<NewsStandAdapter.MyViewHolder>{

    private Context mContext;
    List<NewsStand> newsStandList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;
        public Button onClickListener;


        public MyViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.newsImage);
            onClickListener = (Button)view.findViewById(R.id.onClickListener);


        }
    }

    public NewsStandAdapter(Context mContext, List<NewsStand> newsList) {
        this.mContext = mContext;
        this.newsStandList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NewsStand newsStand = newsStandList.get(position);
        holder.titleTextView.setText(newsStand.getName());
        Glide.with(mContext).load(newsStand.getImage()).into(holder.imageView);
        holder.onClickListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newsStand.getState().equals("ONLINE")){
                    Intent intent1 = new Intent(mContext, NewsScrollViewPager.class);
                    intent1.putExtra("position", String.valueOf(holder.getAdapterPosition()));
                    intent1.putExtra("id",String.valueOf(newsStand.get_id()));
                    mContext.startActivity(intent1);
                }
                else if (newsStand.getState().equals("OFFLINE")){
                    Intent intent1 = new Intent(mContext, SavedPager.class);
                    intent1.putExtra("position", String.valueOf(holder.getAdapterPosition()));
                    mContext.startActivity(intent1);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return newsStandList.size();
    }

}
