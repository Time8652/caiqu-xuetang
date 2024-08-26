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
import com.example.aidraw.News.WorkNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<WorkNew> workNewArrayList;

    public WorkAdapter(Context context, ArrayList<WorkNew> workNewArrayList) {
        this.context = context;
        this.workNewArrayList = workNewArrayList;
    }

    @NonNull
    @Override
    public WorkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_work, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.MyViewHolder holder, int position) {
        WorkNew workNew = workNewArrayList.get(position);
        Glide.with(context).load(workNew.work_url).into(holder.imageView_work);
        holder.work_title.setText(workNew.work_title);
        holder.work_like.setText(workNew.work_like);
        holder.work_time.setText(workNew.work_time);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.work_like.setText(String.valueOf(Integer.parseInt(workNew.work_like) + 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return workNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_work, imageView;
        TextView work_title, work_like, work_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_work = itemView.findViewById(R.id.imageView5);
            work_title = itemView.findViewById(R.id.work_title);
            work_like = itemView.findViewById(R.id.work_like);
            work_time = itemView.findViewById(R.id.work_time);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
