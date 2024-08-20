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
import com.example.aidraw.News.ChallengeNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<ChallengeNew> challengeNewArrayList;

    public ChallengeAdapter(Context context, ArrayList<ChallengeNew> challengeNewArrayList) {
        this.context = context;
        this.challengeNewArrayList = challengeNewArrayList;
    }

    @NonNull
    @Override
    public ChallengeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_challenge, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeAdapter.MyViewHolder holder, int position) {
        ChallengeNew challengeNew = challengeNewArrayList.get(position);
        Glide.with(context).load(challengeNew.head_url).into(holder.imageView_head);
        holder.tv_name.setText(challengeNew.name);
        holder.tv_time.setText(challengeNew.time);
        Glide.with(context).load(challengeNew.work_url).into(holder.imageView_work);
    }

    @Override
    public int getItemCount() {
        return challengeNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_head, imageView_work;
        TextView tv_name, tv_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_head = itemView.findViewById(R.id.imageView_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            imageView_work = itemView.findViewById(R.id.imageView2);
        }
    }
}
