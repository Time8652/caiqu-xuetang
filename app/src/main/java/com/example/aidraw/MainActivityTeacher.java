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

public class MainActivityTeacher extends AppCompatActivity implements View.OnClickListener {

    private String key;
    private FragmentManager fragmentManager;
    private InteractionFragment interactionFragment;
    private ManageFragment manageFragment;
    private CommunityFragment communityFragment;
    private PersonalFragment personalFragment;
    private LinearLayout interactionLinearLayout, manageLinearLayout, communityLinearLayout, personalLinearLayout;
    private ImageView interactionImageView, manageImageView, communityImageView, personalImageView;
    private TextView interactionTextView, manageTextView, communityTextView, personalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        key = LoginActivity.getKey();
        initView();
        // 设置默认的显示界面
        setTabSelection(0);
    }

    private void initView() {
        fragmentManager = getFragmentManager();//用于对Fragment进行管理
        interactionLinearLayout = findViewById(R.id.interaction_layout);
        manageLinearLayout = findViewById(R.id.manage_layout);
        communityLinearLayout = findViewById(R.id.community_layout);
        personalLinearLayout = findViewById(R.id.personal_layout);
        interactionImageView = findViewById(R.id.interaction_imageview);
        manageImageView = findViewById(R.id.manage_imageview);
        communityImageView = findViewById(R.id.community_imageview);
        personalImageView = findViewById(R.id.personal_imageview);
        interactionTextView = findViewById(R.id.interaction_textview);
        manageTextView = findViewById(R.id.manage_textview);
        communityTextView = findViewById(R.id.community_textview);
        personalTextView = findViewById(R.id.personal_textview);
        //处理点击事件
        interactionLinearLayout.setOnClickListener(this);
        manageLinearLayout.setOnClickListener(this);
        communityLinearLayout.setOnClickListener(this);
        personalLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.interaction_layout) {
            setTabSelection(0);
        } else if (v.getId() == R.id.manage_layout) {
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
                interactionImageView.setImageResource(R.drawable.navbar_interaction_purple);//修改布局中的图片
                interactionTextView.setTextColor(Color.parseColor("#FF7E64FD"));//修改字体颜色
                if (interactionFragment == null) {
                    // 如果interactionFragment为空，则创建一个并添加到界面上
                    interactionFragment = new InteractionFragment(key);
                    transaction.add(R.id.fragment, interactionFragment);

                } else {
                    // 如果interactionFragment不为空，则直接将它显示出来
                    transaction.show(interactionFragment);//显示的动作
                }
                break;
            case 1:
                manageImageView.setImageResource(R.drawable.navbar_class_purple);
                manageTextView.setTextColor(Color.parseColor("#FF7E64FD"));
                if (manageFragment == null) {
                    manageFragment = new ManageFragment(key);
                    transaction.add(R.id.fragment, manageFragment);

                } else {
                    transaction.show(manageFragment);
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
        interactionImageView.setImageResource(R.drawable.navbar_interaction_grey);
        interactionTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        manageImageView.setImageResource(R.drawable.navbar_class_grey);
        manageTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        communityImageView.setImageResource(R.drawable.navbar_community_grey);
        communityTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
        personalImageView.setImageResource(R.drawable.navbar_mine_grey);
        personalTextView.setTextColor(Color.parseColor("#FFC7C7C7"));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (interactionFragment != null) {
            transaction.hide(interactionFragment);
        }
        if (manageFragment != null) {
            transaction.hide(manageFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (personalFragment != null) {
            transaction.hide(personalFragment);
        }
    }
}