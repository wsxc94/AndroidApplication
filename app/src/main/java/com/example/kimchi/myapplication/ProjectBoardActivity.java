package com.example.kimchi.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Transaction;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectBoardActivity extends AppCompatActivity {
    private CommentAdapter mAdapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;

    private static final String KEY_DATE = "date";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ENDDATE = "enddate";
    private static final String KEY_ENDDAY = "endday";
    private static final String KEY_ENDYEAR = "endyears";
    private static final String KEY_PURPOSE = "purpose";
    private static final String KEY_QUALIFICATION = "qualification";
    private static final String KEY_STDATE = "startdate";
    private static final String KEY_STDAY = "startday";
    private static final String KEY_STYEAR = "startyears";
    private static final String KEY_EMAIL = "userID";
    private static final String KEY_IMAGE = "picture";

    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_broad);

        final Intent intent = getIntent();
        final TextView muserid = findViewById(R.id.borad_id);
        final TextView mtitle = findViewById(R.id.borad_title);
        final TextView mday = findViewById(R.id.borad_time);
        final TextView mstYear = findViewById(R.id.borad_starYear);
        final TextView mstMon = findViewById(R.id.borad_starMon);
        final TextView mstDay = findViewById(R.id.borad_starDay);
        final TextView menYear = findViewById(R.id.borad_endYear);
        final TextView menMon = findViewById(R.id.borad_endMon);
        final TextView menDay = findViewById(R.id.borad_endDay);
        final TextView mpur = findViewById(R.id.borad_purpose);
        final TextView mQu = findViewById(R.id.borad_cham);
        final ImageView imageView = findViewById(R.id.borad_image);
        //TextView mpath = findViewById(R.id.borad_path); 경로

        final String docuId = intent.getStringExtra("project_docuId");
        final String path = intent.getStringExtra("project_path"); //경로

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser muser = mAuth.getCurrentUser();
        final String email = muser.getEmail().toString();

        Query query = db.collection("프로젝트").document(docuId).collection("comment").orderBy("mdate");
        FirestoreRecyclerOptions<CommentData> options = new FirestoreRecyclerOptions.Builder<CommentData>()
                .setQuery(query,CommentData.class)
                .build();
        mAdapter = new CommentAdapter(options,this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comment_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        db.collection("프로젝트").document(docuId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String title = documentSnapshot.getString(KEY_TITLE);
                    String day = documentSnapshot.getString(KEY_DATE);
                    String enddate = documentSnapshot.getString(KEY_ENDDATE);
                    String endday = documentSnapshot.getString(KEY_ENDDAY);
                    String endyear = documentSnapshot.getString(KEY_ENDYEAR);
                    String stdate = documentSnapshot.getString(KEY_STDATE);
                    String stday = documentSnapshot.getString(KEY_STDAY);
                    String styear = documentSnapshot.getString(KEY_STYEAR);
                    String pur = documentSnapshot.getString(KEY_PURPOSE);
                    String qu = documentSnapshot.getString(KEY_QUALIFICATION);
                    String email = documentSnapshot.getString(KEY_EMAIL);
                    String image = documentSnapshot.getString(KEY_IMAGE);


                     //나머지 키값 셋해주기.
                    mtitle.setText(title);
                    mday.setText(day);
                    mstYear.setText(styear);
                    mstMon.setText(stdate);
                    mstDay.setText(stday);
                    menYear.setText(endyear);
                    menMon.setText(enddate);
                    menDay.setText(endday);
                    mpur.setText(pur);
                    mQu.setText(qu);
                    muserid.setText(email);
                    imageView.setImageURI(Uri.parse(image));



                }else{
                    Toast.makeText(ProjectBoardActivity.this, "Not Data", Toast.LENGTH_SHORT).show();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        final TextView comment = findViewById(R.id.comment_text);
        Button send = findViewById(R.id.comment_send);
        final EditText editText = findViewById(R.id.comment_edit_message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time = System.currentTimeMillis();
                Date day = new Date(time);
              String text = editText.getText().toString();
              CommentData commentData = new CommentData(docuId,text,mFormat.format(day),email);
                db.collection("프로젝트").document(docuId).collection("comment").document().set(commentData);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);



            }
        });
        mAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final DocumentSnapshot documentSnapshot, int position) {

                CommentData commentData = documentSnapshot.toObject(CommentData.class);
                String getKeyEmail = commentData.getmEmail().toString();

                if (email.equals(getKeyEmail)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ProjectBoardActivity.this);
                    dialog.setTitle("댓글 삭제")
                            .setMessage("댓글을 삭제하시겠습니까?")
                            .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.collection("프로젝트").document(docuId).collection("comment").document(documentSnapshot.getId()).delete();
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(getIntent());
                                    overridePendingTransition(0, 0);
                                    Toast.makeText(ProjectBoardActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
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
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

}
