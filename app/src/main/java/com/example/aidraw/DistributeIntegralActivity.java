package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DistributeIntegralActivity extends AppCompatActivity {

    private String key, add_reason = null, subtract_reason = null;
    private int add = 0, subtract = 0;
    private EditText editText, editText_other_point, editText_add_other_reason, editText_subtract_other_point, editText_subtract_other_reason;
    private TextView check, add_1, add_2, add_3, add_4, add_5, add_reason_1, add_reason_2, add_reason_3,
            subtract_1, subtract_2, subtract_3, subtract_4, subtract_5, subtract_reason_1, subtract_reason_2;
    private ImageView gone;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribute_integral);
        key = LoginActivity.getKey();
        initView();
    }

    private void initView() {
        editText = findViewById(R.id.editText);
        editText_other_point = findViewById(R.id.editText_other_point);
        editText_add_other_reason = findViewById(R.id.editText_add_other_reason);
        editText_subtract_other_point = findViewById(R.id.editText_subtract_other_point);
        editText_subtract_other_reason = findViewById(R.id.editText_subtract_other_reason);
        add_1 = findViewById(R.id.add_1);
        add_2 = findViewById(R.id.add_2);
        add_3 = findViewById(R.id.add_3);
        add_4 = findViewById(R.id.add_4);
        add_5 = findViewById(R.id.add_5);
        add_reason_1 = findViewById(R.id.add_reason_1);
        add_reason_2 = findViewById(R.id.add_reason_2);
        add_reason_3 = findViewById(R.id.add_reason_3);
        subtract_1 = findViewById(R.id.subtract_1);
        subtract_2 = findViewById(R.id.subtract_2);
        subtract_3 = findViewById(R.id.subtract_3);
        subtract_4 = findViewById(R.id.subtract_4);
        subtract_5 = findViewById(R.id.subtract_5);
        subtract_reason_1 = findViewById(R.id.subtract_reason_1);
        subtract_reason_2 = findViewById(R.id.subtract_reason_2);
        check = findViewById(R.id.check);
        gone = findViewById(R.id.gone);
        gone.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recyclerView);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initOnClickListener() {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelection(editText.length());
                gone.setVisibility(View.VISIBLE);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editText.getText().toString().equals("")) {

                }
            }
        });
        gone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                gone.setVisibility(View.GONE);
            }
        });
        add_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = 1;
                add_1.setTextColor(0xFFFFFFFF);
                add_2.setTextColor(0xFF7E64FD);
                add_3.setTextColor(0xFF7E64FD);
                add_4.setTextColor(0xFF7E64FD);
                add_5.setTextColor(0xFF7E64FD);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = 2;
                add_1.setTextColor(0xFF7E64FD);
                add_2.setTextColor(0xFFFFFFFF);
                add_3.setTextColor(0xFF7E64FD);
                add_4.setTextColor(0xFF7E64FD);
                add_5.setTextColor(0xFF7E64FD);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = 3;
                add_1.setTextColor(0xFF7E64FD);
                add_2.setTextColor(0xFF7E64FD);
                add_3.setTextColor(0xFFFFFFFF);
                add_4.setTextColor(0xFF7E64FD);
                add_5.setTextColor(0xFF7E64FD);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = 4;
                add_1.setTextColor(0xFF7E64FD);
                add_2.setTextColor(0xFF7E64FD);
                add_3.setTextColor(0xFF7E64FD);
                add_4.setTextColor(0xFFFFFFFF);
                add_5.setTextColor(0xFF7E64FD);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = 5;
                add_1.setTextColor(0xFF7E64FD);
                add_2.setTextColor(0xFF7E64FD);
                add_3.setTextColor(0xFF7E64FD);
                add_4.setTextColor(0xFF7E64FD);
                add_5.setTextColor(0xFFFFFFFF);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_2));
            }
        });
        editText_other_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_other_point.setText("+");
                editText_other_point.setSelection(editText_other_point.length());
                add_1.setTextColor(0xFF7E64FD);
                add_2.setTextColor(0xFF7E64FD);
                add_3.setTextColor(0xFF7E64FD);
                add_4.setTextColor(0xFF7E64FD);
                add_5.setTextColor(0xFF7E64FD);
                add_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        editText_other_point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText_other_point.getText().toString().substring(0).equals("+") && editText_other_point.getText().length() > 1) {
                    add = Integer.parseInt(editText_other_point.getText().toString().substring(1, editText_other_point.length()));
                }
            }
        });
        add_reason_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_reason = add_reason_1.getText().toString();
                add_reason_1.setTextColor(0xFFFFFFFF);
                add_reason_2.setTextColor(0xFF7E64FD);
                add_reason_3.setTextColor(0xFF7E64FD);
                add_reason_1.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_reason_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_3.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_reason_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_reason = add_reason_2.getText().toString();
                add_reason_1.setTextColor(0xFF7E64FD);
                add_reason_2.setTextColor(0xFFFFFFFF);
                add_reason_3.setTextColor(0xFF7E64FD);
                add_reason_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_2.setBackground(getResources().getDrawable(R.drawable.point_2));
                add_reason_3.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        add_reason_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_reason = add_reason_3.getText().toString();
                add_reason_1.setTextColor(0xFF7E64FD);
                add_reason_2.setTextColor(0xFF7E64FD);
                add_reason_3.setTextColor(0xFFFFFFFF);
                add_reason_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_3.setBackground(getResources().getDrawable(R.drawable.point_2));
            }
        });
        editText_add_other_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_add_other_reason.setText("");
                add_reason_1.setTextColor(0xFF7E64FD);
                add_reason_2.setTextColor(0xFF7E64FD);
                add_reason_3.setTextColor(0xFF7E64FD);
                add_reason_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                add_reason_3.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        editText_add_other_reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editText_add_other_reason.getText().toString().equals("")) {
                    add_reason = editText_add_other_reason.getText().toString();
                }
            }
        });
        subtract_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract = -1;
                subtract_1.setTextColor(0xFFFFFFFF);
                subtract_2.setTextColor(0xFF7E64FD);
                subtract_3.setTextColor(0xFF7E64FD);
                subtract_4.setTextColor(0xFF7E64FD);
                subtract_5.setTextColor(0xFF7E64FD);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_2));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        subtract_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract = -2;
                subtract_1.setTextColor(0xFF7E64FD);
                subtract_2.setTextColor(0xFFFFFFFF);
                subtract_3.setTextColor(0xFF7E64FD);
                subtract_4.setTextColor(0xFF7E64FD);
                subtract_5.setTextColor(0xFF7E64FD);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_2));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        subtract_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract = -3;
                subtract_1.setTextColor(0xFF7E64FD);
                subtract_2.setTextColor(0xFF7E64FD);
                subtract_3.setTextColor(0xFFFFFFFF);
                subtract_4.setTextColor(0xFF7E64FD);
                subtract_5.setTextColor(0xFF7E64FD);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_2));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        subtract_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract = -4;
                subtract_1.setTextColor(0xFF7E64FD);
                subtract_2.setTextColor(0xFF7E64FD);
                subtract_3.setTextColor(0xFF7E64FD);
                subtract_4.setTextColor(0xFFFFFFFF);
                subtract_5.setTextColor(0xFF7E64FD);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_2));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        subtract_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract = -5;
                subtract_1.setTextColor(0xFF7E64FD);
                subtract_2.setTextColor(0xFF7E64FD);
                subtract_3.setTextColor(0xFF7E64FD);
                subtract_4.setTextColor(0xFF7E64FD);
                subtract_5.setTextColor(0xFFFFFFFF);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_2));
            }
        });
        editText_subtract_other_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_subtract_other_point.setText("-");
                editText_subtract_other_point.setSelection(editText_subtract_other_point.length());
                subtract_1.setTextColor(0xFF7E64FD);
                subtract_2.setTextColor(0xFF7E64FD);
                subtract_3.setTextColor(0xFF7E64FD);
                subtract_4.setTextColor(0xFF7E64FD);
                subtract_5.setTextColor(0xFF7E64FD);
                subtract_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_2.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_3.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_4.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_5.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        editText_subtract_other_point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText_subtract_other_point.getText().toString().substring(0).equals("-") && editText_subtract_other_point.getText().length() > 1) {
                    subtract = -Integer.parseInt(editText_subtract_other_point.getText().toString().substring(1, editText.length()));
                }
            }
        });
        subtract_reason_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract_reason = subtract_reason_1.getText().toString();
                subtract_reason_1.setTextColor(0xFFFFFFFF);
                subtract_reason_2.setTextColor(0xFF7E64FD);
                subtract_reason_1.setBackground(getResources().getDrawable(R.drawable.point_2));
                subtract_reason_2.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        subtract_reason_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract_reason = subtract_reason_2.getText().toString();
                subtract_reason_1.setTextColor(0xFF7E64FD);
                subtract_reason_2.setTextColor(0xFFFFFFFF);
                subtract_reason_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_reason_2.setBackground(getResources().getDrawable(R.drawable.point_2));
            }
        });
        editText_subtract_other_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_subtract_other_reason.setText("");
                subtract_reason_1.setTextColor(0xFF7E64FD);
                subtract_reason_2.setTextColor(0xFF7E64FD);
                subtract_reason_1.setBackground(getResources().getDrawable(R.drawable.point_1));
                subtract_reason_2.setBackground(getResources().getDrawable(R.drawable.point_1));
            }
        });
        editText_subtract_other_reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editText_subtract_other_reason.getText().toString().equals("")) {
                    subtract_reason = editText_subtract_other_reason.getText().toString();
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null && (add != 0 && add_reason != null) && !(subtract != 0 && subtract_reason != null)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String json = "{\n" +
                                        "\t\"studentId\": \"" + editText.getText().toString() + "\",\n" +
                                        "\t\"score\": \"" + add + "\",\n" +
                                        "\t\"message\": \"" + add_reason + "\"\n" +
                                        "}";
                                OkHttpClient client = new OkHttpClient();//创建http客户端
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/student-scores")
                                        .put(RequestBody.create(MediaType.parse("application/json"), json))
                                        .header("Authorization", key)
                                        .build();//创造http请求
                                Response response = client.newCall(request).execute();//执行发送的指令
                                String responseData = response.body().string();//获取后端返回过来的json格式的结果
                                JSONObject jsonObject = new JSONObject(responseData);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DistributeIntegralActivity.this, "积分发布成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DistributeIntegralActivity.this, "积分发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(DistributeIntegralActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                } else if (editText.getText() != null && !(add != 0 && add_reason != null) && (subtract != 0 && subtract_reason != null)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String json = "{\n" +
                                        "\t\"studentId\": \"" + Long.parseLong(editText.getText().toString()) + "\",\n" +
                                        "\t\"score\": \"" + subtract + "\",\n" +
                                        "\t\"message\": \"" + subtract_reason + "\"\n" +
                                        "}";
                                OkHttpClient client = new OkHttpClient();//创建http客户端
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/student-scores-dec")
                                        .put(RequestBody.create(MediaType.parse("application/json"), json))
                                        .build();//创造http请求
                                Response response = client.newCall(request).execute();//执行发送的指令
                                String responseData = response.body().string();//获取后端返回过来的json格式的结果
                                JSONObject jsonObject = new JSONObject(responseData);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DistributeIntegralActivity.this, "积分发布成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DistributeIntegralActivity.this, "积分发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(DistributeIntegralActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(DistributeIntegralActivity.this, "操作有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}