package com.example.kimchi.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

public class MyProjectActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference projectRef = db.collection("프로젝트");
    FirebaseAuth mAuth;
    private MyProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprojectactivity);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser muser = mAuth.getCurrentUser();
        final String email = muser.getEmail().toString();


        Query query = projectRef.whereEqualTo("userID",email).orderBy("date",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ProjectInfo> options = new FirestoreRecyclerOptions.Builder<ProjectInfo>()
                .setQuery(query,ProjectInfo.class)
                .build();
        adapter = new MyProjectAdapter(options,this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myProject_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyProjectAdapter.OnItemClickListener() {
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
                //Toast.makeText(getActivity(),"Position: " + position + " ID: "+id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyProjectActivity.this,ProjectBoardActivity.class);

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

                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new MyProjectAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyProjectActivity.this);
                dialog.setTitle("게시글 삭제")
                        .setMessage("글을 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                projectRef.document(documentSnapshot.getId()).delete();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                                Toast.makeText(MyProjectActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialog.create();
                dialog.show();


            }
        });

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
