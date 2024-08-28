package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class GuideActivityZero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_zero);
        try {
            Thread.sleep(2000);
            startActivity(new Intent(this, ViewpagerActivity.class));
            finish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}