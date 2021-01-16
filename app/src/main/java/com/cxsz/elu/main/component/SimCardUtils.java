package com.cxsz.elu.main.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.cxsz.elu.main.model.bean.SimDetailInfo;

/**
 * 获取sim卡相关信息工具类
 */

public class SimCardUtils {

    public static void initCardState(SimDetailInfo.BodyBean mealInfoBean, TextView cardStateInfo) {
        String simState = mealInfoBean.getSimState();
        if (simState != null) {
            if (simState.equals("1")) {
                cardStateInfo.setText("未激活");
            } else if (simState.equals("2")) {
                cardStateInfo.setText("正使用");
            } else if (simState.equals("3")) {
                cardStateInfo.setText("停机");
            } else if (simState.equals("4")) {
                cardStateInfo.setText("欠费");
            } else if (simState.equals("5")) {
                cardStateInfo.setText("解除集团代付");
            } else if (simState.equals("6")) {
                cardStateInfo.setText("销号");
            } else if (simState.equals("7")) {
                cardStateInfo.setText("测试期");
            } else if (simState.equals("8")) {
                cardStateInfo.setText("沉默期");
            } else if (simState.equals("9")) {
                cardStateInfo.setText("库存");
            } else if (simState.equals("10")) {
                cardStateInfo.setText("出库停机");
            } else if (simState.equals("11")) {
                cardStateInfo.setText("审核停机");
            }
        }
    }

    /**
     * 获取sim卡iccid
     *
     * @param context
     * @return
     */
//    public static String getIccId(Context context) {
//        SubscriptionManager subscriptionManager = null;
//        String iccId = "";
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//            subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
//        }
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                SubscriptionInfo activeSubscriptionInfo = subscriptionManager.getActiveSubscriptionInfo(1);
//
//                if (activeSubscriptionInfo != null) {
//                    iccId = activeSubscriptionInfo.getIccId();
//                }
//            } else {
//                iccId = telephonyManager.getSimSerialNumber();
//            }
//        } catch (SecurityException e) {
//            LoggerUtil.e(e.getMessage());
//        }
//        return iccId;
////        return "89860619140042559128";
//    }

    /**
     * 获取手机ICCID号
     */
    @SuppressLint("MissingPermission")
    public static String getIccId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String iccid = telephonyManager.getSimSerialNumber();
        return iccid;
    }
}
