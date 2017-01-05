package com.weibo.ad.example.entity;


import lombok.Getter;
import lombok.Setter;

public class JsonReturn<T> {
    @Getter @Setter private Integer code;
    @Getter @Setter private String msg;
    @Getter @Setter private T data;
}
