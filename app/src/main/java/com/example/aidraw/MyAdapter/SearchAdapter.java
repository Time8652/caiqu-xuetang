package com.example.aidraw.MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aidraw.News.SearchNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<SearchNew> searchNewArrayList;
    private OnItemClickListener myListener;

    public SearchAdapter(Context context, ArrayList<SearchNew> searchNewArrayList, OnItemClickListener listener) {
        this.context = context;
        this.searchNewArrayList = searchNewArrayList;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SearchNew searchNew = searchNewArrayList.get(position);
        Glide.with(context).load(searchNew.head_url).into(holder.imageView_head);
        holder.tv_name.setText(searchNew.name);
        if (searchNew.gender.equals("男")) {
            holder.gender.setImageResource(R.drawable.class_boy);
        } else if (searchNew.gender.equals("女")) {
            holder.gender.setImageResource(R.drawable.class_girl);
        }
        holder.tv_num.setText(searchNew.num);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myListener != null) {
                    myListener.OnItemClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchNewArrayList.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(View itemView, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_head, gender;
        TextView tv_name, tv_num;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_head = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.textView_1);
            gender = itemView.findViewById(R.id.gender);
            tv_num = itemView.findViewById(R.id.integral_1);
        }
    }
}