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
import com.example.aidraw.News.IntegralNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<IntegralNew> integralNewArrayList;

    public IntegralAdapter(Context context, ArrayList<IntegralNew> integralNewArrayList) {
        this.context = context;
        this.integralNewArrayList = integralNewArrayList;
    }

    @NonNull
    @Override
    public IntegralAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_integral, parent, false);
        return new IntegralAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntegralAdapter.MyViewHolder holder, int position) {
        IntegralNew integralNew = integralNewArrayList.get(position);
        holder.rank.setText(integralNew.rank);
        holder.name.setText(integralNew.name);
        Glide.with(context).load(integralNew.url).into(holder.head);
        switch (integralNew.rank) {
            case "1":
                holder.image.setImageResource(R.drawable.points_champion);
                break;
            case "2":
                holder.image.setImageResource(R.drawable.points_second_place);
                break;
            case "3":
                holder.image.setImageResource(R.drawable.points_third_place);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return integralNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rank, name, score;
        ImageView head, image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.textView13);
            name = itemView.findViewById(R.id.textView_1);
            score = itemView.findViewById(R.id.integral_1);
            head = itemView.findViewById(R.id.iv_1);
            image = itemView.findViewById(R.id.iv_2);
        }
    }
}
