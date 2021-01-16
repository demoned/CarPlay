package com.cxsz.framework.net;

import java.io.Serializable;

/**
 * Created by cxsz-luyong on 2017/12/6.
 */

public class CodeData<T> implements Serializable {


    /**
     * code : 0
     * message : ok
     * body : []
     */

    private int code ;
    private String message ;
    private T body;

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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CodeData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
