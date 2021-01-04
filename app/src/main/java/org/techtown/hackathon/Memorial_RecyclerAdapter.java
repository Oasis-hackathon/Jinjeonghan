package org.techtown.hackathon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Memorial_RecyclerAdapter extends RecyclerView.Adapter<Memorial_RecyclerAdapter.ViewHolder> {
    private ArrayList<Memorial_RecyclerItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public Memorial_RecyclerAdapter(ArrayList<Memorial_RecyclerItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Memorial_RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.memorial_item, parent, false) ;
        Memorial_RecyclerAdapter.ViewHolder vh = new Memorial_RecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Memorial_RecyclerItem item = mData.get(position) ;

        holder.image.setImageResource(R.drawable.temp_image);
        //Glide.with(holder.itemView).load(item.getImage_url()).into(holder.image);
        holder.top.setText("故 " + item.getD_name()); ;
        holder.bottom.setText(item.getD_brith() + "~" + item.getD_day()) ;
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
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조
            top = itemView.findViewById(R.id.memo_name) ;
            bottom = itemView.findViewById(R.id.memo_date) ;
            image = itemView.findViewById(R.id.memo_image);

            // 리사이클러뷰 클릭시 부고함 페이지로 넘어가게끔 구현
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조
                        Memorial_RecyclerItem item = mData.get(pos);
                        String color = item.getColor();
                        String d_brith = item.getD_brith();
                        String d_day = item.getD_day();
                        String d_name = item.getD_name();
                        String image_url = item.getImage_url();
                        String phrases = item.getPhrases();
                        String song = item.getSong();

                        Intent intent = new Intent(v.getContext(), Memorial_page.class);
                        intent.putExtra("color", ""+color);
                        intent.putExtra("d_brith", ""+d_brith);
                        intent.putExtra("d_day", ""+d_day);
                        intent.putExtra("d_name", ""+d_name);
                        intent.putExtra("image_url", ""+image_url);
                        intent.putExtra("phrases", ""+phrases);
                        intent.putExtra("song", ""+song);

                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
