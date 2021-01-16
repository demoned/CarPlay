package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/3/1 0001.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoukeda on 2017/3/1 0001 19:12
 * @email:1009973227@qq.com
 */
public class EquationEvent implements Serializable {
    private boolean isUser;//是否体验卡
    private long time;//上传地理位置时间
    private int type;//1---》调用高德地图，2---》调用本地地图
    private String success;//是否我公司卡
    private List<FlowUsageRate> usageRates;//流量公式

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public List<FlowUsageRate> getUsageRates() {
        return usageRates;
    }

    public void setUsageRates(List<FlowUsageRate> usageRates) {
        this.usageRates = usageRates;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }



    public class FlowUsageRate {
        private double startUsage;

        public double getStartUsage() {
            return startUsage;
        }

        public void setStartUsage(int startUsage) {
            this.startUsage = startUsage;
        }

        public double getEndUsage() {
            return endUsage;
        }

        public void setEndUsage(int endUsage) {
            this.endUsage = endUsage;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        private double endUsage;
        private double rate;
    }
}
