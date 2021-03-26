package com.chzu.util;

public class TableDate {
    private int code;
    private String msg;
    private Integer count;
    private Object data;

    public TableDate(Object data,int count){
        this.code = 0;
        this.data = data;
        this.count = count;
    }

    public TableDate(String msg){
        this.code = 1;
        this.data = null;
        this.msg = msg;
        this.count = 0;
    }

    public static TableDate ok(Object data,int count){
        return new TableDate(data,count);
    }

    public static TableDate error(String msg){
        return new TableDate(msg);
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
