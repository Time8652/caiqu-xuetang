package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity {

    private String identity, gender, flag;
    private View view;
    private ConstraintLayout constraintLayout, constraintLayout2;
    private TextView textView, textView2, textView3, textView4, textView5;
    private ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initView();
        initData();
        SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();
        identity = sharedPreferences.getString("Identity", "");
        gender = sharedPreferences.getString("Gender", "");
        if (!identity.equals("") && !gender.equals("")) {
            startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
//            finish();
        }
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("identity")) {
                    identity = "teacher";
                    edit.putString("Identity","teacher");
                    edit.apply();
                    initData2();
                } else {
                    gender = "boy";
                    edit.putString("Gender","boy");
                    edit.apply();
                    startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("identity")) {
                    identity = "student";
                    edit.putString("Identity","student");
                    edit.apply();
                    initData2();
                } else {
                    gender = "girl";
                    edit.putString("Gender","girl");
                    edit.apply();
                    startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void initView() {
        view = findViewById(R.id.view2);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView3);
        textView3 = findViewById(R.id.textView4);
        textView4 = findViewById(R.id.textView5);
        textView5 = findViewById(R.id.textView6);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    private void initData() {
        view.setBackgroundColor(0x00808080);
        textView.setText("请选择您的身份");
        textView2.setText("我是老师");
        textView3.setText("用色彩点亮梦想");
        textView4.setText("我是学生");
        textView5.setText("我是小小艺术家");
//        imageView.setImageDrawable();
//        imageView2.setImageDrawable();
        flag = "identity";
    }

    private void initData2() {
        view.setBackgroundColor(0xFF808080);
        textView.setText("请选择您的性别");
        textView2.setText("我是男生");
        textView3.setText("指尖艺术，刚中带柔");
        textView4.setText("我是女生");
        textView5.setText("色彩绘梦 ，柔美绽放");
//        imageView.setImageDrawable();
//        imageView2.setImageDrawable();
        flag = "gender";
    }
}