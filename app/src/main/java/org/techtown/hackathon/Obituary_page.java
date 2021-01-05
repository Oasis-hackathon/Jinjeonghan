package org.techtown.hackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Obituary_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obituary_page);

        Intent intent = getIntent();
        String t_relation = intent.getStringExtra("t_relation");
        String t_name = intent.getStringExtra("t_name");
        String t_number = intent.getStringExtra("t_number");
        String d_name = intent.getStringExtra("d_name");
        String d_age = intent.getStringExtra("d_age");
        String d_date = intent.getStringExtra("d_date");
        String d_go_year = intent.getStringExtra("d_go_year");
        String d_go_month = intent.getStringExtra("d_go_month");
        String d_go_day = intent.getStringExtra("d_go_day");
        String location = intent.getStringExtra("location");

        ImageView backbtn = findViewById(R.id.ob_go_back2);
        backbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        TextView tv_name = findViewById(R.id.name_t_Text);
        TextView tvd_top = findViewById(R.id.textView2);
        TextView tvd_date = findViewById(R.id.d_day_Text);
        TextView tv_location = findViewById(R.id.d_location_Text);

        tv_name.setText(t_name);
        tvd_top.setText("故 " + d_name + "님께서 " + d_date);
        tvd_date.setText(d_go_year + "년 " + d_go_month + "월 " + d_go_day + "일");
        tv_location.setText(location);

        // 배경 색상
        Button btn_white = findViewById(R.id.btn_white1);
        Button btn_brown1= findViewById(R.id.btn_brown1);
        Button btn_brown2 = findViewById(R.id.btn_brown2);
        LinearLayout back_color = findViewById(R.id.back_color);

        btn_white.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                back_color.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        btn_brown1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                back_color.setBackgroundColor(getResources().getColor(R.color.brown1));
            }
        });
        btn_brown2.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                back_color.setBackgroundColor(getResources().getColor(R.color.brown2));
            }
        });
    }
}
