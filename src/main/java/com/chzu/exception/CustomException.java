package com.chzu.exception;

public class CustomException extends Exception {
    private String msg;
    private Throwable throwable;
    public CustomException(String msg){
        super(msg);
        this.msg = msg;
    }
    public CustomException(Throwable throwable){
        super(throwable);
        this.throwable = throwable;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
