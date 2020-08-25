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

public class BookListActivity extends AppCompatActivity {
    private AppDataAdapter mAdapter;
    private List<AppData> appDataArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);

        bookData();

        setUpRecyclerView();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {

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

    private void setUpRecyclerView() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

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

    private void bookData() {
        appDataArrayList = new ArrayList<>();
        appDataArrayList.add(new AppData("Do it! 점프 투 파이썬", "http://www.yes24.com/Product/Goods/24567417?scode=032&OzSrank=1", "http://image.yes24.com/Goods/24567417/800x0"));
        appDataArrayList.add(new AppData("윤성우의 열혈 C프로그래밍","http://www.yes24.com/Product/Goods/4333686?scode=032&OzSrank=1","http://image.yes24.com/Goods/4333686/800x0"));
        appDataArrayList.add(new AppData("윤성우의 열혈 C++ 프로그래밍","http://www.yes24.com/Product/Goods/3816661?scode=032&OzSrank=2","http://image.yes24.com/Goods/3816661/800x0"));
        appDataArrayList.add(new AppData("윤성우의 열혈 자료구조","http://www.yes24.com/Product/Goods/6214396?scode=032&OzSrank=3","http://image.yes24.com/Goods/6214396/800x0"));
        appDataArrayList.add(new AppData("윤성우의 열혈 TCP/IP 소켓 프로그래밍","http://www.yes24.com/Product/Goods/3630373?scode=032&OzSrank=4","http://image.yes24.com/Goods/3630373/800x0"));
        appDataArrayList.add(new AppData("윤성우의 열혈 파이썬 기초편","http://www.yes24.com/Product/Goods/67025877?scode=032&OzSrank=5","http://image.yes24.com/Goods/67025877/800x0"));
        appDataArrayList.add(new AppData("Java의 정석","http://www.yes24.com/Product/Goods/24259565?scode=032&OzSrank=4","http://image.yes24.com/Goods/24259565/800x0"));
        appDataArrayList.add(new AppData("안드로이드 with Kotlin 앱 프로그래밍 가이드","http://www.yes24.com/Product/Goods/67883812?scode=032&OzSrank=3","http://image.yes24.com/Goods/67883812/800x0"));
        appDataArrayList.add(new AppData("Do it! 안드로이드 앱 프로그래밍","http://www.yes24.com/Product/Goods/70932159?scode=032&OzSrank=1","http://image.yes24.com/Goods/70932159/800x0"));
        appDataArrayList.add(new AppData("Do it! HTML5+CSS3 웹 표준의 정석","http://www.yes24.com/Product/Goods/34890410?scode=032&OzSrank=1","http://image.yes24.com/Goods/34890410/800x0"));
        appDataArrayList.add(new AppData("생활코딩! HTML+CSS+자바스크립트","http://www.yes24.com/Product/Goods/67883315?scode=032&OzSrank=2","http://image.yes24.com/Goods/67883315/800x0"));
        appDataArrayList.add(new AppData("유니티 게임 프로그래밍 바이블","http://www.yes24.com/Product/Goods/70977188?scode=032&OzSrank=4","http://image.yes24.com/Goods/70977188/800x0"));
        appDataArrayList.add(new AppData("DirectX 12를 이용한 3D 게임 프로그래밍 입문","http://www.yes24.com/Product/Goods/40977256?scode=032&OzSrank=8","http://image.yes24.com/Goods/40977256/800x0"));
        appDataArrayList.add(new AppData("만들면서 배우는 언리얼 게임 프로그래밍","http://www.yes24.com/Product/Goods/17552678?scode=032&OzSrank=12","http://image.yes24.com/momo/TopCate489/MidCate005/48848587.jpg"));
        appDataArrayList.add(new AppData("이것이 C++이다","http://www.yes24.com/Product/Goods/23840775?scode=032&OzSrank=8","http://image.yes24.com/Goods/23840775/800x0"));
        appDataArrayList.add(new AppData("Hello Coding C# 프로그래밍","http://www.yes24.com/Product/Goods/57560258?Acode=101","http://image.yes24.com/Goods/57560258/800x0"));
        /*
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        */




    }
}

