package com.example.kimchi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TabFragment3 extends Fragment {
 private Button btn1;
 private Button btn2;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference freeRef = db.collection("자유게시판");

    private FreeboradAdapter madapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment3, container, false);

        btn1 = (Button) view.findViewById(R.id.frag3_button1); // 새로고침 버튼
        btn2 = (Button) view.findViewById(R.id.frag3_button2); // 프로젝트 기획 버튼

        Query query = freeRef.orderBy("mdate",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FreeBoradData> options = new FirestoreRecyclerOptions.Builder<FreeBoradData>()
                .setQuery(query,FreeBoradData.class)
                .build();
        madapter = new FreeboradAdapter(options);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.free_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(madapter);

        madapter.setOnItemClickListener(new FreeboradAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                FreeBoradData info = documentSnapshot.toObject(FreeBoradData.class);
                String id = documentSnapshot.getId(); // 데이터 아이디
                //String title = info.getMtitle();
                //String day = info.getMdate();

                Intent intent = new Intent(getActivity(),Freeborad.class);

                intent.putExtra("free_docuId",id);
                //intent.putExtra("free_title",title);
                //intent.putExtra("free_day",day);

                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent project = new Intent(getActivity(), FreeboradActivity.class);
                startActivity(project);
            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        madapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        madapter.stopListening();
    }


}
