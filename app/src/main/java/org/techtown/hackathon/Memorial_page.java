package org.techtown.hackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Memorial_page extends AppCompatActivity {
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorial_page);

        Intent intent = getIntent();
        String color = intent.getStringExtra("color");
        String d_brith = intent.getStringExtra("d_brith");
        String d_day = intent.getStringExtra("d_day");
        String d_name = intent.getStringExtra("d_name");
        String image_url = intent.getStringExtra("image_url");
        String phrases = intent.getStringExtra("phrases");
        String song = intent.getStringExtra("song");

        TextView me_song = findViewById(R.id.memo_page_BGM);
        TextView me_d_name = findViewById(R.id.memo_page_name);
        TextView me_date = findViewById(R.id.memo_page_date);
        TextView me_phrases = findViewById(R.id.memo_page_phrases);
        ImageView me_profile = findViewById(R.id.profile_image);
        LinearLayout me_background = findViewById(R.id.profile_back);

        switch(color) {
            case "white":
                me_background.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case "yellow":
                me_background.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case "green":
                me_background.setBackgroundColor(getResources().getColor(R.color.green));
                break;
        }

        me_song.setText("BGM - " + song);
        me_d_name.setText("故 " + d_name + " 추모관");
        me_date.setText(d_brith + "~" + d_day);
        me_phrases.setText(phrases);
        me_profile.setImageResource(R.drawable.dog);
    }
}
