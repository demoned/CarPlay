package com.cxsz.elu.main.model.event;
/**
 * Created by Administrator on 2017/5/5.
 */

/**
 * Zhou keda
 * 2017/5/5
 */
public class YouMengEvent {
//    kd_btn_buy,点击购买套餐,0
//    kd_fresh_flow,点击更新流量,0
//    kd_btn_history,点击购买记录,0
//    kd_btn_wx,点击公众号,0
//    kd_btn_kf,点击客服电话,0
//    kd_btn_update,点击版本更新,0
//    kd_btn_sms_buy,点击短信的购买套餐,0
    public static final String kd_btn_buy ="kd_btn_buy";
    public static final String kd_fresh_flow ="kd_fresh_flow";
    public static final String kd_btn_history ="kd_btn_history";
    public static final String kd_btn_wx ="kd_btn_wx";
    public static final String kd_btn_kf ="kd_btn_kf";
    public static final String kd_btn_update ="kd_btn_update";
    public static final String kd_btn_sms_buy ="kd_btn_sms_buy";
    public static final String kd_service = "kd_service";
    /**
     * 违反机卡绑定被关闭GPRS
     */
    public static final String kd_stop_gprs = "kd_stop_gprs";
    /**
     * 违反机卡绑定规则
     */
    public static final String kd_bind_rule_reject = "kd_bind_rule_reject";
    /**
     * 符合机卡绑定规则的卡主动打开GPRS
     */
    public static final String kd_start_gprs = "kd_start_gprs";
    /**
     * 打开首页
     */
    public static final String kd_main_ui = "kd_main_ui";
}
