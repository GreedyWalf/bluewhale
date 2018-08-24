package com.qs.bluewhale.utils;

import java.util.Map;

/**
 * ajax请求响应json
 */
public class JsonResult {

    private JsonStatus status = JsonStatus.SUCCESS;
    private String msg;
    private Object data;
    private Map<String, String> resultMap;

    public JsonResult(JsonStatus status, String msg, Map<String, String> resultMap) {
        this.status = status;
        this.msg = msg;
        this.resultMap = resultMap;
    }

    public JsonResult(JsonStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonResult() {

    }

    public JsonStatus getStatus() {
        return status;
    }

    public void setStatus(JsonStatus status) {
        this.status = status;
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

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }
}
