package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ResourceFragment extends Fragment {

    private String key;
    private ImageView classic_work_more, model_3D_more, imageView_3D_model, game_more, ai_more;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout_1, constraintLayout_2, constraintLayout_3;
    private ProgressBar progress_horizontal_1, progress_horizontal_2, progress_horizontal_3;
    private TextView progress_1, progress_2, progress_3;
    private LinearLayout linearLayout_line, linearLayout_color, linearLayout_composition, linearLayout_style;
    private TextView ask;
    private double rate_1, rate_2, rate_3;

    ResourceFragment(String key) {
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resource, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        classic_work_more = view.findViewById(R.id.classic_work_more);
        model_3D_more = view.findViewById(R.id.model_3D_more);
        imageView_3D_model = view.findViewById(R.id.imageView_3D_model);
        game_more = view.findViewById(R.id.game_more);
        recyclerView = view.findViewById(R.id.recyclerView);
        constraintLayout_1 = view.findViewById(R.id.constraintLayout_1);
        constraintLayout_2 = view.findViewById(R.id.constraintLayout_2);
        constraintLayout_3 = view.findViewById(R.id.constraintLayout_3);
        progress_horizontal_1 = view.findViewById(R.id.progress_horizontal_1);
        progress_horizontal_2 = view.findViewById(R.id.progress_horizontal_2);
        progress_horizontal_3 = view.findViewById(R.id.progress_horizontal_3);
        progress_1 = view.findViewById(R.id.progress_1);
        progress_2 = view.findViewById(R.id.progress_2);
        progress_3 = view.findViewById(R.id.progress_3);
        ai_more = view.findViewById(R.id.ai_more);
        linearLayout_line = view.findViewById(R.id.linearLayout_line);
        linearLayout_color = view.findViewById(R.id.linearLayout_color);
        linearLayout_composition = view.findViewById(R.id.linearLayout_composition);
        linearLayout_style = view.findViewById(R.id.linearLayout_style);
        ask = view.findViewById(R.id.ask);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        rate_1 = 0.5;
        progress_1.setText("已解锁：" + rate_1 * 100 + "%");
        rate_2 = 0.67;
        progress_2.setText("已解锁：" + rate_2 * 100 + "%");
        rate_3 = 0.33;
        progress_3.setText("已解锁：" + rate_3 * 100 + "%");
        progress_horizontal_1.setProgress((int) (rate_1 * 100));
        progress_horizontal_2.setProgress((int) (rate_2 * 100));
        progress_horizontal_3.setProgress((int) (rate_3 * 100));
    }

    private void initOnClickListener() {
        classic_work_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //经典作品详细页
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //经典作品详细页
            }
        });
        model_3D_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3D模型详细页
            }
        });
        imageView_3D_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3D模型详细页
            }
        });
        game_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //游戏解锁详细页
            }
        });
        constraintLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //游戏一解锁页
            }
        });
        constraintLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //游戏二解锁页
            }
        });
        constraintLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //游戏三解锁页
            }
        });
        ai_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //所有AI功能详细页
            }
        });
        linearLayout_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //线条优化功能
            }
        });
        linearLayout_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //色彩搭配功能
            }
        });
        linearLayout_composition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构图建议功能
            }
        });
        linearLayout_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //风格转换功能
            }
        });
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //问一问
            }
        });
    }
}