package com.vnshine.phoenix.cooking.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.vnshine.phoenix.cooking.R;


public class AdActivity extends AppCompatActivity {
    private ImageView imgBack;
    private WebView webView;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        setup();
        addEvent();

    }

    private void addEvent() {
        link=getIntent().getStringExtra("link");
        if(link!=null){
            if(isNetworkConnected(this)){
                webView.loadUrl(link);
            }else {
                //webView.loadUrl("file:///android_asset/thanhtoan.html");
                System.out.println("offline");
            }
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdActivity.this,SplashScreen.class));
                finish();
            }
        });

    }

    private static boolean isNetworkConnected(Context activty) {
        ConnectivityManager cm = (ConnectivityManager) activty.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    public void setup(){
        imgBack= (ImageView) findViewById(R.id.imgBack);
        webView= (WebView) findViewById(R.id.web_thongtin);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);



    }
    private class MyBrowser extends WebViewClient {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
