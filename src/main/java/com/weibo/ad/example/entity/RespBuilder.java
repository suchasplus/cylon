package com.weibo.ad.example.entity;

public class RespBuilder {
    public static <T> JsonReturn<T> build(T t, Integer code, String msg) {
        JsonReturn<T> jr = new JsonReturn<>();
        jr.setData(t);
        jr.setCode(code);
        jr.setMsg(msg);
        return jr;
    }
}
