package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aidraw.MyAdapter.ViewpagerAdapter;

public class ViewpagerActivity extends AppCompatActivity implements GuideFragmentFour.OnFragmentInteractionListener{

    private TextView textView;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        textView = findViewById(R.id.textView);
        viewPager2 = findViewById(R.id.viewpager2);
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(this);
        viewPager2.setAdapter(viewpagerAdapter);
        viewPager2.setUserInputEnabled(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.getIdentity().equals("teacher")) {
                    startActivity(new Intent(ViewpagerActivity.this, MainActivityTeacher.class));
                } else {
                    startActivity(new Intent(ViewpagerActivity.this, MainActivityStudent.class));
                }
                finish();
            }
        });

    }

    @Override
    public void onFragmentInteraction() {
        finish();
    }
}