package com.cxsz.framework.net;

public class WeChatInfoBean {

    /**
     * code : 0
     * message : 查询卡信息成功
     * body : {"appid":"wxcd259021b83cf23b","wechatNum":"流量卡掌上厅","wechatName":"流量卡掌上厅","wechatType":"1","appsecret":"c29e84c4596e101645b4e5055fbe55a5","appToken":"weixin","orgId":29,"mchId":"1493042852","billToOrg":29,"notice":"18948333403","phone":"18948333403","autoReply":null,"url":null,"autoReplyPic":null,"autoReplyUrl":null,"iotCardHint":"请输入物联卡卡号","phoneHint":"请输入机主手机号","regReplyPic":null,"regUrl":null,"createBy":null,"createTime":[2019,9,30,10,45,36],"updateBy":null,"updateTime":"2020-02-15 19:00:41","status":null,"appAeskey":"GUrrQftB22Z5e9vKNYwilWzsM8J5iuZGbS2zZTVXdZP","mchKey":null,"loginUrl":"http://mp.iot100.online/index.html#/login","versionNo":"V1.0","payToAppid":null,"enabled":1}
     */

    private int code;
    private String message;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * appid : wxcd259021b83cf23b
         * wechatNum : 流量卡掌上厅
         * wechatName : 流量卡掌上厅
         * wechatType : 1
         * appsecret : c29e84c4596e101645b4e5055fbe55a5
         * appToken : weixin
         * orgId : 29
         * mchId : 1493042852
         * billToOrg : 29
         * notice : 18948333403
         * phone : 18948333403
         * autoReply : null
         * url : null
         * autoReplyPic : null
         * autoReplyUrl : null
         * iotCardHint : 请输入物联卡卡号
         * phoneHint : 请输入机主手机号
         * regReplyPic : null
         * regUrl : null
         * createBy : null
         * createTime : [2019,9,30,10,45,36]
         * updateBy : null
         * updateTime : 2020-02-15 19:00:41
         * status : null
         * appAeskey : GUrrQftB22Z5e9vKNYwilWzsM8J5iuZGbS2zZTVXdZP
         * mchKey : null
         * loginUrl : http://mp.iot100.online/index.html#/login
         * versionNo : V1.0
         * payToAppid : null
         * enabled : 1
         */

        private String appid;
        private String wechatNum;
        private String wechatName;
        private String wechatType;
        private String appsecret;
        private String appToken;
        private int orgId;
        private String mchId;
        private int billToOrg;
        private String notice;
        private String phone;
        private Object autoReply;
        private Object url;
        private Object autoReplyPic;
        private Object autoReplyUrl;
        private String iotCardHint;
        private String phoneHint;
        private Object regReplyPic;
        private Object regUrl;
        private Object createBy;
        private Object updateBy;
        private String updateTime;
        private Object status;
        private String appAeskey;
        private Object mchKey;
        private String loginUrl;
        private String versionNo;
        private Object payToAppid;
        private int enabled;
        private String createTime;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getWechatNum() {
            return wechatNum;
        }

        public void setWechatNum(String wechatNum) {
            this.wechatNum = wechatNum;
        }

        public String getWechatName() {
            return wechatName;
        }

        public void setWechatName(String wechatName) {
            this.wechatName = wechatName;
        }

        public String getWechatType() {
            return wechatType;
        }

        public void setWechatType(String wechatType) {
            this.wechatType = wechatType;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getAppToken() {
            return appToken;
        }

        public void setAppToken(String appToken) {
            this.appToken = appToken;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public int getBillToOrg() {
            return billToOrg;
        }

        public void setBillToOrg(int billToOrg) {
            this.billToOrg = billToOrg;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getAutoReply() {
            return autoReply;
        }

        public void setAutoReply(Object autoReply) {
            this.autoReply = autoReply;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public Object getAutoReplyPic() {
            return autoReplyPic;
        }

        public void setAutoReplyPic(Object autoReplyPic) {
            this.autoReplyPic = autoReplyPic;
        }

        public Object getAutoReplyUrl() {
            return autoReplyUrl;
        }

        public void setAutoReplyUrl(Object autoReplyUrl) {
            this.autoReplyUrl = autoReplyUrl;
        }

        public String getIotCardHint() {
            return iotCardHint;
        }

        public void setIotCardHint(String iotCardHint) {
            this.iotCardHint = iotCardHint;
        }

        public String getPhoneHint() {
            return phoneHint;
        }

        public void setPhoneHint(String phoneHint) {
            this.phoneHint = phoneHint;
        }

        public Object getRegReplyPic() {
            return regReplyPic;
        }

        public void setRegReplyPic(Object regReplyPic) {
            this.regReplyPic = regReplyPic;
        }

        public Object getRegUrl() {
            return regUrl;
        }

        public void setRegUrl(Object regUrl) {
            this.regUrl = regUrl;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getAppAeskey() {
            return appAeskey;
        }

        public void setAppAeskey(String appAeskey) {
            this.appAeskey = appAeskey;
        }

        public Object getMchKey() {
            return mchKey;
        }

        public void setMchKey(Object mchKey) {
            this.mchKey = mchKey;
        }

        public String getLoginUrl() {
            return loginUrl;
        }

        public void setLoginUrl(String loginUrl) {
            this.loginUrl = loginUrl;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public Object getPayToAppid() {
            return payToAppid;
        }

        public void setPayToAppid(Object payToAppid) {
            this.payToAppid = payToAppid;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
