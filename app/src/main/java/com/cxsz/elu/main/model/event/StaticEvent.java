package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/5/18.
 */

/**
 * Zhou keda
 * 2017/5/18
 */
public class StaticEvent {
    private  int ruleId;
    private long timeRange;
    private float minSpeed;
    private long minDistance;
    private long maxTraffic;

    public long getMaxTraffic() {
        return maxTraffic;
    }

    public void setMaxTraffic(long maxTraffic) {
        this.maxTraffic = maxTraffic;
    }

    public long getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(long minDistance) {
        this.minDistance = minDistance;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }


    public long getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(long timeRange) {
        this.timeRange = timeRange;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    @Override
    public String toString() {
        return "StaticEvent{" +
                "ruleId=" + ruleId +
                ", timeRange=" + timeRange +
                ", minSpeed=" + minSpeed +
                ", minDistance=" + minDistance +
                ", maxTraffic=" + maxTraffic +
                '}';
    }
}
