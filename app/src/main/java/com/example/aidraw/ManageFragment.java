package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidraw.MyAdapter.StudentAdapter;
import com.example.aidraw.News.StudentNew;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("ValidFragment")
public class ManageFragment extends Fragment {

    private String key;
    private static String classId;
    private TextView grade_class, notice, term, class_name, student_number, class_time;
    private ImageView class_more;
    private ListView listView;
    private LinearLayout homework, integral;
    private RecyclerView recyclerView;
    private ArrayList<StudentNew> studentNewArrayList;
    private URL[] url;
    private String[] classname, class_id, name, gender, id;
    private boolean classIfOpen = false;

    ManageFragment(String key) {
        this.key = key;
    }
    public static String getClassId() {
        return classId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        grade_class = view.findViewById(R.id.grade_class);
        notice = view.findViewById(R.id.notice);
        term = view.findViewById(R.id.textView_term);
        class_name = view.findViewById(R.id.textView_class_name);
        student_number = view.findViewById(R.id.textView_student_number);
        class_time = view.findViewById(R.id.textView_class_time);
        class_more = view.findViewById(R.id.class_more);
        listView = view.findViewById(R.id.listView);
        homework = view.findViewById(R.id.linearLayout_homework);
        integral = view.findViewById(R.id.linearLayout_integral);
        recyclerView = view.findViewById(R.id.recyclerView);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    private void initData() {
        class_more.setImageResource(R.drawable.expand_more_white);
        listView.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://" + LoginActivity.getUrl() + ":8080/teacher/class-list")
                            .header("Authorization", key)
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = dataObject.getJSONArray("list");
                    classname = new String[jsonArray.length()];
                    class_id = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        classname[i] = jsonObject2.getString("grade") + "年级" + jsonObject2.getString("classNum") + "班";
                        class_id[i] = jsonObject2.getString("id");

                    }
                    classId = class_id[0];
                    grade_class.setText(classname[0]);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, classname);
                            listView.setAdapter(arrayAdapter);
                        }
                    });
                    //放在这里是为了异步让classId有值
                    initClass(classId);
                    initStudent(classId);
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

    private void initOnClickListener() {
        class_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!classIfOpen){
                    //open
                    listView.setVisibility(View.VISIBLE);
                    class_more.setImageResource(R.drawable.expand_less_white);
                    TranslateAnimation animation = new TranslateAnimation(
                            Animation.ABSOLUTE, 0f,
                            Animation.ABSOLUTE, 0f,
                            Animation.RELATIVE_TO_SELF, -1f,
                            Animation.RELATIVE_TO_SELF, 0f
                    );
                    animation.setDuration(300);
                    animation.setFillAfter(true);
                    animation.setInterpolator(new DecelerateInterpolator());
                    listView.startAnimation(animation);
                }else {
                    //close
                    listView.clearAnimation();
                    listView.setVisibility(View.GONE);
                    class_more.setImageResource(R.drawable.expand_more_white);
                }
                classIfOpen = !classIfOpen;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            classId = class_id[position];
                            grade_class.setText(classname[position]);
                            initClass(classId);
                            initStudent(classId);
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
                //close
                listView.clearAnimation();
                listView.setVisibility(View.GONE);
                class_more.setImageResource(R.drawable.expand_more_white);
                classIfOpen = !classIfOpen;
            }
        });
        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //作业详细页
                startActivity(new Intent(getActivity(), HomeworkActivity.class));
            }
        });
        integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //积分详细页
                startActivity(new Intent(getActivity(), IntegralActivity.class));
            }
        });
    }

    private void initClass(String classId) {
        new Thread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Log.d( "run11: ",classId.toString());
                    if(classId!=null) {
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/teacher/class?classId=" + classId)
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        Log.d( "run:222 ",responseData);
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    term.setText("当前学期：" + "2024-2025年第一学期");
                    class_name.setText("班级名称：" + classname[0]);
                    student_number.setText("学生人数：" + dataObject.getString("num"));
                    class_time.setText("上课时间：" + dataObject.getString("classTime"));
                    class_more.setImageResource(R.drawable.expand_more_white);
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

    @SuppressLint("NotifyDataSetChanged")
    private void initStudent(String classId) {
        studentNewArrayList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(classId!=null) {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/teacher/student-list?classId=" + classId)
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = dataObject.getJSONArray("list");
                        Log.d("jsonarray ",jsonArray.toString());
                        url = new URL[jsonArray.length()];
                        name = new String[jsonArray.length()];
                        gender = new String[jsonArray.length()];
                        id = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            url[i] = new URL(jsonObject2.get("headerUrl").toString());
                            name[i] = jsonObject2.getString("name");
                            gender[i] = jsonObject2.getString("gender");
                            id[i] = String.valueOf(jsonObject2.getInt("num"));
                        }
                        Log.d( "student1 ",name[0]);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            StudentNew studentNew = new StudentNew(url[i], name[i], gender[i], id[i]);
                            studentNewArrayList.add(studentNew);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StudentAdapter studentAdapter = new StudentAdapter(getContext(), studentNewArrayList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(studentAdapter);
                                studentAdapter.notifyDataSetChanged();
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
}