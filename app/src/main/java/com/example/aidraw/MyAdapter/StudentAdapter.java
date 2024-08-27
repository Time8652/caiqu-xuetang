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
import com.example.aidraw.News.StudentNew;
import com.example.aidraw.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<StudentNew> studentNewArrayList;

    public StudentAdapter(Context context, ArrayList<StudentNew> studentNewArrayList) {
        this.context = context;
        this.studentNewArrayList = studentNewArrayList;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        StudentNew studentNew = studentNewArrayList.get(position);
        holder.tv_name.setText(studentNew.name);
        holder.student_id.setText("2022001030" + studentNew.id);
        Glide.with(context).load(studentNew.url).into(holder.imageView_head);
        if (studentNew.gender.equals("男")) {
            holder.gender.setImageResource(R.drawable.class_boy);
        } else {
            holder.gender.setImageResource(R.drawable.class_girl);
        }
    }

    @Override
    public int getItemCount() {
        return studentNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_head, gender;
        TextView tv_name, student_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_head = itemView.findViewById(R.id.imageView_head);
            gender = itemView.findViewById(R.id.gender);
            tv_name = itemView.findViewById(R.id.tv_name);
            student_id = itemView.findViewById(R.id.student_id);
        }
    }
}
