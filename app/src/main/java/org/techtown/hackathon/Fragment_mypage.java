package org.techtown.hackathon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_mypage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup frag = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);
        // 정보 받기
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String name = bundle.getString("name");

        TextView tv_name = frag.findViewById(R.id.mypage_name);
        TextView tv_id = frag.findViewById(R.id.mypage_id);
        tv_name.setText(name);
        tv_id.setText(id);

        return frag;
    }

}
