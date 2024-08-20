package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;

@SuppressLint("ValidFragment")
public class ClassFragment extends Fragment {

    private String key, term_text, class_text, teacher_text, student_number_text, class_time_text;
    private URL url1, url2, url3, url4, url5;
    private TextView term, class_name, teacher, student_number, class_time, notice, textView_completed, textView_incomplete,
            textView15, textView19, textView23, textView26, textView29, textView32, textView16, textView20, textView30,
            textView33, textView_1, textView_2, textView_3, textView_4, textView_5, integral_1, integral_2, integral_3,
            integral_4, integral_5;
    private LinearLayout completed, incomplete, homework_more, integral_more;
    private View View_completed, View_incomplete;
    private ConstraintLayout homework_1, homework_2, homework_3, homework_4, homework_5, homework_6, constraintLayout_1,
            constraintLayout_2, constraintLayout_3, constraintLayout_4, constraintLayout_5;
    private ImageView imageView_1, imageView_2, imageView_3, imageView_4, imageView_5;

    ClassFragment(String key) {
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        term = view.findViewById(R.id.textView_term);
        class_name = view.findViewById(R.id.textView_class_name);
        teacher = view.findViewById(R.id.textView_teacher);
        student_number = view.findViewById(R.id.textView_student_number);
        class_time = view.findViewById(R.id.textView_class_time);
        notice = view.findViewById(R.id.notice);
        textView_completed = view.findViewById(R.id.textView_completed);
        textView_incomplete = view.findViewById(R.id.textView_incomplete);
        View_completed = view.findViewById(R.id.View_completed);
        View_incomplete = view.findViewById(R.id.View_incomplete);
        completed = view.findViewById(R.id.completed);
        incomplete = view.findViewById(R.id.incomplete);
        homework_1 = view.findViewById(R.id.homework_1);
        homework_2 = view.findViewById(R.id.homework_2);
        homework_3 = view.findViewById(R.id.homework_3);
        homework_4 = view.findViewById(R.id.homework_4);
        homework_5 = view.findViewById(R.id.homework_5);
        homework_6 = view.findViewById(R.id.homework_6);
        textView15 = view.findViewById(R.id.textView15);
        textView19 = view.findViewById(R.id.textView19);
        textView23 = view.findViewById(R.id.textView23);
        textView26 = view.findViewById(R.id.textView26);
        textView29 = view.findViewById(R.id.textView29);
        textView32 = view.findViewById(R.id.textView32);
        textView16 = view.findViewById(R.id.textView16);
        textView20 = view.findViewById(R.id.textView20);
        textView30 = view.findViewById(R.id.textView30);
        textView33 = view.findViewById(R.id.textView33);
        homework_more = view.findViewById(R.id.homework_more);
        imageView_1 = view.findViewById(R.id.imageView_1);
        imageView_2 = view.findViewById(R.id.imageView_2);
        imageView_3 = view.findViewById(R.id.imageView_3);
        imageView_4 = view.findViewById(R.id.imageView_4);
        imageView_5 = view.findViewById(R.id.imageView_5);
        textView_1 = view.findViewById(R.id.textView_1);
        textView_2 = view.findViewById(R.id.textView_2);
        textView_3 = view.findViewById(R.id.textView_3);
        textView_4 = view.findViewById(R.id.textView_4);
        textView_5 = view.findViewById(R.id.textView_5);
        integral_1 = view.findViewById(R.id.integral_1);
        integral_2 = view.findViewById(R.id.integral_2);
        integral_3 = view.findViewById(R.id.integral_3);
        integral_4 = view.findViewById(R.id.integral_4);
        integral_5 = view.findViewById(R.id.integral_5);
        constraintLayout_1 = view.findViewById(R.id.constraintLayout_1);
        constraintLayout_2 = view.findViewById(R.id.constraintLayout_2);
        constraintLayout_3 = view.findViewById(R.id.constraintLayout_3);
        constraintLayout_4 = view.findViewById(R.id.constraintLayout_4);
        constraintLayout_5 = view.findViewById(R.id.constraintLayout_5);
        integral_more = view.findViewById(R.id.integral_more);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        term_text = "2024-2025年第一学期";
        term.setText("当前学期：" + term_text);
        class_text = "一年级二班";
        class_name.setText("班级名称：" + class_text);
        teacher_text = "姚老师";
        teacher.setText("美术老师：" + teacher_text);
        student_number_text = "35";
        student_number.setText("学生人数：" + student_number_text);
        class_time_text = "周三6-7节/周五3-4节";
        class_time.setText("上课时间：" + class_time_text);
        notice.setText("作业：《创意色彩小世界——我的梦幻家园》已批改完成，请及时查看~");
        textView_completed.setTextColor(0xFF333333);
        View_completed.setVisibility(View.VISIBLE);
        View_incomplete.setVisibility(View.INVISIBLE);
        homework_1.setVisibility(View.VISIBLE);
        homework_2.setVisibility(View.VISIBLE);
        homework_3.setVisibility(View.VISIBLE);
        homework_4.setVisibility(View.VISIBLE);
        homework_5.setVisibility(View.GONE);
        homework_6.setVisibility(View.GONE);
        textView15.setText("创意色彩小世界——我的梦幻家园");
        textView19.setText("故事里的角色——我设计的卡通人物");
        textView23.setText("动手创造——我的环保手工艺品");
        textView26.setText("剪纸艺术——我的创意剪影世界");
        textView29.setText("创意色彩小世界——我的梦幻家园");
        textView32.setText("故事里的角色——我设计的卡通人物");
        textView16.setText("B");
        textView20.setText("A+");
        textView30.setText("剩余15小时30分钟");
        textView33.setText("剩余48小时50分钟");
        Glide.with(ClassFragment.this).load(url1).into(imageView_1);
        Glide.with(ClassFragment.this).load(url2).into(imageView_2);
        Glide.with(ClassFragment.this).load(url3).into(imageView_3);
        Glide.with(ClassFragment.this).load(url4).into(imageView_4);
        Glide.with(ClassFragment.this).load(url5).into(imageView_5);
        textView_1.setText("罗小昔");
        textView_2.setText("曾小卒");
        textView_3.setText("罗小昔");
        textView_4.setText("曾小卒");
        textView_5.setText("罗小昔");
        integral_1.setText("300积分");
        integral_2.setText("298积分");
        integral_3.setText("280积分");
        integral_4.setText("266积分");
        integral_5.setText("258积分");
        //处理点击事件
        initOnClickListener();
    }

    private void initOnClickListener() {
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_completed.setTextColor(0xFF333333);
                textView_incomplete.setTextColor(0xFF999999);
                View_completed.setVisibility(View.VISIBLE);
                View_incomplete.setVisibility(View.INVISIBLE);
                homework_1.setVisibility(View.VISIBLE);
                homework_2.setVisibility(View.VISIBLE);
                homework_3.setVisibility(View.VISIBLE);
                homework_4.setVisibility(View.VISIBLE);
                homework_5.setVisibility(View.GONE);
                homework_6.setVisibility(View.GONE);
            }
        });
        incomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_completed.setTextColor(0xFF999999);
                textView_incomplete.setTextColor(0xFF333333);
                View_completed.setVisibility(View.INVISIBLE);
                View_incomplete.setVisibility(View.VISIBLE);
                homework_1.setVisibility(View.GONE);
                homework_2.setVisibility(View.GONE);
                homework_3.setVisibility(View.GONE);
                homework_4.setVisibility(View.GONE);
                homework_5.setVisibility(View.VISIBLE);
                homework_6.setVisibility(View.VISIBLE);
            }
        });
        homework_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看已批作业一
            }
        });
        homework_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看已批作业二
            }
        });
        homework_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看待批作业三
            }
        });
        homework_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看待批作业四
            }
        });
        homework_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看未交作业五
            }
        });
        homework_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看未交作业六
            }
        });
        homework_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看所有作业
            }
        });
        constraintLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
        constraintLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
        constraintLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
        constraintLayout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
        constraintLayout_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
        integral_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看完整榜单
            }
        });
    }
}