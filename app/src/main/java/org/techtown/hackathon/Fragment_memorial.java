package org.techtown.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_memorial extends Fragment {
    RecyclerView mRecyclerView = null ;
    Memorial_RecyclerAdapter mAdapter = null ;
    ArrayList<Memorial_RecyclerItem> mList = new ArrayList<Memorial_RecyclerItem>(); // 객체를 담을 어레이 리스트(어댑터 쪽으로)
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    String DBheader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup frag = (ViewGroup) inflater.inflate(R.layout.fragment_memorial, container, false);

        // 정보 받기
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String name = bundle.getString("name");
        DBheader = id;

        ImageView make_me = frag.findViewById(R.id.make_memorial);
        make_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Make_memorialActivity.class);
                intent.putExtra("id", ""+id);
                intent.putExtra("name", ""+name);
                startActivity(intent);
            }
        });

        // 리사이클러뷰
        mRecyclerView = frag.findViewById(R.id.recycler_mymemo) ;
        mRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance(); // firebase 데이터베이스 연동

        databaseReference = database.getReference(DBheader); // DB 테이블 연결
        databaseReference.child("나의추모관").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // firebase 데이터베이스의 데이터를 받아오는 곳
                mList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Memorial_RecyclerItem info = snapshot.getValue(Memorial_RecyclerItem.class);
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
        mAdapter = new Memorial_RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        return frag;
    }

}
