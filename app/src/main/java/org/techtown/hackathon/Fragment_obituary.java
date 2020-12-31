package org.techtown.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_obituary extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup frag1 = (ViewGroup) inflater.inflate(R.layout.fragment_obituary, container, false);
        LinearLayout go_write = frag1.findViewById(R.id.write_obituary);
        go_write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Write_obituaryActivity.class);
                startActivity(intent);
            }
        });
        return frag1;
    }

}
