package com.cxsz.elu.main.model.bean;

import com.google.gson.annotations.SerializedName;

public class MQTTResponseBean {

    /**
     * id : 6
     * version : 1.0
     * params : {"cx-kgxp":5}
     */

    private String id;
    private String version;
    private ParamsBean params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * cx-kgxp : 5
         */

        @SerializedName("cx-kgxp")
        private int cxkgxp;

        public int getCxkgxp() {
            return cxkgxp;
        }

        public void setCxkgxp(int cxkgxp) {
            this.cxkgxp = cxkgxp;
        }
    }
}
