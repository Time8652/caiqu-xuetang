package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aidraw.MyAdapter.TopicAdapter;
import com.example.aidraw.News.TopicNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadTopicActivity extends AppCompatActivity {

    private ImageView topic_head;
    private LinearLayout completed, incomplete;
    private TextView topic_title, topic_participation, topic_worker, textView_completed, textView_incomplete;
    private View View_completed, View_incomplete;
    private RecyclerView recyclerView;
    private ArrayList<TopicNew> topicNewArrayList;
    private String[] head_url, work_url;
    private String[] name, time, text;
    private int[] star;
    private boolean isGood = true, isFirst = true;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_topic);
        textView_completed = findViewById(R.id.textView_completed);
        textView_completed.setTextColor(0xFF7E64FD);
        View_completed = findViewById(R.id.View_completed);
        View_completed.setBackgroundColor(0xFF7E64FD);
        textView_incomplete = findViewById(R.id.textView_incomplete);
        textView_incomplete.setTextColor(0xFF999999);
        View_incomplete = findViewById(R.id.View_incomplete);
        View_incomplete.setVisibility(View.GONE);
        completed = findViewById(R.id.completed);
        incomplete = findViewById(R.id.incomplete);
        topic_head = findViewById(R.id.topic_head);
        topic_title = findViewById(R.id.topic_title);
        topic_participation = findViewById(R.id.textView);
        topic_worker = findViewById(R.id.textView22);
        topic_title.setText(CommunityFragment.getTopicTitle());
        topic_participation.setText("精选话题" + String.valueOf(CommunityFragment.getTopicParticipation()) + "个");
        recyclerView = findViewById(R.id.recyclerView);
        topicNewArrayList = new ArrayList<>();
        initGood(1);
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGood) {
                    View_completed.setVisibility(View.VISIBLE);
                    textView_completed.setTextColor(0xFF7E64FD);
                    View_completed.setBackgroundColor(0xFF7E64FD);
                    textView_incomplete.setTextColor(0xFF999999);
                    View_incomplete.setVisibility(View.GONE);
                    initGood(1);
                    isGood = !isGood;
                }
            }
        });
        incomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGood) {
                    View_incomplete.setVisibility(View.VISIBLE);
                    textView_completed.setTextColor(0xFF999999);
                    View_completed.setVisibility(View.GONE);
                    textView_incomplete.setTextColor(0xFF7E64FD);
                    View_incomplete.setBackgroundColor(0xFF7E64FD);
                    initGood(0);
                    isGood = !isGood;
                }
            }
        });
    }

    private void initGood(int isGoodI) {
        int finalI = isGoodI;
        new Thread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/common/community-discuss?title=" + CommunityFragment.getTopicTitle() + "&isGood=" + finalI)
                            .header("Authorization", LoginActivity.getKey())
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = dataObject.getJSONArray("list");
                    head_url = new String[jsonArray.length()];
                    name = new String[jsonArray.length()];
                    time = new String[jsonArray.length()];
                    text = new String[jsonArray.length()];
                    work_url = new String[jsonArray.length()];
                    star = new int[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        head_url[i] = jsonObject2.getString("headerUrl");
                        name[i] = jsonObject2.getString("author");
                        time[i] = jsonObject2.getString("createTime");
                        text[i] = jsonObject2.getString("title") + " " + jsonObject2.getString("text");
                        work_url[i] = jsonObject2.getString("imgUrl");
                        star[i] = jsonObject2.getInt("starsNum");
                    }
                    if (isFirst) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(UploadTopicActivity.this).load(work_url[0]).into(topic_head);
                                topic_worker.setText("话题发布者：" + name[0]);
                            }
                        });
                        isFirst = !isFirst;
                    }
                    for (int i = 0; i < jsonArray.length(); i++){
                        TopicNew topicNew = new TopicNew(head_url[i], name[i], time[i], text[i], work_url[i], star[i]);
                        topicNewArrayList.add(topicNew);
                    }
                    runOnUiThread(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            TopicAdapter topicAdapter = new TopicAdapter(UploadTopicActivity.this, topicNewArrayList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(UploadTopicActivity.this));
                            recyclerView.setAdapter(topicAdapter);
                            topicAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UploadTopicActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}