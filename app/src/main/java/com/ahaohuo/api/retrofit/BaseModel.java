package com.ahaohuo.api.retrofit;

/**
 * Author jzy
 * Date 2016/8/31
 */

public class BaseModel<T> {
    public String code;
    public String msg;

    public T data;

    public boolean success() {
        return code.equals("200");
    }
}
