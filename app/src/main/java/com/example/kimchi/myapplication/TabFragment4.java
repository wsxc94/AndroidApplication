package com.example.kimchi.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TabFragment4 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment4, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_news);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

         queue = Volley.newRequestQueue(this.getContext());

         getNews();

        return view;
    }

    public void getNews() {

        String url = "https://newsapi.org/v2/top-headlines?country=kr&category=technology&apiKey=9e093e42924247208ccab101effa7ba9";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("News",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray arrayArticles = jsonObject.getJSONArray("articles");

                            final ArrayList<NewsItem> news = new ArrayList<>();

                            for(int i =0,j= arrayArticles.length(); i< j; i++){
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("News",obj.toString());
                                NewsItem newsItem = new NewsItem();
                                newsItem.setmNewstitle(obj.getString("title"));
                                newsItem.setmImageUrl(obj.getString("urlToImage"));
                                newsItem.setMtext(obj.getString("description"));
                                newsItem.setMpubData(obj.getString("publishedAt"));
                                newsItem.setMlink(obj.getString("url"));
                                news.add(newsItem);

                            }

                            mAdapter = new NewsAdapter(getContext(), news, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Object obj = v.getTag();
                                    if (v.getTag() != null){
                                        int position = (int)obj;
                                        ((NewsAdapter)mAdapter).getNews(position);

                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(position).getMlink()));
                                        intent.setData(Uri.parse(news.get(position).getMlink()));
                                        startActivity(intent);

                                    }
                                }
                            });
                            recyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });
        queue.add(stringRequest);
    }

}

