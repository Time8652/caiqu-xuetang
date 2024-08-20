package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class InteractionFragment extends Fragment {

    private String key, search;
    private EditText editText;
    private ImageView gone;
    private CardView model_3D, work, virtual, intelligent;
    private ConstraintLayout candidate, vote, divide, photograph, white_board, timer;
    private ConstraintLayout pk_1V1, creative, fun_Q_A, jielong, coloring, riddle;

    InteractionFragment(String key) {
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interaction, container, false);
        initView(view);
        search(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        editText = view.findViewById(R.id.editText);
        gone = view.findViewById(R.id.gone);
        candidate = view.findViewById(R.id.linearLayout_candidate);
        vote = view.findViewById(R.id.linearLayout_vote);
        divide = view.findViewById(R.id.linearLayout_divide);
        photograph = view.findViewById(R.id.linearLayout_photograph);
        white_board = view.findViewById(R.id.linearLayout_white_board);
        timer = view.findViewById(R.id.linearLayout_timer);
        model_3D = view.findViewById(R.id.linearLayout_3D_model);
        work = view.findViewById(R.id.linearLayout_work);
        virtual = view.findViewById(R.id.linearLayout_virtual);
        intelligent = view.findViewById(R.id.linearLayout_intelligent);
        pk_1V1 = view.findViewById(R.id.constraintLayout_1V1_PK);
        creative = view.findViewById(R.id.constraintLayout_creative);
        fun_Q_A = view.findViewById(R.id.constraintLayout_fun_Q_A);
        jielong = view.findViewById(R.id.constraintLayout_jielong);
        coloring = view.findViewById(R.id.constraintLayout_coloring);
        riddle = view.findViewById(R.id.constraintLayout_riddle);
        //处理点击事件
        initOnClickListener();
    }

    private void initOnClickListener() {
        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选人
            }
        });
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //投票
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分组
            }
        });
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍摄
            }
        });
        white_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //白板
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计时器
            }
        });
        model_3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3D模型展示
                startActivity(new Intent(getActivity(), ModelActivity.class));
            }
        });
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //学生作品展示
                startActivity(new Intent(getActivity(), WorkActivity.class));
            }
        });
        virtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //虚拟美术馆
                startActivity(new Intent(getActivity(), VirtualActivity.class));
            }
        });
        intelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //智能画作分析
                startActivity(new Intent(getActivity(), IntelligentActivity.class));
            }
        });
        pk_1V1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1V1 PK
            }
        });
        creative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创意小挑战
            }
        });
        fun_Q_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //趣味问答
            }
        });
        jielong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接龙绘画
            }
        });
        coloring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //涂色游戏
            }
        });
        riddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //作品猜谜
            }
        });
    }

    private void search(View view) {
        search = editText.getText().toString();
        if (search.equals("")) {
            gone.setVisibility(View.GONE);
        } else {
            gone.setVisibility(View.VISIBLE);
            gone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText(null);
                }
            });
        }
    }
}