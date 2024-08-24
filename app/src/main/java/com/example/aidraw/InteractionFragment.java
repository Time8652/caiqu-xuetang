package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class InteractionFragment extends Fragment {

    private String key, search;
    private final int CAMERA_REQUEST_CODE = 1, REQUEST_CODE_TAKE = 0;
    private static String selection = null;
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
                selection = "选人";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //投票
                selection = "投票";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分组
                selection = "分组";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍摄
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setAction("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, REQUEST_CODE_TAKE);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                }
            }
        });
        white_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //白板
                selection = "白板";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计时器
                selection = "计时器";
                startActivity(new Intent(getActivity(), WebActivity.class));
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
                selection = "1V1 PK";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        creative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创意小挑战
                selection = "创意小挑战";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        fun_Q_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //趣味问答
                selection = "趣味问答";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        jielong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接龙绘画
                selection = "接龙绘画";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        coloring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //涂色游戏
                selection = "涂色游戏";
                startActivity(new Intent(getActivity(), WebActivity.class));
            }
        });
        riddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //作品猜谜
                selection = "作品猜谜";
                startActivity(new Intent(getActivity(), WebActivity.class));
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

    public static String getSelection() {
        return selection;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "权限获取失败！无法使用本功能！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}