package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SuppressLint("ValidFragment")
public class PersonalFragment extends Fragment {

    private String key, name_text;
    private URL url;
    private ImageView head, imageView_1, imageView_2, imageView_6, imageView_7, imageView_8, imageView_9;
    private TextView identity, name, concise, saying, celebrity, textView_6, textView_7, textView_8, textView_9;
    private ConstraintLayout constraintLayout_1, constraintLayout_2, constraintLayout_3, constraintLayout_4,
            constraintLayout_5, constraintLayout_6, constraintLayout_7;

    PersonalFragment(String key) {
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        head = view.findViewById(R.id.imageView_head);
        imageView_1 = view.findViewById(R.id.imageView_1);
        imageView_2 = view.findViewById(R.id.imageView_2);
        identity = view.findViewById(R.id.textView_identity);
        name = view.findViewById(R.id.textView_name);
        concise = view.findViewById(R.id.concise);
        saying = view.findViewById(R.id.textView_saying);
        celebrity = view.findViewById(R.id.textView_celebrity);
        imageView_6 = view.findViewById(R.id.imageView6);
        imageView_7 = view.findViewById(R.id.imageView7);
        imageView_8 = view.findViewById(R.id.imageView8);
        imageView_9 = view.findViewById(R.id.imageView9);
        textView_6 = view.findViewById(R.id.textView6);
        textView_7 = view.findViewById(R.id.textView7);
        textView_8 = view.findViewById(R.id.textView8);
        textView_9 = view.findViewById(R.id.textView9);
        constraintLayout_1 = view.findViewById(R.id.constraintLayout_1);
        constraintLayout_2 = view.findViewById(R.id.constraintLayout_2);
        constraintLayout_3 = view.findViewById(R.id.constraintLayout_3);
        constraintLayout_4 = view.findViewById(R.id.constraintLayout_4);
        constraintLayout_5 = view.findViewById(R.id.constraintLayout_5);
        constraintLayout_6 = view.findViewById(R.id.constraintLayout_6);
        constraintLayout_7 = view.findViewById(R.id.constraintLayout_7);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        if (LoginActivity.getIdentity().equals("teacher")) {
            identity.setText("教师");
            imageView_6.setImageResource(R.drawable.mine_class);
            imageView_7.setImageResource(R.drawable.mine_timetable);
            imageView_8.setImageResource(R.drawable.mine_resources);
            imageView_9.setImageResource(R.drawable.mine_homework);
            textView_6.setText("我的班级");
            textView_7.setText("我的课程表");
            textView_8.setText("教学资源");
            textView_9.setText("学生作业");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/teacher/info")
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        url = (URL) jsonObject.get("headerUrl");
                        name_text = jsonObject.getString("name");
                    } catch (Exception e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        } else {
            identity.setText("学生");
//            imageView_6.setImageResource(R.drawable.);
//            imageView_7.setImageResource(R.drawable.);
//            imageView_8.setImageResource(R.drawable.);
//            imageView_9.setImageResource(R.drawable.);
            textView_6.setText("个人信息");
            textView_7.setText("学习报告");
            textView_8.setText("我的积分");
            textView_9.setText("我的收藏");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/student/info")
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        url = (URL) jsonObject.get("headerUrl");
                        name_text = jsonObject.getString("name");
                    } catch (Exception e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
        Glide.with(PersonalFragment.this).load(url).into(head);
        name.setText(name_text);
        concise.setText("简介：一名普普通通的小学美术老师~");
        saying.setText("美术是揭示真理的谎言。");
        celebrity.setText("——毕加索");
    }

    private void initOnClickListener() {
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至详细个人信息页
            }
        });
        identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至详细个人信息页
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至详细个人信息页
            }
        });
        concise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至详细个人信息页
            }
        });
        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至留言页
            }
        });
        imageView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至签到页
            }
        });
        constraintLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能一
            }
        });
        constraintLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能二
            }
        });
        constraintLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能三
            }
        });
        constraintLayout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能四
            }
        });
        constraintLayout_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能五
            }
        });
        constraintLayout_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能六
            }
        });
        constraintLayout_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至功能七
            }
        });
    }
}