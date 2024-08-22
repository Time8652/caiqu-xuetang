package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IntelligentActivity extends AppCompatActivity {

    private String key;

    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1, REQUEST_CODE_PHOTO = 0;
    private ImageView imageView_intelligent;
    private EditText editText;
    private TextView send, intelligent_image;
    private File file = null;
    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent);
        key = LoginActivity.getKey();
        imageView_intelligent = findViewById(R.id.imageView_intelligent);
        intelligent_image = findViewById(R.id.intelligent_image);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);
        imageView_intelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查外部存储空间的权限
                if (ContextCompat.checkSelfPermission(IntelligentActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(IntelligentActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PHOTO);
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = "{\n" +
                                    "\t\"text\": \"" + editText.getText().toString() + "\"\n" +
                                    "}";
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);//通过表单上传文件
                            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);//上传的文件以及类型
                            requestBody.addFormDataPart("file", file.getName(), fileBody).addFormDataPart("homeworkPostDTO", json);
                            Request request = new Request.Builder()
                                    .url("http://" + LoginActivity.getUrl() + ":8080/ai/muti")
                                    .post(requestBody.build())
                                    .build();//创造http请求
                            Response response = client.newCall(request).execute();//执行发送的指令
                            String responseData = response.body().string();//获取后端返回过来的json格式的结果
                            JSONObject jsonObject = new JSONObject(responseData);
                            editText.setText("");
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(IntelligentActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取失败！无法使用本功能！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            uri = data.getData();
            if (uri != null) {
                String path = uri.getPath();
                if (path != null) {
                    file = new File(path);
                    intelligent_image.setText(path);
                }
            }
        }
    }
}