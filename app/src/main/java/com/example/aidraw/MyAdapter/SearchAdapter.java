package com.example.aidraw.MyAdapter;

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

    public SearchAdapter(Context context, ArrayList<SearchNew> searchNewArrayList) {
        this.context = context;
        this.searchNewArrayList = searchNewArrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        SearchNew searchNew = searchNewArrayList.get(position);
        Glide.with(context).load(searchNew.head_url).into(holder.imageView_head);
        holder.tv_name.setText(searchNew.name);
        if (searchNew.gender.equals("男")) {
            holder.gender.setImageResource(R.drawable.class_boy);
        } else if (searchNew.gender.equals("女")) {
            holder.gender.setImageResource(R.drawable.class_girl);
        }
        holder.tv_num.setText(searchNew.num);
    }

    @Override
    public int getItemCount() {
        return searchNewArrayList.size();
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