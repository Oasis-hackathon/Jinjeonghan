package org.techtown.hackathon;

import android.content.Intent;
import android.os.Bundle;
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
        String d_go = intent.getStringExtra("d_go");
        String location = intent.getStringExtra("location");

        TextView tv_relation = findViewById(R.id.relation_t_Text);
        TextView tv_name = findViewById(R.id.name_t_Text);
        TextView tvd_name_age = findViewById(R.id.textView2);
        TextView tvd_name_date = findViewById(R.id.textView3);
        TextView tvd_date = findViewById(R.id.p_date_Text);
        TextView tv_location = findViewById(R.id.p_location_Text);

        tv_relation.setText(t_relation);
        tv_name.setText(t_name);
        tvd_name_age.setText("故 " + d_name + " (" + d_age +"세)");
        tvd_name_date.setText("故 " + d_name + "님께서 " + d_date);
        tvd_date.setText(d_go);
        tv_location.setText(location);
    }
}
