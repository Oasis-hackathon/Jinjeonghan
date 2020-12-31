package org.techtown.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Write_obituaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_obituary);

        Button backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        // 스피너
        Spinner spinner = (Spinner)findViewById(R.id.spinner_rg);

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.relation, android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(monthAdapter);
        //spinner.setSelection(pos); // 스피너 default 값 설정

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //DBheader = String.valueOf(adapterView.getItemAtPosition(i)); // 글자는 adapterView, i는 index
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
