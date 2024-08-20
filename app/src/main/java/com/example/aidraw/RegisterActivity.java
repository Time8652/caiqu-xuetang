package com.example.aidraw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 0;
    private String number;
    private int code;
    private Button bt;
    private TextView tv;
    private EditText et1,et2,et3;
    private CheckBox cb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_password = et1.getText().toString();
                String password1 = et2.getText().toString();
                String password2 = et3.getText().toString();
                boolean checkbox = cb.isChecked();
                if (password1.equals(password2) && checkbox) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                    if (sharedPreferences.getString("Identity", "").equals("teacher")) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String json = "{\n" +
                                            "\t\"username\": " + number + ",\n" +
                                            "\t\"old_password\": " + old_password + "\n" +
                                            "\t\"new_password\": " + password1 + ",\n" +
                                            "\t\"re_password\": " + password2 + ",\n" +
                                            "}";
                                    OkHttpClient client = new OkHttpClient();//创建http客户端
                                    Request request = new Request.Builder()
                                            .url("http://" + LoginActivity.getUrl() + ":8989/teacher/password")
                                            .put(RequestBody.create(MediaType.parse("application/json"), json))
                                            .build();//创造http请求
                                    Response response = client.newCall(request).execute();//执行发送的指令
                                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                                    JSONObject jsonObject = new JSONObject(responseData);
                                    code = jsonObject.getInt("code");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String json = "{\n" +
                                            "\t\"username\": " + number + ",\n" +
                                            "\t\"old_password\": " + old_password + "\n" +
                                            "\t\"new_password\": " + password1 + ",\n" +
                                            "}";
                                    OkHttpClient client = new OkHttpClient();//创建http客户端
                                    Request request = new Request.Builder()
                                            .url("http://" + LoginActivity.getUrl() + ":8989/student/password")
                                            .put(RequestBody.create(MediaType.parse("application/json"), json))
                                            .build();//创造http请求
                                    Response response = client.newCall(request).execute();//执行发送的指令
                                    String responseData = response.body().string();//获取后端返回过来的json格式的结果
                                    JSONObject jsonObject = new JSONObject(responseData);
                                    code = jsonObject.getInt("code");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                    if (code == 0) {
                        Register(number, password1, true);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        bt = findViewById(R.id.btn_register);
        tv = findViewById(R.id.tv_number);
        et1 = findViewById(R.id.et_old_password);
        et2 = findViewById(R.id.et_password1);
        et3 = findViewById(R.id.et_password2);
        cb = findViewById(R.id.checkBox);
    }

    private void initData() {
        number = LoginActivity.getNow_number();
        tv.setText(number);
    }

    private void Register(String number, String password, boolean checkbox) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("Number",number);
        bundle.putString("Password",password);
        intent.putExtras(bundle);
        setResult(RESULT_CODE,intent);
        Toast.makeText(RegisterActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}