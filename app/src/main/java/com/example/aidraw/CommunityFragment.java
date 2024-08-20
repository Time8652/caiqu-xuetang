package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("ValidFragment")
public class CommunityFragment extends Fragment {

    private String key;
    private ImageView resources_more, resources_1, resources_2, resources_3, resources_more_and_more, online_challenge_more,
            topic_discussion_more, imageView_1, imageView_2, display_work_more;
    private CardView constraintLayout_1, constraintLayout_2, constraintLayout_3;
    private ConstraintLayout constraintLayout_topic_1, constraintLayout_topic_2, constraintLayout_topic_3;
    private TextView online_challenge_title_1, online_challenge_title_2, online_challenge_title_3, online_challenge_1,
            online_challenge_2, online_challenge_3, topic_1, topic_2, topic_3, participation_1, participation_2,
            participation_3, send;
    private EditText topic;
    private RecyclerView recyclerView;
    private int[] challenge_id, topic_participation;
    private String[] challenge_title, challenge_text, topic_title;
    private static boolean challengeFlag = false, topicFlag = false;
    private static int challengeId;
    private static String challengeTitle, challengeText, topicTitle;

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
        recyclerView = view.findViewById(R.id.recyclerView);
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
                    online_challenge_title_1.setText(challenge_title[0]);
                    online_challenge_title_2.setText(challenge_title[1]);
                    online_challenge_title_3.setText(challenge_title[2]);
                    online_challenge_1.setText(challenge_text[0]);
                    online_challenge_2.setText(challenge_text[1]);
                    online_challenge_3.setText(challenge_text[2]);
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
                    topic_1.setText(topic_title[0]);
                    topic_2.setText(topic_title[1]);
                    topic_3.setText(topic_title[2]);
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
                                participation_1.setText("精选回答" + topic_participation[0] + "个");
                                break;
                            case 1:
                                participation_2.setText("精选回答" + topic_participation[0] + "个");
                                break;
                            case 2:
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
                    challenge_title = new String[3];
                    challenge_text = new String[3];
                    for (int i = 0; i < 3; i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        challenge_title[i] = jsonObject2.getString("title");
                        challenge_text[i] = jsonObject2.getString("text");
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
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
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
    public static boolean getTopicFlag() {
        return topicFlag;
    }
    public static String getTopicTitle() {
        return topicTitle;
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
                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        constraintLayout_topic_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至话题二
                topicFlag = true;
                topicTitle = topic_title[1];
                startActivity(new Intent(getActivity(), UploadTopicActivity.class));
            }
        });
        constraintLayout_topic_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至话题三
                topicFlag = true;
                topicTitle = topic_title[2];
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
            @Override
            public void onClick(View v) {
                //添加话题
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布评论
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