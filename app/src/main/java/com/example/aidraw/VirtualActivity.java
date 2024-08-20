package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VirtualActivity extends AppCompatActivity {

    private String key;
    private CardView imageView_1, imageView_2, imageView_3, imageView_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual);
        key = LoginActivity.getKey();
        imageView_1 = findViewById(R.id.imageView_1);
        imageView_2 = findViewById(R.id.imageView_2);
        imageView_3 = findViewById(R.id.imageView_3);
        imageView_4 = findViewById(R.id.imageView_4);
        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}