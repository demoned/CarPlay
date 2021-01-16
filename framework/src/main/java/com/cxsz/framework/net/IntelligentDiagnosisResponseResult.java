package com.cxsz.framework.net;

public class IntelligentDiagnosisResponseResult {

    /**
     * status : 0
     * data : {"info":"卡号：【1064831764640】,确认实名状态上游失败！","result":0}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : 卡号：【1064831764640】,确认实名状态上游失败！
         * result : 0
         */

        private String info;
        private int result;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
