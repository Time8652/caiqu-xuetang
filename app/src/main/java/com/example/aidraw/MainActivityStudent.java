package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityStudent extends AppCompatActivity implements View.OnClickListener {

    private String key;
    private FragmentManager fragmentManager;
    private ClassFragment classFragment;
    private ResourceFragment resourceFragment;
    private CommunityFragment communityFragment;
    private PersonalFragment personalFragment;
    private LinearLayout classLinearLayout, resourceLinearLayout, communityLinearLayout, personalLinearLayout;
    private ImageView classImageView, resourceImageView, communityImageView, personalImageView;
    private TextView classTextView, resourceTextView, communityTextView, personalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        key = LoginActivity.getKey();
        initView();
        // 设置默认的显示界面
        setTabSelection(0);
    }

    private void initView() {
        fragmentManager = getFragmentManager();//用于对Fragment进行管理
        classLinearLayout = findViewById(R.id.class_layout);
        resourceLinearLayout = findViewById(R.id.resource_layout);
        communityLinearLayout = findViewById(R.id.community_layout);
        personalLinearLayout = findViewById(R.id.personal_layout);
        classImageView = findViewById(R.id.class_imageview);
        resourceImageView = findViewById(R.id.resource_imageview);
        communityImageView = findViewById(R.id.community_imageview);
        personalImageView = findViewById(R.id.personal_imageview);
        classTextView = findViewById(R.id.class_textview);
        resourceTextView = findViewById(R.id.resource_textview);
        communityTextView = findViewById(R.id.community_textview);
        personalTextView = findViewById(R.id.personal_textview);
        //处理点击事件
        classLinearLayout.setOnClickListener(this);
        resourceLinearLayout.setOnClickListener(this);
        communityLinearLayout.setOnClickListener(this);
        personalLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.class_layout) {
            setTabSelection(0);
        } else if (v.getId() == R.id.resource_layout) {
            setTabSelection(1);
        } else if (v.getId() == R.id.community_layout) {
            setTabSelection(2);
        } else if (v.getId() == R.id.personal_layout) {
            setTabSelection(3);
        }
    }

    private void setTabSelection(int index) {
        clearSelection();// 每次选中之前先清除掉上次的选中状态
        FragmentTransaction transaction = fragmentManager.beginTransaction();// 开启一个Fragment事务
        hideFragments(transaction);// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index) {
            case 0:
                classImageView.setImageResource(R.drawable.navbar_class_purple);//修改布局中的图片
                classTextView.setTextColor(Color.parseColor("#FF7E64FD"));//修改字体颜色
                if (classFragment == null) {
                    // 如果classFragment为空，则创建一个并添加到界面上
                    classFragment = new ClassFragment(key);
                    transaction.add(R.id.fragment, classFragment);

                } else {
                    // 如果classFragment不为空，则直接将它显示出来
                    transaction.show(classFragment);//显示的动作
                }
                break;
            case 1:
                resourceImageView.setImageResource(R.drawable.navbar_class_purple);
                resourceTextView.setTextColor(Color.parseColor("#FF7E64FD"));
                if (resourceFragment == null) {
                    resourceFragment = new ResourceFragment(key);
                    transaction.add(R.id.fragment, resourceFragment);

                } else {
                    transaction.show(resourceFragment);
                }
                break;
            case 2:
                communityImageView.setImageResource(R.drawable.navbar_community_purple);
                communityTextView.setTextColor(Color.parseColor("#FF7E64FD"));
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment(key);
                    transaction.add(R.id.fragment, communityFragment);

                } else {
                    transaction.show(communityFragment);
                }
                break;
            case 3:
                personalImageView.setImageResource(R.drawable.navbar_mine_purple);
                personalTextView.setTextColor(Color.parseColor("#FF7E64FD"));
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment(key);
                    transaction.add(R.id.fragment, personalFragment);

                } else {
                    transaction.show(personalFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        classImageView.setImageResource(R.drawable.navbar_class_grey);
        classTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        resourceImageView.setImageResource(R.drawable.navbar_class_grey);
        resourceTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        communityImageView.setImageResource(R.drawable.navbar_community_grey);
        communityTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        personalImageView.setImageResource(R.drawable.navbar_mine_grey);
        personalTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (classFragment != null) {
            transaction.hide(classFragment);
        }
        if (resourceFragment != null) {
            transaction.hide(resourceFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (personalFragment != null) {
            transaction.hide(personalFragment);
        }
    }
}