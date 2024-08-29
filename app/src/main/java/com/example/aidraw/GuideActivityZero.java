package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class GuideActivityZero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_zero);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 启动新的 Activity
                        startActivity(new Intent(GuideActivityZero.this, ViewpagerActivity.class));
                        // 关闭当前 Activity
                        finish();
                    }
                });
            }
        }, 1000); // 延迟 2 秒（2000 毫秒）

    }
}