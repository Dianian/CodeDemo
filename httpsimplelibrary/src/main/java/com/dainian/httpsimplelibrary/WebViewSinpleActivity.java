package com.dainian.httpsimplelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewSinpleActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_sinple);
        initView();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);//让WebView支持JS脚本
        mWebView.setWebViewClient(new WebViewClient());//当从一个网页跳转到另一个网页时仍在当前WebView中，而不是打开系统浏览器
        mWebView.loadUrl("http://www.qq.com");//传入网址
    }
}
