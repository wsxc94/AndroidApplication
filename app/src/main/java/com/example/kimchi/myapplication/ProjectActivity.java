package com.example.kimchi.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    private EditText editTitle;
    private EditText startyears;
    private EditText startMonth;
    private EditText startDays;
    private EditText endyears;
    private EditText endMonth;
    private EditText endDays;
    private EditText purpose;
    private TextView hashtag1;
    private TextView hashtag2;
    private TextView hashtag3;
    private TextView hashtag4;
    private EditText qualification;
    private ImageView picture;
    private Uri imageUri;
    private String image;
    private boolean imageY = false;
    private static final int PICK_FROM_ALBUM = 10;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private EditText editHash;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionReference projectRef = db.collection("프로젝트");
    FirebaseAuth mAuth;
    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        storage = FirebaseStorage.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }

        final StorageReference storageRef = storage.getReferenceFromUrl("gs://startingproject-d3f0e.appspot.com");

        editTitle = findViewById(R.id.Project_titleText);

        startyears = findViewById(R.id.Project_startYear);
        startMonth = findViewById(R.id.Project_startMonth);
        startDays = findViewById(R.id.Project_startDay);

        endyears = findViewById(R.id.Project_endYear);
        endMonth = findViewById(R.id.Project_endMonth);
        endDays = findViewById(R.id.Project_endDay);

        purpose = findViewById(R.id.Project_purpose);
        qualification = findViewById(R.id.Project_qualification);
        picture = (ImageView) findViewById(R.id.Project_Image);

        hashtag1 = findViewById(R.id.Project_hashTag1);
        hashtag2 = findViewById(R.id.Project_hashTag2);
        hashtag3 = findViewById(R.id.Project_hashTag3);
        hashtag4 = findViewById(R.id.Project_hashTag4);

        editHash = findViewById(R.id.Project_EditHashTag);

        btn2 = findViewById(R.id.Project_hashTagAdd);
        btn2.setOnClickListener(new View.OnClickListener() { //해시태그 추가
            @Override
            public void onClick(View v) {
                  hashtag1.setText(editHash.getText().toString());
            }
        });


        picture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent  = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);

    }
});
        btn1 = (Button) findViewById(R.id.Project_Button);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addProject(v,storageRef);

            }
        });

    }

    public void addProject(View v,StorageReference storageRef){
     final String title = editTitle.getText().toString();
     final String syears = startyears.getText().toString();
     final String smonth = startMonth.getText().toString();
     final String sdays = startDays.getText().toString();
     final String eyear = endyears.getText().toString();
     final String emonth = endMonth.getText().toString();
     final String eDays = endDays.getText().toString();
     final String pur = purpose.getText().toString();
     final String qul = qualification.getText().toString();
     final String htag1 = hashtag1.getText().toString();
     final String htag2 = hashtag2.getText().toString();
     final String htag3 = hashtag3.getText().toString();
     final String htag4 = hashtag4.getText().toString();
     final long time = System.currentTimeMillis();
     final Date day = new Date(time);

        if (title.isEmpty() || imageY == false || pur.isEmpty() || qul.isEmpty()) {
            // 이메일 공백
            Toast.makeText(this, "텍스트와 사진을 입력해주세요.", Toast.LENGTH_SHORT).show();

        }else {


            mAuth = FirebaseAuth.getInstance();
            FirebaseUser muser = mAuth.getCurrentUser();
            final String user = muser.getEmail().toString();
            String uid = muser.getUid();
            image = imageUri.toString();

            ProjectInfo projectInfo = new ProjectInfo(user, title, syears, smonth, sdays, eyear, emonth, eDays, pur, qul, image, htag1, htag2, htag3, htag4, time, mFormat.format(day));

            projectRef.add(projectInfo);

            upload(storageRef);

            Toast.makeText(ProjectActivity.this, "프로젝트가 등록되었습니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ProjectActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_FROM_ALBUM){
            imageY = true;
            imageUri = Uri.fromFile(new File(getPath(data.getData())));
            Picasso.with(this).load(imageUri).into(picture);
        }
    }
   public void upload(StorageReference storageRef){
       StorageReference fileRef = storageRef.child("images/"+imageUri.getLastPathSegment());

       UploadTask uploadTask = fileRef.putFile(imageUri);
       uploadTask.addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {

           }
       }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

           }
       });

   }
    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);

    }


}
