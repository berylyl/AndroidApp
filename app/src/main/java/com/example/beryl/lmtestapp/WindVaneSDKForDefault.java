package com.example.beryl.lmtestapp;

import android.content.Context;
import android.taobao.windvane.WindVaneSDK;
import android.taobao.windvane.config.WVAppParams;
import android.taobao.windvane.jsbridge.api.WVAPI;
import android.taobao.windvane.util.TaoLog;

/**
 * 初始化
 */
public class WindVaneSDKForDefault {

    public static final String SPNAME = "browserSP";
    public static String Spyd_demote = "demote";

    /**
     * 初始化WindVane 的basic功能，以及一些与宿主APP相关的初始化工作
     */
    public static void init(Context context, String cacheDir, int mode,
                            WVAppParams params) {

        TaoLog.setLogSwitcher(false);
        /**
         * windvane 核心初始化，功能
         */
        WindVaneSDK.init(context, cacheDir, mode, params);
        WVAPI.setup();
    }

}
