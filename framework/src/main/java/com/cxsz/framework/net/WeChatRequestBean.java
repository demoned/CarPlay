package com.cxsz.framework.net;

public class WeChatRequestBean {

    /**
     * appId : 41DD6E37A6EE0A4F60AA39CA8A3AA386
     * timestamp : 2018-08-28 18:23:13
     * nonceStr :  f875f82199d54fbcb25f58e54cc4a9a8
     * signature : F0ACB49224CDA9C7D5345421F55B0D3DF9694463
     */

    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;

    public WeChatRequestBean(String appId, String timestamp, String nonceStr, String signature) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
