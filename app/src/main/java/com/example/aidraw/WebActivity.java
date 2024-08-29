package com.example.aidraw;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

    private WebView webView;
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);
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
        webView = findViewById(R.id.webView);
        WebSettings ws = webView.getSettings();
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//设置布局，会引起WebView的重新布局（relayout）,默认值NARROW_COLUMNS
        ws.setLoadsImagesAutomatically(true);//自动加载图片资源
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setJavaScriptEnabled(true);//执行javascript脚本
        ws.setUseWideViewPort(true);//支持HTML的“viewport”标签或者使用wide viewport
        ws.setLoadWithOverviewMode(true);//缩小内容以适应屏幕宽度
        ws.setGeolocationEnabled(true);//启用定位
        ws.setDatabaseEnabled(true);
        ws.setDomStorageEnabled(true);//启用DOM存储API
        webView.requestFocus();
        webView.canGoForward();
        webView.canGoBack();
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}