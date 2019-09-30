package com.example.studinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class point100 extends AppCompatActivity {

    private WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point100);

        webView2=(WebView)findViewById(R.id.webView2);
        String url= "https://www.100points.gtu.ac.in";
        webView2.getSettings().setLoadsImagesAutomatically(true);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView2.loadUrl(url);
    }
}
