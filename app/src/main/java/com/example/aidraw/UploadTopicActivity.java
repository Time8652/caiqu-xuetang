package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.ChallengeAdapter;
import com.example.aidraw.MyAdapter.TopicAdapter;
import com.example.aidraw.News.ChallengeNew;
import com.example.aidraw.News.TopicNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadTopicActivity extends AppCompatActivity {

    private ImageView topic_head;
    private TextView topic_title, topic_participation, topic_worker;
    private RecyclerView recyclerView;
    private ArrayList<TopicNew> topicNewArrayList;
    private URL[] head_url, work_url;
    private String[] name, time, text;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_topic);
        topic_head = findViewById(R.id.topic_head);
        topic_title = findViewById(R.id.topic_title);
        topic_participation = findViewById(R.id.textView);
        topic_worker = findViewById(R.id.textView2);
        topic_title.setText(CommunityFragment.getTopicTitle());
        topic_participation.setText("精选回答39个");
        topic_worker.setText("话题发布者：罗小昔");
        recyclerView = findViewById(R.id.recyclerView);
        topicNewArrayList = new ArrayList<>();
        if (CommunityFragment.getChallengeFlag()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/common/community-discuss?title=" + CommunityFragment.getTopicTitle() + "&isGood=0")
                                .header("Authorization", LoginActivity.getKey())
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = dataObject.getJSONArray("list");
                        head_url = new URL[jsonArray.length()];
                        name = new String[jsonArray.length()];
                        time = new String[jsonArray.length()];
                        text = new String[jsonArray.length()];
                        work_url = new URL[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            head_url[i] = (URL) jsonObject2.get("headerUrl");
                            name[i] = (String) jsonObject2.get("name");
                            time[i] = (String) jsonObject2.get("createTime");
                            text[i] = (String) jsonObject2.get("text");
                            work_url[i] = (URL) jsonObject2.get("workUrl");
                        }
                        for (int i = 0; i < jsonArray.length(); i++){
                            TopicNew topicNew = new TopicNew(head_url[i], name[i], time[i], text[i], work_url[i]);
                            topicNewArrayList.add(topicNew);
                        }
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
        TopicAdapter topicAdapter = new TopicAdapter(this, topicNewArrayList);
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
    }
}