package com.sadek.orxstradev.smartmall.activites;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.sadek.orxstradev.smartmall.R;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        String urlString = getIntent().getStringExtra("url");
        if (urlString != null)
            webView.loadUrl(urlString);
    }
}