package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class UploadResourceActivity extends AppCompatActivity {

    private ProgressBar progress_horizontal_1, progress_horizontal_2, progress_horizontal_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resource);
        progress_horizontal_1 = findViewById(R.id.progress_horizontal_1);
        progress_horizontal_2 = findViewById(R.id.progress_horizontal_2);
        progress_horizontal_3 = findViewById(R.id.progress_horizontal_3);
        progress_horizontal_1.setProgress(50);
        progress_horizontal_2.setProgress(67);
        progress_horizontal_3.setProgress(33);
    }
}