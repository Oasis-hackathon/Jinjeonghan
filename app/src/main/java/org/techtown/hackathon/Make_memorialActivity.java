package org.techtown.hackathon;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

public class Make_memorialActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 1;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage mStorage;
    private String back_color;

    private File tempFile;
    private Uri imageUri;
    private String pathUri;
    ImageView change_profile;
    public String profile_url;

    public class Make {
        public String d_name;
        public String d_brith;
        public String d_day;
        public String phrases;
        public String song;
        public String color;
        public String image_url;

        public Make(String d_name, String d_brith, String d_day, String phrases,
                     String song, String color, String image_url) {
            this.d_name = d_name;
            this.d_brith = d_brith;
            this.d_day = d_day;
            this.phrases = phrases;
            this.song = song;
            this.color = color;
            this.image_url = image_url;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_memorial);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");

        Button backbtn = findViewById(R.id.go_back);
        backbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // 배경 색상
        Button btn_white = findViewById(R.id.btn_white);
        Button btn_yellow = findViewById(R.id.btn_yellow);
        Button btn_green = findViewById(R.id.btn_green);

        btn_white.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                back_color = "white";
            }
        });
        btn_yellow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                back_color = "yellow";
            }
        });
        btn_green.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                back_color = "green";
            }
        });

        // db 연결
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        mStorage = FirebaseStorage.getInstance();

        // 이미지 설정

        change_profile = findViewById(R.id.profile);
        change_profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gotoAlbum();
            }
        });

        // 제작버튼
        Button btn_finish = findViewById(R.id.me_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText me_d_name = findViewById(R.id.make_Name_d_editText);
                EditText me_d_brith = findViewById(R.id.make_bri_d_editText);
                EditText me_d_day = findViewById(R.id.make_d_date_editText);
                EditText me_phrases = findViewById(R.id.make_pre_editText);
                EditText me_song = findViewById(R.id.make_song_editText);



                Uri file = Uri.fromFile(new File(pathUri)); // path
                StorageReference storageReference = mStorage.getReference().child("나의추모관").child("uid/"+file.getLastPathSegment());
                UploadTask uploadTask = storageReference.putFile(imageUri);
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                        }
                        else {
                             // 실패시
                        }
                    }
                });
                profile_url = urlTask.toString();

/*
                // 스토리지에 방생성 후 선택한 이미지 넣음
                StorageReference storageReference = mStorage.getReference().child("나의추모관");
                storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                        while (!imageUrl.isComplete()) ;
                        profile_url = imageUrl.getResult().toString();
                    }
                });
                //storageReference.getDownloadUrl().addOnSuccessListener()
*/
                Make_memorialActivity.Make m = new Make_memorialActivity.Make(me_d_name.getText().toString(), me_d_brith.getText().toString(),
                        me_d_day.getText().toString(), me_phrases.getText().toString(), me_song.getText().toString(), back_color, profile_url);
                databaseReference.child(id).child("나의추모관").push().setValue(m);

                finish();
            }
        });

    }

    // 앨범 메소드
    private void gotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) { // 코드가 틀릴경우
            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        tempFile = null;
                    }
                }
            }
            return;
        }

        switch (requestCode) {
            case PICK_FROM_ALBUM: { // 코드 일치
                // Uri
                imageUri = data.getData();
                pathUri = getPath(data.getData());
                change_profile.setImageURI(imageUri); // 이미지 띄움
                break;
            }
        }
    }

    // uri 절대경로 가져오기
    public String getPath(Uri uri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(index);
    }

}
