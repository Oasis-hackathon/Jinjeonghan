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

public class Memorial_RecyclerAdapter_public extends RecyclerView.Adapter<Memorial_RecyclerAdapter_public.ViewHolder> {
    private ArrayList<Memorial_RecyclerItem_public> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public Memorial_RecyclerAdapter_public(ArrayList<Memorial_RecyclerItem_public> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Memorial_RecyclerAdapter_public.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.memorial_item, parent, false) ;
        Memorial_RecyclerAdapter_public.ViewHolder vh = new Memorial_RecyclerAdapter_public.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Memorial_RecyclerItem_public item = mData.get(position) ;

        Glide.with(holder.itemView).load(item.getPreview()).into(holder.image);
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
                        Memorial_RecyclerItem_public item = mData.get(pos);
                        String preview = item.getPreview();
                        String d_brith = item.getD_brith();
                        String d_day = item.getD_day();
                        String d_name = item.getD_name();
                        String image_url = item.getImage_url();
                        String albom1 = item.getAlbom1();
                        String albom2 = item.getAlbom2();
                        String albom3 = item.getAlbom3();
                        String letter_write1 = item.getLetter_write1();
                        String letter_write2 = item.getLetter_write2();
                        String profile_write = item.getProfile_write();
                        String profile_image = item.getProfile_image();
                        String song = item.getSong();

                        Intent intent = new Intent(v.getContext(), Memorial_page_public.class);
                        intent.putExtra("preview", ""+preview);
                        intent.putExtra("d_brith", ""+d_brith);
                        intent.putExtra("d_day", ""+d_day);
                        intent.putExtra("d_name", ""+d_name);
                        intent.putExtra("image_url", ""+image_url);
                        intent.putExtra("albom1", ""+albom1);
                        intent.putExtra("albom2", ""+albom2);
                        intent.putExtra("albom3", ""+albom3);
                        intent.putExtra("letter_write1", ""+letter_write1);
                        intent.putExtra("letter_write2", ""+letter_write2);
                        intent.putExtra("profile_write", ""+profile_write);
                        intent.putExtra("song", ""+song);
                        intent.putExtra("profile_image", ""+profile_image);

                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
