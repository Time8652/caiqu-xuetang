package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.News.StudentNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeworkActivity extends AppCompatActivity {

    private String key;
    private EditText title, content;
    private ImageView imageView, imageView_day, imageView_time;
    private TextView deadline_day, deadline_time, cancel, save, send;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        key = LoginActivity.getKey();
        initView();
        initData();
    }

    private void initView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        imageView = findViewById(R.id.imageView);
        imageView_day = findViewById(R.id.imageView_day);
        imageView_time = findViewById(R.id.imageView_time);
        deadline_day = findViewById(R.id.deadline_day);
        deadline_time = findViewById(R.id.deadline_time);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        send = findViewById(R.id.send);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        if (flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework_draft?classId=" + ManageFragment.getClassId())
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        title.setText(dataObject.getString("title"));
                        content.setText(dataObject.getString("text"));
                        deadline_day.setText(dataObject.getString("endTime").substring(0, 10));
                        deadline_time.setText(dataObject.getString("endTime").substring(11, 19));
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void initOnClickListener() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    title.setSelection(title.length());
                } else {
                    title.setText("");
                }
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    content.setSelection(content.length());
                } else {
                    content.setText("");
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = "{\n" +
                                    "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                    "\t\"title\": \"" + title.getText().toString() + "\",\n" +
                                    "\t\"text\": \"" + content.getText().toString() + "\",\n" +
                                    "\t\"endTime\": \"" + deadline_day.getText().toString() + "\t" + deadline_time.getText().toString() + "\",\n" +
                                    "\t\"status\": \"" + "0" + "\"\n" +
                                    "}";
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            Request request = new Request.Builder()
                                    .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();//创造http请求
                            Response response = client.newCall(request).execute();//执行发送的指令
                            String responseData = response.body().string();//获取后端返回过来的json格式的结果
                            JSONObject jsonObject = new JSONObject(responseData);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(HomeworkActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                        flag = true;
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(HomeworkActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
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
                                    "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                    "\t\"title\": \"" + title.getText().toString() + "\",\n" +
                                    "\t\"text\": \"" + content.getText().toString() + "\",\n" +
                                    "\t\"endTime\": \"" + deadline_day.getText().toString() + "\t" + deadline_time.getText().toString() + "\",\n" +
                                    "\t\"status\": \"" + "1" + "\"\n" +
                                    "}";
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            Request request = new Request.Builder()
                                    .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();//创造http请求
                            Response response = client.newCall(request).execute();//执行发送的指令
                            String responseData = response.body().string();//获取后端返回过来的json格式的结果
                            JSONObject jsonObject = new JSONObject(responseData);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(HomeworkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                        flag = false;
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(HomeworkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}