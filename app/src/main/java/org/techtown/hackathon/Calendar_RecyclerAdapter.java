package org.techtown.hackathon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Calendar_RecyclerAdapter extends RecyclerView.Adapter<Calendar_RecyclerAdapter.ViewHolder> {
    private ArrayList<Obituary_RecyclerItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public Calendar_RecyclerAdapter(ArrayList<Obituary_RecyclerItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Calendar_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.calendar_item, parent, false) ;
        Calendar_RecyclerAdapter.ViewHolder vh = new Calendar_RecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Obituary_RecyclerItem item = mData.get(position) ;

        holder.top.setText(item.getD_go_month() + "." + item.getD_go_day()); ;
        holder.bottom.setText("故 " + item.getD_name() + "님");
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView top ;
        TextView bottom ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조
            top = itemView.findViewById(R.id.cal_date) ;
            bottom = itemView.findViewById(R.id.cal_name) ;
        }
    }
}