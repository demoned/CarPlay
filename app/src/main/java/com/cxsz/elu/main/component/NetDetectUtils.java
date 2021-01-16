package com.cxsz.elu.main.component;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.cxsz.elu.main.App;


/**
 * 网络状态检测工具类
 */
public class NetDetectUtils {

    /**
     * 判断是否有网络
     *
     * @return
     */
    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;//when machine can get Internet.
                }
            }
        }
        return false;
    }

    //返回当前网络连接类型
    public static int getNetworkType(Context context) {
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != systemService) {
            NetworkInfo info = systemService.getActiveNetworkInfo();
            if (null != info) return info.getType();
        }
        return -1;//情况未知，则认为没有网络
    }

    public static String getNetworkNameByType(int type) {
        switch (type) {
            case -1://没有网络
                return "NO NETWORK";
            case ConnectivityManager.TYPE_MOBILE:   //移动网络
                return "MOBILE";
            case ConnectivityManager.TYPE_WIFI:     //WiFi
                return "WIFI";
            case ConnectivityManager.TYPE_MOBILE_MMS://彩信网络
                return "MOBILE_MMS";
            case ConnectivityManager.TYPE_MOBILE_SUPL:
                return "MOBILE_SUPL";
            case ConnectivityManager.TYPE_MOBILE_DUN://提供了通过 Bluetooth 无线技术接入 Internet 和其它拨号服务的标准
                return "MOBILE_DUN";
            case ConnectivityManager.TYPE_MOBILE_HIPRI:
                return "MOBILE_HIPRI";
            case ConnectivityManager.TYPE_WIMAX:     //全球微波互联接入
                return "WIMAX";
            case ConnectivityManager.TYPE_BLUETOOTH:
                return "BLUETOOTH";
            case ConnectivityManager.TYPE_DUMMY:
                return "DUMMY";
            case ConnectivityManager.TYPE_ETHERNET:
                return "ETHERNET";
            case 10:
                return "MOBILE_FOTA";
            case 11:
                return "MOBILE_IMS";
            case 12:
                return "MOBILE_CBS";
            case 13:
                return "WIFI_P2P";
            case 14:
                return "MOBILE_IA";
            case 15:
                return "MOBILE_EMERGENCY";
            case 16:
                return "PROXY";
            case ConnectivityManager.TYPE_VPN:
                return "VPN";
            default:
                return "未知：" + Integer.toString(type);
        }
    }


    //获取当前网络状态：2G、3G、4G、未知
    public static String getMobileNetworkType(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_CDMA:                // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_IDEN://L_8           // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:                // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_1xRTT: //CDMA2000    // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:                // ~ 100 kbps
            case 16: //  NETWORK_TYPE_GSM
                return "2G";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:              // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_UMTS:  //WCDMA       // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:              // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_HSPA:                // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD://L_11         // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:               // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:               // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B://L_9         // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP://L_13         // ~ 10-20 Mbps
            case 17: //  NETWORK_TYPE_TD_SCDMA        //TD_SCDMA
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE://L_11           // ~ 10+ Mbps
            case 18: //  NETWORK_TYPE_IWLAN
                return "4G";
            default:
                return "UnKnown";
        }
    }
}
