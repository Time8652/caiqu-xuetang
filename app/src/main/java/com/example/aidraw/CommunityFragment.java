package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.WorkAdapter;
import com.example.aidraw.News.WorkNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SuppressLint("ValidFragment")
public class CommunityFragment extends Fragment {

    private String key;
    private ImageView resources_more, resources_1, resources_2, resources_3, resources_more_and_more,
            online_challenge_more, topic_discussion_more, imageView_1, imageView_2, display_work_more;
    private CardView constraintLayout_1, constraintLayout_2, constraintLayout_3;
    private ConstraintLayout constraintLayout_topic_1, constraintLayout_topic_2, constraintLayout_topic_3;
    private TextView online_challenge_title_1, online_challenge_title_2, online_challenge_title_3, online_challenge_1,
            online_challenge_2, online_challenge_3, topic_1, topic_2, topic_3, participation_1, participation_2,
            participation_3, send;
    private EditText topic, ed_topic_title;
    private RecyclerView recyclerView;
    private ArrayList<WorkNew> workNewArrayList;
    private URL[] work_url;
    private int[] challenge_id, topic_participation, topic_Participation;
    private String[] challenge_title, challenge_text, topic_title, work_title, work_like, work_time, work_final_time;
    private static boolean challengeFlag = false, topicFlag = false;
    private static int challengeId;
    private static String challengeTitle, challengeText, topicTitle;
    private static int topicParticipation;
    private URL topic_url;

    CommunityFragment(String key) {
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        resources_more = view.findViewById(R.id.resources_more);
        resources_1 = view.findViewById(R.id.resources_1);
        resources_2 = view.findViewById(R.id.resources_2);
        resources_3 = view.findViewById(R.id.resources_3);
        resources_more_and_more = view.findViewById(R.id.resources_more_and_more);
        online_challenge_more = view.findViewById(R.id.online_challenge_more);
        topic_discussion_more = view.findViewById(R.id.topic_discussion_more);
        imageView_1 = view.findViewById(R.id.imageView_1);
        imageView_2 = view.findViewById(R.id.imageView_2);
        display_work_more = view.findViewById(R.id.display_work_more);
        constraintLayout_1 = view.findViewById(R.id.constraintLayout_1);
        constraintLayout_2 = view.findViewById(R.id.constraintLayout_2);
        constraintLayout_3 = view.findViewById(R.id.constraintLayout_3);
        constraintLayout_topic_1 = view.findViewById(R.id.constraintLayout_topic_1);
        constraintLayout_topic_2 = view.findViewById(R.id.constraintLayout_topic_2);
        constraintLayout_topic_3 = view.findViewById(R.id.constraintLayout_topic_3);
        online_challenge_title_1 = view.findViewById(R.id.online_challenge_title_1);
        online_challenge_title_2 = view.findViewById(R.id.online_challenge_title_2);
        online_challenge_title_3 = view.findViewById(R.id.online_challenge_title_3);
        online_challenge_1 = view.findViewById(R.id.online_challenge_1);
        online_challenge_2 = view.findViewById(R.id.online_challenge_2);
        online_challenge_3 = view.findViewById(R.id.online_challenge_3);
        topic_1 = view.findViewById(R.id.topic_1);
        topic_2 = view.findViewById(R.id.topic_2);
        topic_3 = view.findViewById(R.id.topic_3);
        participation_1 = view.findViewById(R.id.participation_1);
        participation_2 = view.findViewById(R.id.participation_2);
        participation_3 = view.findViewById(R.id.participation_3);
        send = view.findViewById(R.id.send);
        topic = view.findViewById(R.id.topic);
        ed_topic_title = view.findViewById(R.id.topic_title);
        recyclerView = view.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/common/challenge")
                            .header("Authorization", key)
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = dataObject.getJSONArray("list");
                    challenge_id = new int[3];
                    challenge_title = new String[3];
                    challenge_text = new String[3];
                    for (int i = 0; i < 3; i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        challenge_id[i] = jsonObject2.getInt("id");
                        challenge_title[i] = jsonObject2.getString("title");
                        challenge_text[i] = jsonObject2.getString("text");
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            online_challenge_title_1.setText(challenge_title[0]);
                            online_challenge_title_2.setText(challenge_title[1]);
                            online_challenge_title_3.setText(challenge_title[2]);
                            online_challenge_1.setText(challenge_text[0]);
                            online_challenge_2.setText(challenge_text[1]);
                            online_challenge_3.setText(challenge_text[2]);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/common/community-discuss-list")
                            .header("Authorization", key)
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    topic_title = new String[3];
                    for (int i = 0; i < 3; i++) {
                        topic_title[i] = jsonArray.getString(i);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            topic_1.setText(topic_title[0]);
                            topic_2.setText(topic_title[1]);
                            topic_3.setText(topic_title[2]);
                            topic_Participation = new int[3];
                            // ui 更新完启动后一个线程，确保后续请求执行时，topic_title已经初始化
                            for (int i = 0; i < 3; i++) {
                                int finalI = i;
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            OkHttpClient client = new OkHttpClient();//创建http客户端
                                            Request request = new Request.Builder()
                                                    .url("http://" + LoginActivity.getUrl() + ":8080/common/good-discuss-number?title=" + topic_title[finalI])
                                                    .header("Authorization", key)
                                                    .get()
                                                    .build();//创造http请求
                                            Response response = client.newCall(request).execute();//执行发送的指令
                                            String responseData = response.body().string();//获取后端返回过来的json格式的结果
                                            JSONObject jsonObject = new JSONObject(responseData);
                                            topic_participation = new int[1];
                                            topic_participation[0] = jsonObject.getInt("data");
                                            switch (finalI) {
                                                case 0:
                                                    topic_Participation[0] = topic_participation[0];
                                                    participation_1.setText("精选回答" + topic_participation[0] + "个");
                                                    break;
                                                case 1:
                                                    topic_Participation[1] = topic_participation[0];
                                                    participation_2.setText("精选回答" + topic_participation[0] + "个");
                                                    break;
                                                case 2:
                                                    topic_Participation[2] = topic_participation[0];
                                                    participation_3.setText("精选回答" + topic_participation[0] + "个");
                                                    break;
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                }).start();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
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
                    getActivity().runOnUiThread(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            WorkAdapter workAdapter = new WorkAdapter(getContext(), workNewArrayList);
                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(staggeredGridLayoutManager);
                            recyclerView.setAdapter(workAdapter);
                            workAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    public static boolean getChallengeFlag() {
        return challengeFlag;
    }
    public static int getChallengeId() {
        return challengeId;
    }
    public static String getChallengeTitle() {
        return challengeTitle;
    }
    public static String getChallengeText() {
        return challengeText;
    }
    public static String getTopicTitle() {
        return topicTitle;
    }
    public static int getTopicParticipation() {
        return topicParticipation;
    }

    private void initOnClickListener() {
        resources_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至资源详细页
                startActivity(new Intent(getActivity(), UploadResourceActivity.class));
            }
        });
        resources_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至资源详细页
                startActivity(new Intent(getActivity(), UploadResourceActivity.class));
            }
        });
        resources_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至资源详细页
                startActivity(new Intent(getActivity(), UploadResourceActivity.class));
            }
        });
        resources_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至资源详细页
                startActivity(new Intent(getActivity(), UploadResourceActivity.class));
            }
        });
        resources_more_and_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至资源详细页
                startActivity(new Intent(getActivity(), UploadResourceActivity.class));
            }
        });
        online_challenge_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至挑战详细页
                challengeFlag = false;
//                startActivity(new Intent(getActivity(), UploadChallengeActivity.class));
            }
        });
        constraintLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至挑战一
                challengeId = challenge_id[0];
                challengeTitle = challenge_title[0];
                challengeText = challenge_text[0];
                challengeFlag = true;
                startActivity(new Intent(getActivity(), UploadChallengeActivity.class));
            }
        });
        constraintLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至挑战二
                challengeId = challenge_id[1];
                challengeTitle = challenge_title[1];
                challengeText = challenge_text[1];
                challengeFlag = true;
                startActivity(new Intent(getActivity(), UploadChallengeActivity.class));
            }
        });
        constraintLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至挑战三
                challengeId = challenge_id[2];
                challengeTitle = challenge_title[2];
                challengeText = challenge_text[2];
                challengeFlag = true;
                startActivity(new Intent(getActivity(), UploadChallengeActivity.class));
            }
        });
        topic_discussion_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至话题详细页
                topicFlag = false;
//                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        constraintLayout_topic_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至置顶话题一
                topicFlag = true;
                topicTitle = topic_title[0];
                topicParticipation = topic_Participation[0];
                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        constraintLayout_topic_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至话题二
                topicFlag = true;
                topicTitle = topic_title[1];
                topicParticipation = topic_Participation[1];
                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        constraintLayout_topic_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至话题三
                topicFlag = true;
                topicTitle = topic_title[2];
                topicParticipation = topic_Participation[2];
                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传图片到文本框
            }
        });
        imageView_2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //添加话题
                ed_topic_title.setText("#");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布评论
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json;
                            if (LoginActivity.getIdentity().equals("teacher")) {
                                json = "{\n" +
                                        "\t\"text\": \"" + topic.getText().toString() + "\",\n" +
                                        "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                        "\t\"title\": \"" + ed_topic_title.getText().toString() + "\",\n" +
                                        "\t\"type\": \"" + "0" + "\"\n" +
                                        "}";
                            } else {
                                json = "{\n" +
                                        "\t\"text\": \"" + topic.getText().toString() + "\",\n" +
                                        "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                        "\t\"title\": \"" + ed_topic_title.getText().toString() + "\",\n" +
                                        "\t\"type\": \"" + "1" + "\"\n" +
                                        "}";
                            }
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            Request request = new Request.Builder()
                                    .url("http://" + LoginActivity.getUrl() + ":8080/common/community-discuss")
                                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                                    .build();//创造http请求
                            Response response = client.newCall(request).execute();//执行发送的指令
                            String responseData = response.body().string();//获取后端返回过来的json格式的结果
                            JSONObject jsonObject = new JSONObject(responseData);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "发表成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "发表失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        display_work_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至作品详细页
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至作品详细页
            }
        });
    }
}