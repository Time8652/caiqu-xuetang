package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.IntegralAdapter;
import com.example.aidraw.News.IntegralNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IntegralActivity extends AppCompatActivity {

    private String key;
    private TextView distribute_integral;
    private RecyclerView recyclerView;
    private ArrayList<IntegralNew> integralNewArrayList;
    private URL[] url;
    private String[] rank, name, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        key = LoginActivity.getKey();
        distribute_integral = findViewById(R.id.distribute_integral);
        recyclerView = findViewById(R.id.recyclerView);
        initPoint(ManageFragment.getClassId());
        distribute_integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntegralActivity.this, DistributeIntegralActivity.class));
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initPoint(Long classId) {
        integralNewArrayList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/common/score-rank?classId=" + classId)
                            .header("Authorization", key)
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = dataObject.getJSONArray("list");
                    url = new URL[jsonArray.length()];
                    rank = new String[jsonArray.length()];
                    name = new String[jsonArray.length()];
                    score = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        url[i] = (URL) jsonObject2.get("headerUrl");
                        rank[i] = (String) jsonObject2.get("rank");
                        name[i] = (String) jsonObject2.get("name");
                        score[i] = (String) jsonObject2.get("score");
                    }
                    for (int i = 0; i < jsonArray.length(); i++){
                        IntegralNew integralNew = new IntegralNew(url[i], rank[i], name[i], score[i]);
                        integralNewArrayList.add(integralNew);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(IntegralActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
        IntegralAdapter integralAdapter = new IntegralAdapter(this, integralNewArrayList);
        recyclerView.setAdapter(integralAdapter);
        integralAdapter.notifyDataSetChanged();
    }
}