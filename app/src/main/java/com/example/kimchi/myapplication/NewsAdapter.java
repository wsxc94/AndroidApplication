package com.example.kimchi.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context mContext;
    private ArrayList<NewsItem> mNewsList;
    private View.OnClickListener onClickListener;

     public NewsAdapter(Context context , ArrayList<NewsItem> newsList, View.OnClickListener onClick){
           mContext = context;
           mNewsList  = newsList;
           onClickListener = onClick;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item, viewGroup,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
          NewsItem currentItem = mNewsList.get(i);

          String imageUrl = currentItem.getmImageUrl();
          String titleName = currentItem.getmNewstitle();
          String links = currentItem.getMlink();
          String text = currentItem.getMtext();
          String pubDate = currentItem.getMpubData();

          newsViewHolder.mtitle.setText(titleName);
          newsViewHolder.newstext.setText(text);
          newsViewHolder.mPubDate.setText("날짜: "+pubDate);
          Picasso.with(mContext).load(imageUrl).fit().centerInside().into(newsViewHolder.mImageView);
          newsViewHolder.rootView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder

    {

        public ImageView mImageView;
        public TextView mtitle;
        public TextView mPubDate;
        public TextView newstext;
        public View rootView;

        public NewsViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.news_image);
        mtitle = itemView.findViewById(R.id.news_title);
        newstext = itemView.findViewById(R.id.news_text);
        mPubDate = itemView.findViewById(R.id.news_date);
        rootView = itemView;
        itemView.setClickable(true);
        itemView.setEnabled(true);
        itemView.setOnClickListener(onClickListener);
    }
    }
    public NewsItem getNews(int position){
         return mNewsList != null ? mNewsList.get(position) : null;
    }
}