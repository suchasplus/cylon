package com.weibo.ad.cylon.entity;


import lombok.Getter;
import lombok.Setter;

public class JsonReturn<T> {
    @Getter @Setter private Integer code;
    @Getter @Setter private String msg;
    @Getter @Setter private T data;
}
