package com.learning.zuul.domain.dto;


public class ResponseDto<T> {

    private int code;

    private T date;

    private String msg;

    public ResponseDto() {
    }

    public ResponseDto(int code, T date, String msg) {
        this.code = code;
        this.date = date;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
