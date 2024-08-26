package com.example.aidraw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeworkActivity extends AppCompatActivity {

    private String key;
    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1, REQUEST_CODE_PHOTO = 0;
    private EditText title, content;
    private ImageView imageView, imageView_day, imageView_time;
    private TextView deadline_day, deadline_time, cancel, save, send, homework_image;
    private File file = null;
    private Uri uri = null;
    private String path = null;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        key = LoginActivity.getKey();
        initView();
        initData();
    }

    private void initView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        imageView = findViewById(R.id.imageView);
        imageView_day = findViewById(R.id.imageView_day);
        imageView_time = findViewById(R.id.imageView_time);
        deadline_day = findViewById(R.id.deadline_day);
        deadline_time = findViewById(R.id.deadline_time);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        send = findViewById(R.id.send);
        homework_image = findViewById(R.id.homework_image);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
        flag = sharedPreferences.getBoolean("Homework", false);
        if (flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();//创建http客户端
                        Request request = new Request.Builder()
                                .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework_draft?classId=" + ManageFragment.getClassId())
                                .header("Authorization", key)
                                .get()
                                .build();//创造http请求
                        Response response = client.newCall(request).execute();//执行发送的指令
                        String responseData = response.body().string();//获取后端返回过来的json格式的结果
                        JSONObject jsonObject = new JSONObject(responseData);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        title.setText(dataObject.getString("title"));
                        content.setText(dataObject.getString("text"));
                        deadline_day.setText(dataObject.getString("endTime").substring(0, 10));
                        deadline_time.setText(dataObject.getString("endTime").substring(11, 19));
                        file = downloadImage(dataObject.get("homeworkUrl").toString());
                        homework_image.setText(dataObject.get("homeworkUrl").toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void initOnClickListener() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    title.setSelection(title.length());
                } else {
                    title.setText("");
                }
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    content.setSelection(content.length());
                } else {
                    content.setText("");
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查外部存储空间的权限
                if (ContextCompat.checkSelfPermission(HomeworkActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HomeworkActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PHOTO);
                }
            }
        });
        DatePickerDialog.OnDateSetListener datePickerDialog1 = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String months, days;
                if (month < 9) {
                    months = "0" + String.valueOf(month + 1);
                } else {
                    months = String.valueOf(month + 1);
                }
                if (dayOfMonth < 10) {
                    days = "0" + String.valueOf(dayOfMonth);
                } else {
                    days = String.valueOf(dayOfMonth);
                }
                deadline_day.setText(String.valueOf(year) + "-" + months + "-" + days);
            }
        };
        imageView_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HomeworkActivity.this, datePickerDialog1, 2024, 0, 1);
                datePickerDialog.show();
            }
        });
        TimePickerDialog.OnTimeSetListener timeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hours, minutes;
                if (hourOfDay < 10) {
                    hours = "0" + String.valueOf(hourOfDay);
                } else {
                    hours = String.valueOf(hourOfDay);
                }
                if (minute < 10) {
                    minutes = "0" + String.valueOf(minute);
                } else {
                    minutes = String.valueOf(minute);
                }
                deadline_time.setText(hours + ":" + minutes + ":00");
            }
        };
        imageView_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(HomeworkActivity.this, timeSetListener1, 1, 1, true);
                timePickerDialog.show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = "{\n" +
                                    "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                    "\t\"title\": \"" + title.getText().toString() + "\",\n" +
                                    "\t\"text\": \"" + content.getText().toString() + "\",\n" +
                                    "\t\"endTime\": \"" + deadline_day.getText().toString() + "T" + deadline_time.getText().toString() + "\",\n" +
                                    "\t\"status\": \"" + "0" + "\"\n" +
                                    "}";
                            RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), json);
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);//通过表单上传文件
                            if (file != null) {
                                Log.d("TAG", "run: "+file);
                                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);//上传的文件以及类型
                                requestBody.addFormDataPart("file", file.getName(), fileBody)
                                        .addFormDataPart("homeworkPostDTO", "homeworkPostDTO.json", jsonBody);
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                        .post(requestBody.build())
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
                                            Toast.makeText(HomeworkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();
                                            edit.putBoolean("Homework", false);
                                            edit.apply();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(HomeworkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                requestBody.addFormDataPart("homeworkPostDTO", "homeworkPostDTO.json", jsonBody);
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                        .post(requestBody.build())
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
                                            Toast.makeText(HomeworkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();
                                            edit.putBoolean("Homework", false);
                                            edit.apply();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(HomeworkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String json = "{\n" +
                                    "\t\"classId\": \"" + ManageFragment.getClassId() + "\",\n" +
                                    "\t\"title\": \"" + title.getText().toString() + "\",\n" +
                                    "\t\"text\": \"" + content.getText().toString() + "\",\n" +
                                    "\t\"endTime\": \"" + deadline_day.getText().toString() + "T" + deadline_time.getText().toString() + "\",\n" +
                                    "\t\"status\": \"" + "1" + "\"\n" +
                                    "}";
                            RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), json);
                            OkHttpClient client = new OkHttpClient();//创建http客户端
                            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);//通过表单上传文件
                            if (file != null) {
                                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);//上传的文件以及类型
                                requestBody.addFormDataPart("file", file.getName(), fileBody)
                                        .addFormDataPart("homeworkPostDTO", "homeworkPostDTO.json", jsonBody);
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                        .post(requestBody.build())
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
                                            Toast.makeText(HomeworkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();
                                            edit.putBoolean("Homework", false);
                                            edit.apply();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(HomeworkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                requestBody.addFormDataPart("homeworkPostDTO", "homeworkPostDTO.json", jsonBody);
                                Request request = new Request.Builder()
                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
                                        .post(requestBody.build())
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
                                            Toast.makeText(HomeworkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences("Record",MODE_PRIVATE);
                                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();
                                            edit.putBoolean("Homework", false);
                                            edit.apply();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(HomeworkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HomeworkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取失败！无法使用本功能！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            uri = data.getData();
            if (Build.VERSION.SDK_INT < 19) {
                path = getImagePath(uri, null);
            } else {
                if (DocumentsContract.isDocumentUri(this, uri)) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (TextUtils.equals(uri.getAuthority(), "com.android.providers.media.documents")) {
                        String id = documentId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                        path = getImagePath(contentUri, null);
                    }
                } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                    path = getImagePath(uri, null);
                } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                    path = uri.getPath();
                }
            }
            if (path != null) {

                file = new File(path);
                homework_image.setText(path);
            }
        }
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String image_path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                image_path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
            return image_path;
        }
        return null;
    }

    public static File downloadImage(String imageUrl) throws IOException {
        // 创建URL对象
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // 检查响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 创建临时文件
            File tempFile = File.createTempFile("tempImage", ".jpg");
            tempFile.deleteOnExit(); // 确保JVM退出时删除临时文件
            // 将数据从URL写入到文件
            try (InputStream is = connection.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
            // 返回文件对象
            return tempFile;
        }
        return null;
    }
}