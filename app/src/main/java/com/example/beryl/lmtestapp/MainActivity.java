package com.example.beryl.lmtestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.wView)
    WebView wView;
    @ViewById(R.id.txt_title)
    TextView txt_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void loadInnerWebView() {
        wView.loadUrl("https://www.baidu.com");
        wView.setWebChromeClient(new WebChromeClient() {
            //这里设置获取到的网站title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                txt_title.setText(title);
            }

        });
        wView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    //进入自定义webview
    @Click(R.id.btn_H5Page)
    public void open_H5(View view) {
        startActivity(new Intent(this, WebViewProxyActivity.class));
    }
    //进如WindWanewebview
    @Click(R.id.btn_WindVanePage)
    public void open_WindVanePage(View view) {
        startActivity(new Intent(this, WindVaneViewProxyActivity.class));
    }
    //进如WindWanewebview
    @Click(R.id.btn_WindVaneFragmentPage)
    public void open_WindVaneFragmentPage(View view) {
        startActivity(new Intent(this, WVFragmentWebViewActivity.class));
    }

    @Click(R.id.btn_back)
    public void back(View view) {
        wView.loadUrl("https://www.baidu.com");
    }
    //刷新当前页面
    @Click(R.id.btn_reload)
    public void reload(View view) {
        wView.reload();
    }
    //滚动到顶部
    @Click(R.id.btn_top)
    public void top(View view)  {
        wView.setScrollY(0);
    }



}
