package com.example.beryl.lmtestapp;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.taobao.windvane.WindVaneSDK;
import android.taobao.windvane.config.EnvEnum;
import android.taobao.windvane.config.WVAppParams;
import android.taobao.windvane.util.PhoneInfo;
import android.webkit.WebView;

/**
 * Created by allen.zb on 16/5/17.
 */
public class BaseApplication extends Application {

    private static boolean isDaily = true;

    @Override
    public void onCreate() {
        super.onCreate();
        initWindVa();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
    }
    private void initWindVa() {
        WVAppParams params = new WVAppParams();
        try {
            params.imei = PhoneInfo.getImei(this);
            params.imsi = PhoneInfo.getImsi(this);
        } catch (Throwable e) {

        }
        //params.appKey = "21646297";
        params.appKey = "4272";
        params.ttid = "600000@taobao_android_5.4.11";
        params.appTag = "TB";
        params.appVersion = "5.8.1.11";

        WindVaneSDK.setEnvMode(isDaily ? EnvEnum.DAILY : EnvEnum.ONLINE);

        WindVaneSDKForDefault.init(this, "cache", 0, params);
    }
}
