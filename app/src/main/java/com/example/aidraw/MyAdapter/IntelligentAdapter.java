package com.example.aidraw.MyAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aidraw.News.IntelligentNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class IntelligentAdapter extends RecyclerView.Adapter<IntelligentAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<IntelligentNew> intelligentNewArrayList;

    public IntelligentAdapter(Context context, ArrayList<IntelligentNew> intelligentNewArrayList) {
        this.context = context;
        this.intelligentNewArrayList = intelligentNewArrayList;
    }

    @NonNull
    @Override
    public IntelligentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_container_message, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntelligentAdapter.MyViewHolder holder, int position) {
        IntelligentNew intelligentNew = intelligentNewArrayList.get(position);
        if (intelligentNew.type) {
            holder.constraintLayout1.setVisibility(View.GONE);
            holder.constraintLayout2.setVisibility(View.VISIBLE);
            holder.receive_message.setText(intelligentNew.text);
        } else {
            holder.constraintLayout2.setVisibility(View.GONE);
            holder.constraintLayout1.setVisibility(View.VISIBLE);
            holder.send_message.setText(intelligentNew.text);
            if (intelligentNew.path != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(intelligentNew.path);
                holder.send_work.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        return intelligentNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout1, constraintLayout2;
        ImageView send_work;
        TextView send_message, receive_message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout1 = itemView.findViewById(R.id.constraintLayout1);
            constraintLayout2 = itemView.findViewById(R.id.constraintLayout2);
            send_work = itemView.findViewById(R.id.send_work);
            send_message = itemView.findViewById(R.id.send_message);
            receive_message = itemView.findViewById(R.id.receive_message);
        }
    }
}
