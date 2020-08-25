package com.example.kimchi.myapplication;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TabFragment2 extends Fragment {
    ViewGroup btn1;
    ViewGroup btn2;
    ViewGroup btn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);


        btn1 = (ViewGroup) view.findViewById(R.id.frag2_button1); // APP추천 버튼
        btn2 = (ViewGroup) view.findViewById(R.id.frag2_button2); // 교재추천 버튼
        btn3 = (ViewGroup) view.findViewById(R.id.frag2_button3); // 사이트 추천 버튼

        btn1.setOnClickListener(new View.OnClickListener() //앱 추천
        {
            @Override
            public void onClick(View v)
            {

                Intent project = new Intent(getActivity(),ListActivity.class);
                startActivity(project);


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() //교재 추천
        {
            @Override
            public void onClick(View v)
            {

                Intent project = new Intent(getActivity(),BookListActivity.class);
                startActivity(project);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent project = new Intent(getActivity(),SightListActivity.class);
                startActivity(project);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}

