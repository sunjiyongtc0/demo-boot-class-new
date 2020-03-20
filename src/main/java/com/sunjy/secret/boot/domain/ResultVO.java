package com.sunjy.secret.boot.domain;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class ResultVO implements Serializable {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String msg;

    private int count;

    private Object data;


    public JSONObject toJSONObject(){
        return JSONObject.parseObject(JSONObject.toJSON(this).toString());
    }
}
