package com.example.aidraw;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.WorkAdapter;
import com.example.aidraw.News.WorkNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorkActivity extends AppCompatActivity {

    private String key;
    private URL[] work_url;
    private ArrayList<WorkNew> workNewArrayList;
    private String[] work_title, work_like, work_time, work_final_time;
    private RecyclerView recyclerView;
    private String classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        key = LoginActivity.getKey();
        classId = ManageFragment.getClassId();
        recyclerView = findViewById(R.id.recyclerView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/common/community-works")
                            .header("Authorization", key)
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = dataObject.getJSONArray("list");
                    work_url = new URL[jsonArray.length()];
                    work_title = new String[jsonArray.length()];
                    work_like = new String[jsonArray.length()];
                    work_time = new String[jsonArray.length()];
                    work_final_time = new String[jsonArray.length()];
                    Calendar calendar = Calendar.getInstance();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        work_url[i] = new URL(jsonObject2.get("worksUrl").toString());
                        work_title[i] = jsonObject2.getString("title");
                        work_like[i] = jsonObject2.getString("starsNum");
                        work_time[i] = jsonObject2.getString("createTime");
                        if (calendar.get((Calendar.YEAR)) - Integer.parseInt(work_time[i].substring(0, 4)) > 0) {
                            work_final_time[i] = String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_time[i].substring(0, 4))) + "年前";
                        } else if (calendar.get((Calendar.MONTH)) + 1 - Integer.parseInt(work_time[i].substring(5, 7)) > 0) {
                            work_final_time[i] = String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_time[i].substring(5, 7))) + "月前";
                        } else if (calendar.get((Calendar.DAY_OF_MONTH)) - Integer.parseInt(work_time[i].substring(8, 10)) > 0) {
                            work_final_time[i] = String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_time[i].substring(8, 10))) + "日前";
                        } else if (calendar.get((Calendar.HOUR_OF_DAY)) - Integer.parseInt(work_time[i].substring(11, 13)) > 0) {
                            work_final_time[i] = String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_time[i].substring(11, 13))) + "小时前";
                        } else if (calendar.get((Calendar.MINUTE)) - Integer.parseInt(work_time[i].substring(14, 16)) > 0) {
                            work_final_time[i] = String.valueOf(calendar.get(Calendar.YEAR) - Integer.parseInt(work_time[i].substring(14, 16))) + "分钟前";
                        } else {
                            work_final_time[i] = "刚刚";
                        }
                    }
                    workNewArrayList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        WorkNew workNew = new WorkNew(work_url[i], work_title[i], work_like[i], work_time[i]);
                        workNewArrayList.add(workNew);
                    }
                    runOnUiThread(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            WorkAdapter workAdapter = new WorkAdapter(WorkActivity.this, workNewArrayList);
                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(staggeredGridLayoutManager);
                            recyclerView.setAdapter(workAdapter);
                            workAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WorkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}