package com.example.kimchi.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MyfreeBoard extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference freeRef = db.collection("자유게시판");
    FirebaseAuth mAuth;
    private MyFreeboradAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfreeboardactivity);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser muser = mAuth.getCurrentUser();
        final String email = muser.getEmail().toString();


        Query query = freeRef.whereEqualTo("memail",email).orderBy("mdate",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<FreeBoradData> options = new FirestoreRecyclerOptions.Builder<FreeBoradData>()
                .setQuery(query,FreeBoradData.class)
                .build();
        adapter = new MyFreeboradAdapter(options,this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myfreeboard_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyFreeboradAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                FreeBoradData info = documentSnapshot.toObject(FreeBoradData.class);
                String id = documentSnapshot.getId(); // 데이터 아이디

                Intent intent = new Intent(MyfreeBoard.this,Freeborad.class);

                intent.putExtra("free_docuId",id);


                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new MyFreeboradAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final DocumentSnapshot documentSnapshot, int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyfreeBoard.this);
                dialog.setTitle("게시글 삭제")
                        .setMessage("글을 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                freeRef.document(documentSnapshot.getId()).delete();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                                Toast.makeText(MyfreeBoard.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
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
