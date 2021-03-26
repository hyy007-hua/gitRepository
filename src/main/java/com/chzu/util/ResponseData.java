package com.chzu.util;

/***
 * 返回前端的工具类，适合以下特定格式的json数据
 */
public class ResponseData {
    private int code;
    private String msg;
    private Object data;

    public ResponseData(Object data){
        this.code = 0;
        this.msg = "";
        this.data = data;
    }
    public ResponseData(String msg,Object data){
        this.code = 1;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData ok(Object data){
        return new ResponseData(data);
    }

    public static ResponseData error(String msg,Object data){
        return new ResponseData(msg,data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
