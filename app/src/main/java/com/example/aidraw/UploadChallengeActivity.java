package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.ChallengeAdapter;
import com.example.aidraw.News.ChallengeNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadChallengeActivity extends AppCompatActivity {

    private TextView challenge_title, challenge_context;
    private RecyclerView recyclerView;
    private ArrayList<ChallengeNew> challengeNewArrayList;
    private String[] head_url, work_url;
    private String[] name, time;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_challenge);
        challenge_title = findViewById(R.id.challenge_title);
        challenge_context = findViewById(R.id.challenge_context);
        challenge_title.setText(CommunityFragment.getChallengeTitle());
        challenge_context.setText(CommunityFragment.getChallengeText());
        recyclerView = findViewById(R.id.recyclerView);
        challengeNewArrayList = new ArrayList<>();
        if (CommunityFragment.getChallengeFlag()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/common/challenge-works?challengeId=" + CommunityFragment.getChallengeId())
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
                        work_url = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            head_url[i] = jsonObject2.getString("headUrl");
                            name[i] = jsonObject2.getString("name");
                            time[i] = jsonObject2.getString("createTime");
                            work_url[i] = jsonObject2.getString("workUrl");
                        }
                        for (int i = 0; i < jsonArray.length(); i++){
                            ChallengeNew challengeNew = new ChallengeNew(head_url[i], name[i], time[i], work_url[i]);
                            challengeNewArrayList.add(challengeNew);
                        }
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               ChallengeAdapter challengeAdapter = new ChallengeAdapter(UploadChallengeActivity.this, challengeNewArrayList);
                               recyclerView.setLayoutManager(new LinearLayoutManager(UploadChallengeActivity.this));
                               recyclerView.setAdapter(challengeAdapter);
                               challengeAdapter.notifyDataSetChanged();
                           }
                       });

                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UploadChallengeActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }

    }
}