package com.weibo.ad.example.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class BasicJsonEntity {

    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    @Getter @Setter private Date date;
}
