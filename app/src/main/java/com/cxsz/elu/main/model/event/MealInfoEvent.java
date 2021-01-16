package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/3/4 0004.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author zhoukeda on 2017/3/4 0004 11:16
 * @email:1009973227@qq.com
 */
public class MealInfoEvent implements Serializable{

    /**
     * errcode : 0
     * errmsg :
     * retobj : {"type":1,"packageName":"联通100M","activeDate":"2016-11-18","f":100,"stateText":"正常",
     *              "d":27,"firstAlert":"尊敬的【一路沃 】用户，等您好久啦！您当前的体验流量为: 100兆。【一路沃 】将为您提供高质量的车联网云服务，听音乐、在线导航无所不能！让您的驾驶生活更加多彩！",
     *              "totalDays":364,"usedDays":110,"endDate":"2017-11-17","n":19,"F":100,"N":19,"D":27,"TotalDays":364,
     *              "UsedDays":110,"ActiveDate":"2016-11-18","EndDate":"2017-11-17","StateText":"正常",
     *              "FirstAlert":"尊敬的【一路沃 】用户，等您好久啦！您当前的体验流量为: 100兆。【一路沃 】将为您提供高质量的车联网云服务，听音乐、在线导航无所不能！让您的驾驶生活更加多彩！",
     *              "isExperience":1,"PackageName":"联通100M"}
     */

    private int errcode;
    private String errmsg;
    private RetobjEntity retobj;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public RetobjEntity getRetobj() {
        return retobj;
    }

    public void setRetobj(RetobjEntity retobj) {
        this.retobj = retobj;
    }

    public static class RetobjEntity implements Serializable{
        /**
         * type : 1
         * packageName : 联通100M
         * activeDate : 2016-11-18
         * f : 100.0
         * stateText : 正常
         * d : 27
         * firstAlert : 尊敬的【一路沃 】用户，等您好久啦！您当前的体验流量为: 100兆。【一路沃 】将为您提供高质量的车联网云服务，听音乐、在线导航无所不能！让您的驾驶生活更加多彩！
         * totalDays : 364
         * usedDays : 110
         * endDate : 2017-11-17
         * n : 19.0
         * F : 100.0
         * N : 19.0
         * D : 27
         * TotalDays : 364
         * UsedDays : 110
         * ActiveDate : 2016-11-18
         * EndDate : 2017-11-17
         * StateText : 正常
         * FirstAlert : 尊敬的【一路沃 】用户，等您好久啦！您当前的体验流量为: 100兆。【一路沃 】将为您提供高质量的车联网云服务，听音乐、在线导航无所不能！让您的驾驶生活更加多彩！
         * isExperience : 1
         * PackageName : 联通100M
         */

        private int type;
        private double F;
        private double N;
        private int D;
        private int TotalDays;
        private int UsedDays;
        private String ActiveDate;
        private String EndDate;
        private String StateText;
        private String FirstAlert;
        private int isExperience;
        private String PackageName;

        @Override
        public String toString() {
            return "RetobjEntity{" +
                    "type=" + type +
                    ", F=" + F +
                    ", N=" + N +
                    ", D=" + D +
                    ", TotalDays=" + TotalDays +
                    ", UsedDays=" + UsedDays +
                    ", ActiveDate='" + ActiveDate + '\'' +
                    ", EndDate='" + EndDate + '\'' +
                    ", StateText='" + StateText + '\'' +
                    ", FirstAlert='" + FirstAlert + '\'' +
                    ", isExperience=" + isExperience +
                    ", PackageName='" + PackageName + '\'' +
                    ", appTitle='" + appTitle + '\'' +
                    ", appTitleLogo='" + appTitleLogo + '\'' +
                    ", appWxLogo='" + appWxLogo + '\'' +
                    ", trafficTips='" + trafficTips + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", iccid='" + iccid + '\'' +
                    '}';
        }

        /**
         * 自定义标题内容
         */
        @JSONField(name = "appTitle")
        private String appTitle;
        /**
         * 自定义的标题logo图案,base64
         */
        @JSONField(name = "appTitleLogo")
        private String appTitleLogo;
        /**
         * 自定义的微信公众号图案,base64
         */
        @JSONField(name = "appWxLogo")
        private String appWxLogo;
        /**
         * 流量不足时的提示语
         */
        @JSONField(name = "trafficTips")
        private String trafficTips;

        private String mobile;
        private String iccid;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        public double getF() {
            return F;
        }

        public void setF(double F) {
            this.F = F;
        }

        public double getN() {
            return N;
        }

        public void setN(double N) {
            this.N = N;
        }

        public int getD() {
            return D;
        }

        public void setD(int D) {
            this.D = D;
        }

        public int getTotalDays() {
            return TotalDays;
        }

        public void setTotalDays(int TotalDays) {
            this.TotalDays = TotalDays;
        }

        public int getUsedDays() {
            return UsedDays;
        }

        public void setUsedDays(int UsedDays) {
            this.UsedDays = UsedDays;
        }

        public String getActiveDate() {
            return ActiveDate;
        }

        public void setActiveDate(String ActiveDate) {
            this.ActiveDate = ActiveDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public String getStateText() {
            return StateText;
        }

        public void setStateText(String StateText) {
            this.StateText = StateText;
        }

        public String getFirstAlert() {
            return FirstAlert;
        }

        public void setFirstAlert(String FirstAlert) {
            this.FirstAlert = FirstAlert;
        }

        public int getIsExperience() {
            return isExperience;
        }

        public void setIsExperience(int isExperience) {
            this.isExperience = isExperience;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String PackageName) {
            this.PackageName = PackageName;
        }

        public String getAppTitle() {
            return appTitle;
        }

        public void setAppTitle(String appTitle) {
            this.appTitle = appTitle;
        }

        public String getAppTitleLogo() {
            return appTitleLogo;
        }

        public void setAppTitleLogo(String appTitleLogo) {
            this.appTitleLogo = appTitleLogo;
        }

        public String getAppWxLogo() {
            return appWxLogo;
        }

        public void setAppWxLogo(String appWxLogo) {
            this.appWxLogo = appWxLogo;
        }

        public String getTrafficTips() {
            return trafficTips;
        }

        public void setTrafficTips(String trafficTips) {
            this.trafficTips = trafficTips;
        }
    }

    @Override
    public String toString() {

        return "MealInfoEvent{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", retobj=" + retobj +
                '}';
    }
}
