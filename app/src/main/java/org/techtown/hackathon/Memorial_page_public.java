package org.techtown.hackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.appcompat.app.AppCompatActivity;

public class Memorial_page_public extends AppCompatActivity {
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorial_page_public);

        Intent intent = getIntent();
        String preview = intent.getStringExtra("preview");
        String d_brith = intent.getStringExtra("d_brith");
        String d_day = intent.getStringExtra("d_day");
        String d_name = intent.getStringExtra("d_name");
        String image_url = intent.getStringExtra("image_url");
        String albom1 = intent.getStringExtra("albom1");
        String albom2 = intent.getStringExtra("albom2");
        String albom3 = intent.getStringExtra("albom3");
        String letter_write1 = intent.getStringExtra("letter_write1");
        String letter_write2 = intent.getStringExtra("letter_write2");
        String profile_write = intent.getStringExtra("profile_write");
        String profile_image = intent.getStringExtra("profile_image");
        String song = intent.getStringExtra("song");

        TextView me_d_name = findViewById(R.id.memo_page_name);
        TextView me_date = findViewById(R.id.memo_page_date);
        ImageView pub_me_image_url = findViewById(R.id.profile_image);
        ImageView pub_me_albom1 = findViewById(R.id.albom1);
        ImageView pub_me_albom2 = findViewById(R.id.albom2);
        ImageView pub_me_albom3 = findViewById(R.id.albom3);
        ImageView pub_me_letter_write1 = findViewById(R.id.letter_write1);
        ImageView pub_me_letter_write2 = findViewById(R.id.letter_write2);
        ImageView pub_me_profile_write = findViewById(R.id.profile_write);
        ImageView pub_me_song = findViewById(R.id.imageView42);
        ImageView pub_me_profile_image = findViewById(R.id.imageView43);

        Glide.with(this).load(image_url).into(pub_me_image_url);
        Glide.with(this).load(albom1).into(pub_me_albom1);
        Glide.with(this).load(albom2).into(pub_me_albom2);
        Glide.with(this).load(albom3).into(pub_me_albom3);
        Glide.with(this).load(letter_write1).into(pub_me_letter_write1);
        Glide.with(this).load(letter_write2).into(pub_me_letter_write2);
        Glide.with(this).load(profile_write).into(pub_me_profile_write);
        Glide.with(this).load(song).into(pub_me_song);
        Glide.with(this).load(profile_image).into(pub_me_profile_image);
        me_d_name.setText("故 " + d_name + " 추모관");
        me_date.setText(d_brith + "~" + d_day);
    }
}
