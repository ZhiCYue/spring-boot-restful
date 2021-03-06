package com.huami.yuezhichao.restful.resp;

/**
 * response
 */
public class Response<T> {

    // 返回码
    private String code;
    // 返回描述
    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
