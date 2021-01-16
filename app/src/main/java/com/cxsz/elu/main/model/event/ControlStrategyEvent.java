package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/5/18.
 */

import java.util.List;

/**
 * Zhou keda
 * 2017/5/18
 */
public class ControlStrategyEvent {

    /**
     * {code : 0
     ,errMsg :""
      ,ruleList : [{"shareWifi":1,"maxDayTraffic":null,"maxTraffic":null,"productId":0,"updateTime":null,"minDistance":500,"createTime":null,"ruleType":2,"customerId":0,"maxMonTraffic":null,"id":4,"state":1,"excType":1,"minSpeed":3,"timeRange":10},
                  {"shareWifi":0,"maxDayTraffic":null,"maxTraffic":null,"productId":0,"updateTime":null,"minDistance":null,"createTime":null,"ruleType":3,"customerId":0,"maxMonTraffic":null,"id":5,"state":1,"excType":1,"minSpeed":null,"timeRange":null}]
     }*/

    private int code;
    private String errMsg;
    private List<RuleListBean> ruleList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RuleListBean> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleListBean> ruleList) {
        this.ruleList = ruleList;
    }

    public static class RuleListBean {
        /**
         * shareWifi : 1
         * maxDayTraffic : null
         * maxTraffic : null
         * productId : 0
         * updateTime : null
         * minDistance : 500
         * createTime : null
         * ruleType : 2
         * customerId : 0
         * maxMonTraffic : null
         * id : 4
         * state : 1
         * excType : 1
         * minSpeed : 3
         * timeRange : 10
         */

        private int shareWifi;
        private int productId;
        private int minDistance;
        private int ruleType;
        private int customerId;
        private int id;
        private int state;
        private int excType;
        private int minSpeed;
        private int timeRange;
        // "productId": "173",
        //"customerId": "fb92fe0ca7974337ac183a30d12ccdcd"

        public long getMaxTraffic() {
            return maxTraffic;
        }

        public void setMaxTraffic(long maxTraffic) {
            this.maxTraffic = maxTraffic;
        }

        private long maxTraffic;

        public int getShareWifi() {
            return shareWifi;
        }

        public void setShareWifi(int shareWifi) {
            this.shareWifi = shareWifi;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }


        public int getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(int minDistance) {
            this.minDistance = minDistance;
        }


        public int getRuleType() {
            return ruleType;
        }

        public void setRuleType(int ruleType) {
            this.ruleType = ruleType;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getExcType() {
            return excType;
        }

        public void setExcType(int excType) {
            this.excType = excType;
        }

        public int getMinSpeed() {
            return minSpeed;
        }

        public void setMinSpeed(int minSpeed) {
            this.minSpeed = minSpeed;
        }

        public int getTimeRange() {
            return timeRange;
        }

        public void setTimeRange(int timeRange) {
            this.timeRange = timeRange;
        }

        @Override
        public String toString() {
            return "RuleListBean{" +
                    "shareWifi=" + shareWifi +
                    ", ruleType=" + ruleType +
                    ", id=" + id +
                    ", timeRange=" + timeRange +
                    ", maxTraffic=" + maxTraffic +
                    '}';
        }
    }
}
