package com.cxsz.elu.main.model.bean;

import java.io.Serializable;

public class RegisterDeviceBean implements Serializable {

    /**
     * name : no003
     * node_type : 1
     * desc : iot application
     * sec_key : imQE5CGsIiT9ZMBMD/bSbqMnPIBwXXsYynQQsi/fimk=
     * created_time : 2020-06-08T10:33:30.442Z
     * protocol : 1
     */

    private String name;
    private int node_type;
    private String desc;
    private String sec_key;
    private String created_time;
    private int protocol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNode_type() {
        return node_type;
    }

    public void setNode_type(int node_type) {
        this.node_type = node_type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSec_key() {
        return sec_key;
    }

    public void setSec_key(String sec_key) {
        this.sec_key = sec_key;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}