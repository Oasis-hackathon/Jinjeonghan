package org.techtown.hackathon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Obituary_RecyclerAdapter extends RecyclerView.Adapter<Obituary_RecyclerAdapter.ViewHolder> {
    private ArrayList<Obituary_RecyclerItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public Obituary_RecyclerAdapter(ArrayList<Obituary_RecyclerItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Obituary_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.obituary_item, parent, false) ;
        Obituary_RecyclerAdapter.ViewHolder vh = new Obituary_RecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Obituary_RecyclerItem item = mData.get(position) ;

        holder.top.setText("삼가 고인의 명복을 빕니다."); ;
        holder.bottom.setText("故 " + item.getD_name() + "님께서 " + item.getD_date() + "별세하셨습니다.") ;
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
            top = itemView.findViewById(R.id.top_text) ;
            bottom = itemView.findViewById(R.id.bottom_text) ;


            // 리사이클러뷰 클릭시 부고함 페이지로 넘어가게끔 구현
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조
                        Obituary_RecyclerItem item = mData.get(pos);
                        String t_relation = item.getT_relation();
                        String t_name = item.getT_name();
                        String t_number = item.getT_number();
                        String d_name = item.getD_name();
                        String d_age = item.getD_age();
                        String d_date = item.getD_date();
                        String d_go = item.getD_go();
                        String location = item.getLocation();

                        Intent intent = new Intent(v.getContext(), Obituary_page.class);
                        intent.putExtra("t_relation", ""+t_relation);
                        intent.putExtra("t_name", ""+t_name);
                        intent.putExtra("t_number", ""+t_number);
                        intent.putExtra("d_name", ""+d_name);
                        intent.putExtra("d_age", ""+d_age);
                        intent.putExtra("d_date", ""+d_date);
                        intent.putExtra("d_go", ""+d_go);
                        intent.putExtra("location", ""+location);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}