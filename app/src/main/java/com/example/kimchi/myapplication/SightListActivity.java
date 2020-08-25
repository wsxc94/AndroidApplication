package com.example.kimchi.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class SightListActivity extends AppCompatActivity {
    private AppDataAdapter mAdapter;
    private List<AppData> appDataArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);

        siteData();

        setUpRecyclerView();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appDataArrayList.get(position).getApplink()));
                intent.setData(Uri.parse(appDataArrayList.get(position).getApplink()));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(getActivity(), "길게누르기 " + appDataArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
    private void setUpRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AppDataAdapter(appDataArrayList,this);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    private void siteData(){
        appDataArrayList = new ArrayList<>();
        appDataArrayList.add(new AppData("프로그래머스","https://programmers.co.kr/","https://programmers.co.kr/assets/bi-programmers-light-0d164d49b51a123bab5cca11106145d6fac5a5ac04b8646780369c2a5bc0dd79.png"));
        appDataArrayList.add(new AppData("Baekjoon Online Judge","https://www.acmicpc.net/","https://d2gd6pc034wcta.cloudfront.net/images/logo@2x.png"));
        appDataArrayList.add(new AppData("아방스 게임 개발자 커뮤니티","http://www.avangs.info/","http://www.avangs.info/files/attach/images/1358659/83bf9bb6d5d20fda2c546f194dda8a73.png"));
        appDataArrayList.add(new AppData("센치한개발자 안드로이드","https://www.youtube.com/channel/UCvb9oqG4CtZ5H4m_NSD1GYA","https://yt3.ggpht.com/a-/AAuE7mBKmtiiWQOt0LMzsHrfaB8YST2rN3gGu8RY_Q=s288-mo-c-c0xffffffff-rj-k-no"));
        appDataArrayList.add(new AppData("Coding in Flow","https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA","https://yt3.ggpht.com/a-/AAuE7mDD4LzI5MAbO56Z0FkeVXn5c3HjMAYTak9Oig=s288-mo-c-c0xffffffff-rj-k-no"));
        appDataArrayList.add(new AppData("포프TV 개발자 이야기","https://www.youtube.com/channel/UC63J0Q5huHSlbNT3KxvAaHQ","https://yt3.ggpht.com/a-/AAuE7mDB5_OcYq-VnqQFGTLQ8Der0EQGsXb0uITbPg=s288-mo-c-c0xffffffff-rj-k-no"));
        appDataArrayList.add(new AppData("나우캠퍼스 C/C++","https://www.youtube.com/channel/UCl65-Jpz6rSkrZdxagpHQfg","https://yt3.ggpht.com/a-/AAuE7mBprBhjHIfLIh5l1IFo1bkX1gHV_bdesH0chw=s288-mo-c-c0xffffffff-rj-k-no"));
        appDataArrayList.add(new AppData("생활코딩 HTML 웹강좌","https://www.youtube.com/channel/UCvc8kv-i5fvFTJBFAk6n1SA","https://yt3.ggpht.com/a-/AAuE7mAnhILWq81WH3-g87kRryGELMT0casbUd-tXQ=s288-mo-c-c0xffffffff-rj-k-no"));
        /*
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
*/

    }

}

