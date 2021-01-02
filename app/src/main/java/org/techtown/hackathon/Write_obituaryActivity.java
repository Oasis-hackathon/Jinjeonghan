package org.techtown.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Write_obituaryActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public class Write {
        public String t_relation;
        public String t_name;
        public String t_number;
        public String d_name;
        public String d_age;
        public String d_date;
        public String d_go;
        public String location;

        public Write(String t_relation, String t_name, String t_number, String d_name,
                     String d_age, String d_date, String d_go, String location) {
            this.t_relation = t_relation;
            this.t_name = t_name;
            this.t_number = t_number;
            this.d_name = d_name;
            this.d_age = d_age;
            this.d_date = d_date;
            this.d_go = d_go;
            this.location = location;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_obituary);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");

        Button backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // 제작버튼
        Button btn_finish = findViewById(R.id.ob_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner x_t_relation = findViewById(R.id.spinner_rg);
                EditText x_t_name = findViewById(R.id.Name_t_editText);
                EditText x_t_number = findViewById(R.id.Number_t_editText);
                EditText x_d_name = findViewById(R.id.Name_d_editText);
                EditText x_d_age = findViewById(R.id.age_d_editText);
                EditText x_d_date = findViewById(R.id.date_editText);
                EditText x_d_go = findViewById(R.id.date_editText2);
                EditText x_location = findViewById(R.id.location_editText);

                Write w = new Write(x_t_relation.getSelectedItem().toString(), x_t_name.getText().toString(), x_t_number.getText().toString(),
                        x_d_name.getText().toString(), x_d_age.getText().toString(), x_d_date.getText().toString(),
                        x_d_go.getText().toString(), x_location.getText().toString());
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.child(id).child("부고함").push().setValue(w);
                finish();
            }
        });
    }
}
