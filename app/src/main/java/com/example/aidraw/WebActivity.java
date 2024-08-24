package com.example.aidraw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.netWeb);
        switch (InteractionFragment.getSelection()) {
            case "选人":
                url = "";
                break;
            case "投票":
                url = "";
                break;
            case "分组":
                url = "";
                break;
            case "白板":
                url = "";
                break;
            case "计时器":
                url = "";
                break;
            case "1V1 PK":
                url = "";
                break;
            case "创意小挑战":
                url = "";
                break;
            case "趣味问答":
                url = "";
                break;
            case "接龙绘画":
                url = "";
                break;
            case "涂色游戏":
                url = "";
                break;
            case "作品猜谜":
                url = "";
                break;
        }
        //设置webView的信息配置
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        //加载网页信息
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //监听webView已经将网页加载完成了
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
    }
}