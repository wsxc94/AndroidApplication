package com.example.kimchi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class TabFragment1 extends Fragment{
    private Button btn1;
    private Button btn2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference projectRef = db.collection("프로젝트");

    private ProjectAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        Query query = projectRef.orderBy("date",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ProjectInfo> options = new FirestoreRecyclerOptions.Builder<ProjectInfo>()
                .setQuery(query,ProjectInfo.class)
                .build();
        adapter = new ProjectAdapter(options);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.title_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                ProjectInfo info = documentSnapshot.toObject(ProjectInfo.class);
                String id = documentSnapshot.getId(); // 데이터 아이디
                String title = info.getTitle();
                String day = info.getDate();
                String start1 = info.getStartyears();
                String start2 = info.getStartdate();
                String start3 = info.getStartday();
                String end1 = info.getEndyears();
                String end2 = info.getEnddate();
                String end3 = info.getEndday();
                String pur = info.getPurpose();
                String cham = info.getQualification();
                String path = documentSnapshot.getReference().getPath();
                String image = info.getPicture();
                //Toast.makeText(getActivity(),"Position: " + position + " ID: "+id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),ProjectBoardActivity.class);

                intent.putExtra("project_docuId",id);
                intent.putExtra("project_path",path);
                intent.putExtra("project_title",title);
                intent.putExtra("project_day",day);
                intent.putExtra("project_st1",start1);
                intent.putExtra("project_st2",start2);
                intent.putExtra("project_st3",start3);
                intent.putExtra("project_en1",end1);
                intent.putExtra("project_en2",end2);
                intent.putExtra("project_en3",end3);
                intent.putExtra("project_pur",pur);
                intent.putExtra("project_cham",cham);
                intent.putExtra("project_image",image);

                startActivity(intent);
            }
        });

        btn1 = (Button) view.findViewById(R.id.frag1_button1); // 새로고침 버튼
        btn2 = (Button) view.findViewById(R.id.frag1_button2); // 프로젝트 기획 버튼


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.smoothScrollToPosition(0);


            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent project = new Intent(getActivity(), ProjectActivity.class);
                startActivity(project);
            }
        });

        return view;


    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}