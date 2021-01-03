package org.techtown.hackathon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_calendar extends Fragment {
    RecyclerView mRecyclerView = null ;
    Calendar_RecyclerAdapter mAdapter = null ;
    ArrayList<Obituary_RecyclerItem> mList = new ArrayList<Obituary_RecyclerItem>(); // 객체를 담을 어레이 리스트(어댑터 쪽으로)

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String DBheader;

    // 캘린더
    public String fname=null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView diaryTextView,textView2,textView3;
    public EditText contextEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup frag = (ViewGroup) inflater.inflate(R.layout.fragment_calendar, container, false);

        // 정보 받기
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String name = bundle.getString("name");
        DBheader = id;

 /*
        // 리사이클러뷰
        mRecyclerView = frag.findViewById(R.id.come_cal) ;
        mRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager); // 리사이클러뷰에 LinearLayoutManager 지정
        database = FirebaseDatabase.getInstance(); // firebase 데이터베이스 연동

        databaseReference = database.getReference(DBheader); // DB 테이블 연결
        databaseReference.child("부고함").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // firebase 데이터베이스의 데이터를 받아오는 곳
                mList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Obituary_RecyclerItem info = snapshot.getValue(Obituary_RecyclerItem.class);
                    mList.add(info); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                mAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던 중 에러 발생 시
                Log.e("DB 가져오던 중 에러", String.valueOf(error.toException())); // 에러 출력
            }
        });

        // 리사이클러뷰에 Adapter 객체 지정.
        mAdapter = new Calendar_RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
*/
        // 캘린더
        calendarView=frag.findViewById(R.id.calendarView);
        diaryTextView=frag.findViewById(R.id.diaryTextView);
        save_Btn=frag.findViewById(R.id.save_Btn);
        del_Btn=frag.findViewById(R.id.del_Btn);
        cha_Btn=frag.findViewById(R.id.cha_Btn);
        textView2=frag.findViewById(R.id.textView2);
        textView3=frag.findViewById(R.id.textView3);
        contextEditText=frag.findViewById(R.id.contextEditText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                contextEditText.setText("");
                checkDay(year,month,dayOfMonth,id);
            }
        });
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str=contextEditText.getText().toString();
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });

        return frag;
    }
    public void  checkDay(int cYear,int cMonth,int cDay,String userID){
        fname=""+userID+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정
        FileInputStream fis=null;//FileStream fis 변수

        try{
            File file = new File(fname);
            fis = new FileInputStream(file);
            //fis=openFileInput(fname);

            byte[] fileData=new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str=new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });
            if(textView2.getText()==null){
                textView2.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos = new FileOutputStream(readDay);
            //fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content="";
            fos.write((content).getBytes());
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos = new FileOutputStream(readDay);
            //fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content=contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
