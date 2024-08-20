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
import com.example.aidraw.News.TopicNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<TopicNew> topicNewArrayList;

    public TopicAdapter(Context context, ArrayList<TopicNew> topicNewArrayList) {
        this.context = context;
        this.topicNewArrayList = topicNewArrayList;
    }

    @NonNull
    @Override
    public TopicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_topic, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.MyViewHolder holder, int position) {
        TopicNew topicNew = topicNewArrayList.get(position);
        Glide.with(context).load(topicNew.head_url).into(holder.imageView_head);
        holder.tv_name.setText(topicNew.name);
        holder.tv_time.setText(topicNew.time);
        holder.tv_text.setText(topicNew.text);
        Glide.with(context).load(topicNew.work_url).into(holder.imageView_work);
    }

    @Override
    public int getItemCount() {
        return topicNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_head, imageView_work;
        TextView tv_name, tv_time, tv_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_head = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_text = itemView.findViewById(R.id.textView);
            imageView_work = itemView.findViewById(R.id.imageView_1);
        }
    }
}
