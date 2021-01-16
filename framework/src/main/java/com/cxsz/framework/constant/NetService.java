package com.cxsz.framework.constant;

import android.content.Context;

import com.cxsz.framework.BuildConfig;

/**
 * 网络服务类
 * Created by cxsz-hp11 on 2018/6/1.
 */
public class NetService {
    //用于判断是否调试模式
    public static final boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    //App内接口回调的主机名
    public static final String SERVER_URL = BuildConfig.SERVER_URL;
    //微信登录的主机名
    public static final String WE_CHAT_URL = BuildConfig.WECHAT_URL;

    public static final String ORG_PARENT_ID = "332";

    public static String getHostUrl() {
        return BuildConfig.SERVER_URL;
    }
}
