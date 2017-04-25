package com.example.beryl.lmtestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.taobao.windvane.webview.WVWebView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Created by beryl on 16/7/19.
 */


public class WindVaneViewProxyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LOGWindWaneview in。。。。",".....");
        LinearLayout linearLayout = new LinearLayout(this, null);

        WVWebView wvView = new WVWebView(this);
        wvView.loadUrl("http://wapp.m.taobao.com/wv/debug/windvane.html");
        setContentView(wvView);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // 返回成功，淘宝将返回正确的信息，信息保存在intent中的result项中.

            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_CANCELED) {
            // 用户主动取消操作.
        } else if (resultCode == -2) {
            // error,淘宝将返回错误码，同样解析intent中的result项，形式如下：
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        //   webViewService.releaseWebView(webView);
        super.onDestroy();
    }

}
