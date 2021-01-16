package com.cxsz.elu.main.model.bean;

import android.support.annotation.NonNull;

import com.cxsz.framework.tool.SystemUtils;

import java.util.List;

public class SimPackageBean {

    /**
     * body : [{"iccid":"898602F31318C0100088","cardNumber":"17233890088","cardPackageId":6653,"packageGroupId":1,"packageActiveTime":"2020-03-03 10:36:43","packageEndTime":"2020-08-31 23:59:59","packageId":8,"packageNo":"0004","packageName":"33M +33分钟每月","presentOne":0,"presentOneTimes":0,"displayPackageName":"33M +33分钟每月","packageDesc":"33M +33分钟每月","networkGrade":4,"networkGradeStr":"4G","status":0,"statusStr":"正使用","segmentNum":11,"packageTraffic":33,"packageTrafficOut":0,"packageTrafficExt":0,"carrierOperator":1,"disableAction":4,"packageVoice":33,"packageVoiceOut":0,"packageVoiceExt":0,"apnQuantity":1,"useTraffic":24,"useTrafficHis":0,"useVoice":0,"useVoiceHis":0,"validityDuration":3,"settlementMode":1,"settlementModeStr":"月套餐","upgradeFlag":1,"packageType":1,"trialPackage":0,"packageTypeStr":"主套餐"}]
     * code : 0
     * message : 获取卡及套餐讯息成功
     */

    private int code;
    private String message;
    private List<BodyBean> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean implements Comparable<BodyBean> {
        private String packageActiveTime;
        private String packageEndTime;
        private String packageName;
        private String displayPackageName;
        private double status;
        private String statusStr;
        private double packageTraffic;
        private double useTraffic;

        public String getPackageActiveTime() {
            return packageActiveTime;
        }

        public void setPackageActiveTime(String packageActiveTime) {
            this.packageActiveTime = packageActiveTime;
        }

        public String getPackageEndTime() {
            return packageEndTime;
        }

        public void setPackageEndTime(String packageEndTime) {
            this.packageEndTime = packageEndTime;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getDisplayPackageName() {
            return displayPackageName;
        }

        public void setDisplayPackageName(String displayPackageName) {
            this.displayPackageName = displayPackageName;
        }

        public double getStatus() {
            return status;
        }

        public void setStatus(double status) {
            this.status = status;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public double getPackageTraffic() {
            return packageTraffic;
        }

        public void setPackageTraffic(double packageTraffic) {
            this.packageTraffic = packageTraffic;
        }

        public double getUseTraffic() {
            return useTraffic;
        }

        public void setUseTraffic(double useTraffic) {
            this.useTraffic = useTraffic;
        }

        @Override
        public int compareTo(@NonNull BodyBean bodyBean) {
            return (int) (SystemUtils.stringDateToLong(this.getPackageActiveTime()) - SystemUtils.stringDateToLong(bodyBean.getPackageActiveTime()));
        }
    }
}
