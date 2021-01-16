package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/5/8.
 */

/**
 * Zhou keda
 * 2017/5/8
 */
public class UpdateEvent {
    /**
     * code : 0
     * message : 最新版本url返回成功
     * body : {"appVersion":"30","versionDesc":"版本更新","whetherEffec":true,"download":1,"versionState":0,"downloadUrl":"http://testwww.cxsz.com.cn/testapp/yiluwoV2.6.5.apk","versionName":"V2.6.4"}
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
         * appVersion : 30
         * versionDesc : 版本更新
         * whetherEffec : true
         * download : 1
         * versionState : 0
         * downloadUrl : http://testwww.cxsz.com.cn/testapp/yiluwoV2.6.5.apk
         * versionName : V2.6.4
         */

        private String appVersion;
        private String versionDesc;
        private boolean whetherEffec;
        private int download;
        private int versionState;
        private String downloadUrl;
        private String versionName;

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getVersionDesc() {
            return versionDesc;
        }

        public void setVersionDesc(String versionDesc) {
            this.versionDesc = versionDesc;
        }

        public boolean isWhetherEffec() {
            return whetherEffec;
        }

        public void setWhetherEffec(boolean whetherEffec) {
            this.whetherEffec = whetherEffec;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public int getVersionState() {
            return versionState;
        }

        public void setVersionState(int versionState) {
            this.versionState = versionState;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
