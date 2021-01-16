package com.cxsz.framework.constant;

import com.cxsz.framework.BuildConfig;

/**
 * 全局的静态变量
 */
public class KeyConstants {
    public static final String SP_INFO = "code_wevoice";
    public static final String NET_USER_ID = "user_id";
    public static final String NET_TOKEN = "net_token";
    public static final String SECRETS = BuildConfig.APP_SECRETS;
    public static final String APP_ID = BuildConfig.APP_ID;
    public static final String NONCE_STR = "sanjitongchuanandchanxingshenzhou";
    public static final String PRODUCT_ID = "V55VM517Jp";
    public static final String REGISTER_URL = "https://openapi.heclouds.com/common?action=CreateDevice&version=1";
    /*Permissions Code*/
    public static final int CHECK_GPS_CODE = 0x1;
    public static final int PERMISSION_STORAGE_CODE = 0x3;
    public static final int PERMISSION_READ_PHONE_STATE_CODE = 0x5;
    public static final int REQUEST_SIM_DETAIL_INFO_FAILURE = 0x7;
    public static final int REQUEST_SIM_PACKAGE_INFO_FAILURE = 0x8;

    public static final int PERMISSION_ACCESS_FINE_LOCATION = 0x9;
    public static final int PERMISSION_ACCESS_COARSE_LOCATION = 0x10;

    public static final int SHOW_CURRENT_MEAL_WINDOW = 0x21;
    public static final int SHOW_MEAL_END_DAY_WINDOW = 0x22;
    public static final int SHOW_MEAL_END_FLOW_WINDOW = 0x23;

    public static final String MEAL_INFO_BEAN = "MealInfoBean";
    public static final String ONE_NET_DEVICE_INFO = "one_net_device_info";
    public static final int INIT_ONE_NET = 0x26;
    public static final String USER_ACCESS_KEY = "2e2aUa2mXMnO33PvRYVQlQh+ZhvhSSC6lfPVtd2J50frhZg0H0cibnnjZsWACWMLbmqvCl5/j78fUGJszcznjQ==";
    public static final String HOST_URL = "tcp://218.201.45.7:1883";
    public static final String USER_ID = "217906";

}
