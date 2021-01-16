package com.cxsz.elu.main.model.bean;

public class EventBean {
    private int code;
    private String iccidNumberFromNet;


    public EventBean(int code, String iccidNumberFromNet) {
        this.code = code;
        this.iccidNumberFromNet = iccidNumberFromNet;
    }

    public EventBean(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIccidNumberFromNet() {
        return iccidNumberFromNet;
    }

    public void setIccidNumberFromNet(String iccidNumberFromNet) {
        this.iccidNumberFromNet = iccidNumberFromNet;
    }
}
