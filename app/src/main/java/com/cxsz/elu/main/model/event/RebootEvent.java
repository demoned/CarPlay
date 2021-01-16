package com.cxsz.elu.main.model.event;/**
 * Created by Administrator on 2017/5/8.
 */

import java.io.Serializable;

/**
 * 收到注册激活成功的短信, 重启设备
 */
public class RebootEvent implements Serializable {


    private int code;
    private String message;
    private String body;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
