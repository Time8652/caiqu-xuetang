package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class IntelligentActivity extends AppCompatActivity {

    private String key, text;
    private ImageView imageView;
    private EditText editText;
    private TextView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent);
        key = LoginActivity.getKey();
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();
                editText.setText("");
            }
        });
    }
}